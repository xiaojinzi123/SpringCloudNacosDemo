package com.xiaojinzi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServerApi serverApi;

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        List<ServiceInstance> targetIns = null;
        try {
            targetIns = discoveryClient.getInstances("service-server");
        } catch (Exception e) {
            // e.printStackTrace();
        }
        System.out.println("targetIns = " + targetIns);
        System.out.println("discoveryClient.getServices() = " + discoveryClient.getServices());
        // String res = restTemplate.getForObject("http://service-server/helloServer", String.class);
        String res = serverApi.helloFromServer();
        return "hello, 我是 server client, targetIns = " + targetIns + "我调用 server 返回的信息是: " + res;
    }

}