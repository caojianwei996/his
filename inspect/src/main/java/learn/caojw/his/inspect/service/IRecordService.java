package learn.caojw.his.inspect.service;

import learn.caojw.his.inspect.entity.Record;

import java.util.Collection;

public interface IRecordService {
    void insert(Record record);

    void update(Record record);

    Collection<Record> selectAll();

    Record selectById(Long id);
}
