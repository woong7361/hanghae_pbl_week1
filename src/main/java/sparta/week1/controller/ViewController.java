package sparta.week1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home.html";
    }

    @GetMapping("/view/{id}")
    public String view(){
        return "view.html";
    }
}
