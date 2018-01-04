package com.mealer.mealer;

import com.mealer.mealer.Model.Users;
//import com.mealer.mealer.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @RequestMapping("/")
    public String index() {
        return "Mealer";
    }

}
//@RestController
//@EnableAutoConfiguration
//public class Controller {
//
//    @Autowired
//    private UserService userService;
//
//    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
//
//    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, String> getHome() {
//        logger.info("Api request received");
//
//        Map<String, String> response = new HashMap<String, String>();
//        try {
//            Users users = new Users("ala","ma","kota");
//            userService.create(users);
//            response.put("status", "success");
//        } catch (Exception e) {
//            logger.error("Error occurred while trying to process api request", e);
//            response.put("status", "fail");
//        }
//
//        return response;
//    }
//}