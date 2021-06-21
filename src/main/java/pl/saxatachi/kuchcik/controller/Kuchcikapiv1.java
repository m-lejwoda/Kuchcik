package pl.saxatachi.kuchcik.controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.saxatachi.kuchcik.model.Post;
import pl.saxatachi.kuchcik.service.PostService;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/kuchcik/v1/")
public class Kuchcikapiv1 {
    private final PostService postservice;

    public Kuchcikapiv1(PostService postservice) {
        this.postservice = postservice;
    }

    @RequestMapping("/")
    public String home(){
        return "Hello Kuchcik1";
    }

}
