package week03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.codec.digest.DigestUtils;

public class HeaderHttpRequest2Filter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("s_key", DigestUtils.md5(fullRequest.uri()));
    }
}
