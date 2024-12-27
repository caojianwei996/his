package learn.caojw.his.register.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.register.entity.Register;
import learn.caojw.his.register.service.IRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 挂号控制层
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegisterController {
    private final IRegisterService registerService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Register> request) {
        registerService.insert(request.data());
        return Response.ok();
    }

    @PutMapping
    public Response<Void> update(@RequestBody Request<Register> request) {
        registerService.update(request.data());
        return Response.ok();
    }

    @GetMapping("/all")
    public Response<Collection<Register>> select() {
        return Response.ok(registerService.select());
    }

    @GetMapping("/id/{id}")
    public Response<Register> selectById(@PathVariable("id") Long id) {
        return Response.ok(registerService.selectById(id));
    }

    @GetMapping("/name/{name}")
    public Response<Collection<Register>> selectByName(@PathVariable("name") String name) {
        return Response.ok(registerService.selectByName(name));
    }

    @GetMapping
    public Response<Collection<Register>> selectByEmployee() {
        return Response.ok(registerService.selectByEmployee());
    }
}
