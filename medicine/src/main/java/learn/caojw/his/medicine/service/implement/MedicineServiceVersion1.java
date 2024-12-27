package learn.caojw.his.medicine.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.common.util.PinyinUtil;
import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;
import learn.caojw.his.medicine.repository.MedicineRepository;
import learn.caojw.his.medicine.repository.RecordRepository;
import learn.caojw.his.medicine.service.IMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 药品业务层实现版本1
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class MedicineServiceVersion1 implements IMedicineService {
    private final MedicineRepository medicineRepository;
    private final RecordRepository recordRepository;

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
}
