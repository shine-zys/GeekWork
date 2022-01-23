package week03.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponse2Filter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("user_t", "shine_zys");
    }
}
