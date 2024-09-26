package learn.caojw.his.medicine.controller;

import learn.caojw.his.common.entity.Response;
import learn.caojw.his.medicine.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 售药控制层
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@RequestMapping("/sale")
@RestController
public class SaleController {
    private final ISaleService saleService;

    @PostMapping("/sale/{id}/{number}")
    public Response<Void> sale(@PathVariable("id") Long id, @PathVariable("number") BigDecimal number) {
        saleService.sale(id, number);
        return Response.ok();
    }

    @PostMapping("/back/{id}/{number}")
    public Response<Void> back(@PathVariable("id") Long id, @PathVariable("number") BigDecimal number) {
        saleService.back(id, number);
        return Response.ok();
    }

    @PostMapping("/replenish/{id}/{number}")
    public Response<Void> replenish(@PathVariable("id") Long id, @PathVariable("number") BigDecimal number) {
        saleService.replenish(id, number);
        return Response.ok();
    }
}
