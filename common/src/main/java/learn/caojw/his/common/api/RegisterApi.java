package learn.caojw.his.common.api;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("register-service/register/register")
public interface RegisterApi {
    @PutMapping
    Response<Void> update(@RequestBody Request<?> request);
}
