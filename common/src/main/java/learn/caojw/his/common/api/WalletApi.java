package learn.caojw.his.common.api;

import learn.caojw.his.common.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient("wallet-service/wallet")
public interface WalletApi {
    @PostMapping("/wallet/consume/{money}")
    Response<Void> consume(@PathVariable("money") BigDecimal money);

    @PostMapping("/wallet/recharge/{money}")
    Response<Void> recharge(@PathVariable("money") BigDecimal money);
}
