package learn.caojw.his.register.repository;

import learn.caojw.his.register.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
public interface EmployeeRepository {
    Employee selectById(@Param("id") String id);

    Collection<Employee> select(@Param("department") Long department, @Param("level") Long level);
}
