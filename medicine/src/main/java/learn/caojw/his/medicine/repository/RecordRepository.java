package learn.caojw.his.medicine.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.medicine.entity.Record;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录持久层
 *
 * @author 曹健伟
 */
@Mapper
public interface RecordRepository extends BaseMapper<Record> {

}
