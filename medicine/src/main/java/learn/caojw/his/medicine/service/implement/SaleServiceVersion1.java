package learn.caojw.his.medicine.service.implement;

import io.seata.spring.annotation.GlobalTransactional;
import learn.caojw.his.common.api.WalletApi;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;
import learn.caojw.his.medicine.repository.MedicineRepository;
import learn.caojw.his.medicine.repository.RecordRepository;
import learn.caojw.his.medicine.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * 售卖业务层实现版本1
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class SaleServiceVersion1 implements ISaleService {
    private final MedicineRepository medicineRepository;
    private final RecordRepository recordRepository;
    private final WalletApi walletApi;

    @GlobalTransactional
    @Override
    public void sale(Long id, BigDecimal number) {
        Medicine medicine = medicineRepository.selectById(id);
        if (medicine == null) {
            throw new ServiceException("未找到药品");
        } else if (medicine.getNumber().compareTo(number) < 0) {
            throw new ServiceException("药品不足");
        } else {
            Response<Void> response = walletApi.consume(medicine.getPrice().multiply(number));
            if (response == null) {
                throw new ServiceException("");
            } else if (response.code() != 20000) {
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
        }
    }

    @GlobalTransactional
    @Override
    public void back(Long id, BigDecimal number) {
        Medicine medicine = medicineRepository.selectById(id);
        if (medicine == null) {
            throw new ServiceException("未找到药品");
        } else {
            Response<Void> response = walletApi.recharge(medicine.getPrice().multiply(number));
            if (response == null) {
                throw new ServiceException("");
            } else if (response.code() != 20000) {
                throw new ServiceException(response.message());
            } else {
                recordRepository.insert(recordDecrease(medicine, number));
                medicine.setNumber(medicine.getNumber().add(number));
                medicineRepository.updateById(medicine);
            }
        }
    }

    @Transactional
    @Override
    public void replenish(Long id, BigDecimal number) {
        Medicine medicine = medicineRepository.selectById(id);
        if (medicine == null) {
            throw new ServiceException("未找到药品");
        } else {
            recordRepository.insert(recordDecrease(medicine, number));
            medicine.setNumber(medicine.getNumber().add(number));
            medicineRepository.updateById(medicine);
        }
    }

    private Record recordDecrease(Medicine medicine, BigDecimal number) {
        Record record = new Record();
        record.setMedicine(medicine.getId());
        record.setBefore(medicine.getNumber());
        record.setAfter(medicine.getNumber().add(number));
        record.setChange(number);
        record.setTime(OffsetDateTime.now());
        return record;
    }
}
