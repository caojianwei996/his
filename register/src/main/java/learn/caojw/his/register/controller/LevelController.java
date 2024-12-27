package learn.caojw.his.register.controller;

import learn.caojw.his.common.entity.Response;
import learn.caojw.his.register.entity.Department;
import learn.caojw.his.register.entity.Level;
import learn.caojw.his.register.service.IDepartmentService;
import learn.caojw.his.register.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/level")
public class LevelController {
    private final ILevelService levelService;

    @GetMapping
    public Response<Collection<Level>> selectAll() {
        return Response.ok(levelService.selectAll());
    }
}
