package learn.caojw.his.register.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.register.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * 挂号持久层
 *
 * @author 曹健伟
 */
@Mapper
public interface DepartmentRepository extends BaseMapper<Department> {
}
