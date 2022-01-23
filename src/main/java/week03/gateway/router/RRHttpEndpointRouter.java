package week03.gateway.router;

import java.util.List;

public class RRHttpEndpointRouter implements HttpEndpointRouter {

    private static int index = 0;
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        index = index % size;
        String url = urls.get(index);
        index++;
        return url;
    }
}
