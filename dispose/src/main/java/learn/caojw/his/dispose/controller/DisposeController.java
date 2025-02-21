package learn.caojw.his.dispose.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.dispose.entity.Dispose;
import learn.caojw.his.dispose.service.IDisposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dispose")
public class DisposeController {
    private final IDisposeService disposeService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Dispose> request) {
        disposeService.insert(request.data());
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") Long id) {
        disposeService.delete(id);
        return Response.ok();
    }

    @GetMapping
    public Response<Collection<Dispose>> selectAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return Response.ok(disposeService.selectAll(page, size));
    }

    @GetMapping("/{code}")
    public Response<Collection<Dispose>> selectByCode(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @PathVariable("code") String code) {
        return Response.ok(disposeService.selectByCode(page, size, code));
    }
}
