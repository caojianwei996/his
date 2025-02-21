package learn.caojw.his.register.service;

import learn.caojw.his.register.entity.Employee;

import java.time.LocalDateTime;
import java.util.Collection;

public interface IEmployeeService {
    Collection<Employee> select(Long department, Long level, LocalDateTime localDateTime);
}
