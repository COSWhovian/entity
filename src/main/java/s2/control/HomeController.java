package s2.control;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by russl on 11/28/2016.
 */
@RestController
@EnableAutoConfiguration
public class HomeController {

    @RequestMapping("/")
    public String index(){
        System.out.println("Home Page");
        return "index";
    }
}