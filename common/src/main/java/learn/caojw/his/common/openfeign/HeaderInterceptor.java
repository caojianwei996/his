package learn.caojw.his.common.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import learn.caojw.his.common.interceptor.AuthorityInterceptor;

public class HeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", AuthorityInterceptor.getString());
    }
}
