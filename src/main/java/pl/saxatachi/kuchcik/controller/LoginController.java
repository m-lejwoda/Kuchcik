package pl.saxatachi.kuchcik.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.saxatachi.kuchcik.login.LoginCredentials;


@RestController
public class LoginController {
    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials){
    }
}
