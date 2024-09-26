package learn.caojw.his.wallet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.wallet.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository extends BaseMapper<Record> {
}
