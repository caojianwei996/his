package learn.caojw.his.register.service.implement;

import learn.caojw.his.register.entity.Employee;
import learn.caojw.his.register.repository.EmployeeRepository;
import learn.caojw.his.register.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Collection<Employee> select(Long department, Long level, LocalDateTime localDateTime) {
        Collection<Employee> select = employeeRepository.select(department, level);
        if (localDateTime == null) {
            return select;
        } else {
            return select.stream()
                    .filter(employee -> employee
                            .getScheduling()[
                            localDateTime.getDayOfWeek().getValue() * 2 + localDateTime.getHour() >= 12 ? 1 : 0] != null)
                    .toList();
        }
    }
}
