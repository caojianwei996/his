package learn.caojw.his.register.controller;

import learn.caojw.his.common.entity.Response;
import learn.caojw.his.register.entity.Employee;
import learn.caojw.his.register.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @GetMapping
    public Response<Collection<Employee>> select(@RequestParam(value = "department", defaultValue = "") Long department, @RequestParam(value = "level", defaultValue = "") Long level, @RequestParam(value = "time", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime time) {
        return Response.ok(employeeService.select(department, level, time));
    }
}
