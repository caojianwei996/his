package learn.caojw.his.inspect.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.inspect.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository extends BaseMapper<Record> {
}
