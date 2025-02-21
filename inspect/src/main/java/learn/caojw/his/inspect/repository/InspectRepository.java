package learn.caojw.his.inspect.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.inspect.entity.Inspect;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InspectRepository extends BaseMapper<Inspect> {
}
