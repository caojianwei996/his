package learn.caojw.his.wallet.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.wallet.entity.Record;
import learn.caojw.his.wallet.entity.Wallet;
import learn.caojw.his.wallet.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 钱包控制层
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final IWalletService walletService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Wallet> request) {
        walletService.insert(request.data());
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") String id) {
        walletService.delete(id);
        return Response.ok();
    }

    @GetMapping
    public Response<Wallet> select() {
        return Response.ok(walletService.select());
    }

    @PostMapping("/consume/{money}")
    public Response<Void> consume(@PathVariable("money") BigDecimal money) {
        walletService.consume(money);
        return Response.ok();
    }

    @PostMapping("/recharge/{money}")
    public Response<Void> recharge(@PathVariable("money") BigDecimal money) {
        walletService.recharge(money);
        return Response.ok();
    }

    @GetMapping("/record")
    public Response<Collection<Record>> select(@RequestParam("page") int page, @RequestParam("size") int size) {
        return Response.ok(walletService.selectRecord(page, size));
    }
}
