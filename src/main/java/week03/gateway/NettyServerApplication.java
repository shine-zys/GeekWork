package week03.gateway;

import week03.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    
    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort","8888");

        // 这是之前的单个后端url的例子
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
//          //  http://localhost:8888  /api/hello==> gateway API
//          //  http://localhost:8088/api/hello  ==> backend service
        // java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务

        /*************** 多服务转发  http://localhost:8888  ****************/
        // http://localhost:8801   =>  /week2/HttpClientHelper  main进程   https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html
        // http://localhost:8808   =>  /week3/netty/NettyHttpServer  main进程   非/test路径   https://www.baidu.com
        // curl 路由轮询

        String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8808");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
