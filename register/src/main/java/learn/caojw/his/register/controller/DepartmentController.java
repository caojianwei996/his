package learn.caojw.his.register.controller;

import learn.caojw.his.common.entity.Response;
import learn.caojw.his.register.entity.Department;
import learn.caojw.his.register.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final IDepartmentService departmentService;

    @GetMapping
    public Response<Collection<Department>> selectAll() {
        return Response.ok(departmentService.selectAll());
    }

    @GetMapping("/{code}")
    public Response<Collection<Department>> selectByCode(@PathVariable("code") String code) {
        return Response.ok(departmentService.selectByCode(code));
    }
}
