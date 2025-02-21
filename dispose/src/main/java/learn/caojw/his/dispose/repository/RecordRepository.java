package learn.caojw.his.dispose.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.dispose.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository extends BaseMapper<Record> {
}
