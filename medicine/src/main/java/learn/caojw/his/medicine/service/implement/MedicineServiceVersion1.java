package learn.caojw.his.medicine.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;
import learn.caojw.his.medicine.repository.MedicineRepository;
import learn.caojw.his.medicine.repository.RecordRepository;
import learn.caojw.his.medicine.service.IMedicineService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (char c : medicine.getName().toCharArray()) {
                pinyin.append(c >= 19968 && c <= 40869 ? PinyinHelper.toHanyuPinyinStringArray(c, format)[0].charAt(0) : c);
            }
        } catch (BadHanyuPinyinOutputFormatCombination ignored) {
            pinyin.setLength(0);
            pinyin.append(medicine.getName());
        }
        medicine.setCode(pinyin.toString());
        medicineRepository.insert(medicine);
    }

    @Override
    public void delete(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public void update(Medicine medicine) {
        medicineRepository.updateById(medicine);
    }

    @Override
    public Collection<Medicine> select(int page, int size) {
        return medicineRepository.selectPage(new Page<>(page, size), null).getRecords();
    }

    @Override
    public Collection<Medicine> select(String code) {
        return medicineRepository.selectList(Wrappers.<Medicine>lambdaQuery().likeRight(Medicine::getCode, code.toUpperCase()));
    }

    @Override
    public Medicine selectById(Long id) {
        return medicineRepository.selectById(id);
    }

    @Override
    public Collection<Record> selectRecord(int page, int size) {
        return recordRepository.selectPage(new Page<>(page, size), null).getRecords();
    }
}
