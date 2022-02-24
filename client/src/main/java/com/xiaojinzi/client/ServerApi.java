package com.xiaojinzi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-server")
public interface ServerApi {

    @RequestMapping(path = "/helloServer")
    String helloFromServer();

}
