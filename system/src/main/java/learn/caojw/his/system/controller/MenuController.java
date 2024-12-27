package learn.caojw.his.system.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.system.entity.Menu;
import learn.caojw.his.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 菜单控制器
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@RequestMapping("/menu")
@RestController
public class MenuController {
    private final IMenuService menuService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Menu> request) {
        menuService.insert(request.data());
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable String id) {
        menuService.delete(id);
        return Response.ok();
    }

    @PutMapping
    public Response<Void> update(@RequestBody Request<Menu> request) {
        menuService.update(request.data());
        return Response.ok();
    }

    @GetMapping
    public Response<Collection<Menu>> select() {
        return Response.ok(menuService.select());
    }
}
