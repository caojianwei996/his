package learn.caojw.his.medicine.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 药品业务层
 *
 * @author 曹健伟
 */
public interface IMedicineService {
    void insert(Medicine medicine);

    void delete(Long id);

    Page<Medicine> select(Integer page, Integer size, String code);

    Page<Record> selectRecord(int page, int size);

    void sale(Long id, BigDecimal number);

    void back(Long id, BigDecimal number);
}
