package learn.caojw.his.medicine.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.common.api.WalletApi;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.common.util.PinyinUtil;
import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;
import learn.caojw.his.medicine.repository.MedicineRepository;
import learn.caojw.his.medicine.repository.RecordRepository;
import learn.caojw.his.medicine.service.IMedicineService;
import lombok.RequiredArgsConstructor;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * 药品业务层实现
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class MedicineService implements IMedicineService {
    private final MedicineRepository medicineRepository;
    private final RecordRepository recordRepository;
    private final WalletApi walletApi;
    private final RedissonClient redissonClient;

    @Override
    public void insert(Medicine medicine) {
        medicine.setCode(PinyinUtil.toPinyin(medicine.getName()).toLowerCase());
        medicineRepository.insert(medicine);
    }

    @Override
    public void delete(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public Page<Medicine> select(Integer page, Integer size, String code) {
        if (StringUtils.hasText(code)) {
            return medicineRepository.selectPage(new Page<>(page, size), Wrappers.<Medicine>lambdaQuery().likeRight(Medicine::getCode, code));
        } else {
            return medicineRepository.selectPage(new Page<>(page, size), null);
        }
    }

    @Override
    public Page<Record> selectRecord(int page, int size) {
        return recordRepository.selectPage(new Page<>(page, size), null);
    }

    @GlobalTransactional
    @Override
    public void sale(Long id, BigDecimal number) {
        Medicine medicine = medicineRepository.selectById(id);
        if (medicine == null) {
            throw new ServiceException("未找到药品");
        } else if (medicine.getNumber().compareTo(number) < 0) {
            throw new ServiceException("药品不足");
        } else {
            RLock lock = redissonClient.getLock("medicine:" + medicine.getId());
            try {
                lock.lock();
                Response<Void> response = walletApi.consume(medicine.getPrice().multiply(number));
                if (response.code() != 20000) {
                    throw new ServiceException(response.message());
                } else {
                    Record record = new Record();
                    record.setMedicine(medicine.getId());
                    record.setBefore(medicine.getNumber());
                    record.setAfter(medicine.getNumber().subtract(number));
                    record.setChange(number);
                    record.setTime(OffsetDateTime.now());
                    recordRepository.insert(record);
                    medicine.setNumber(medicine.getNumber().subtract(number));
                    medicineRepository.updateById(medicine);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @GlobalTransactional
    @Override
    public void back(Long id, BigDecimal number) {
        Medicine medicine = medicineRepository.selectById(id);
        if (medicine == null) {
            throw new ServiceException("未找到药品");
        } else {
            RLock lock = redissonClient.getLock("medicine:" + medicine.getId());
            try {
                lock.lock();
                Response<Void> response = walletApi.recharge(medicine.getPrice().multiply(number));
                if (response.code() != 20000) {
                    throw new ServiceException(response.message());
                } else {
                    Record record = new Record();
                    record.setMedicine(medicine.getId());
                    record.setBefore(medicine.getNumber());
                    record.setAfter(medicine.getNumber().add(number));
                    record.setChange(number);
                    record.setTime(OffsetDateTime.now());
                    recordRepository.insert(record);
                    medicine.setNumber(medicine.getNumber().add(number));
                    medicineRepository.updateById(medicine);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
