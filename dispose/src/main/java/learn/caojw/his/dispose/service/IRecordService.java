package learn.caojw.his.dispose.service;

import learn.caojw.his.dispose.entity.Record;

import java.util.Collection;

public interface IRecordService {
    void insert(Record record);

    void update(Record record);

    Record selectById(Long id);

    Collection<Record> selectAll();
}
