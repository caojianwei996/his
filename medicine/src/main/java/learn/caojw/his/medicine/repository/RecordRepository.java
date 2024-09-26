package learn.caojw.his.medicine.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.medicine.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository extends BaseMapper<Record> {
}
