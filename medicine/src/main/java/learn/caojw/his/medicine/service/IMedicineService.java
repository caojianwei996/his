package learn.caojw.his.medicine.service;

import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;

import java.util.Collection;

public interface IMedicineService {
    void insert(Medicine medicine);

    void delete(Long id);

    void update(Medicine medicine);

    Collection<Medicine> select(int page, int size);

    Collection<Medicine> select(String code);

    Medicine selectById(Long id);

    Collection<Record> selectRecord(int page, int size);
}
