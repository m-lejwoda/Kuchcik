package pl.saxatachi.kuchcik.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.saxatachi.kuchcik.email.EmailCfg;
import pl.saxatachi.kuchcik.email.EmailSenderImpl;
import pl.saxatachi.kuchcik.email.Feedback;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final EmailCfg emailCfg;
    private final EmailSenderImpl emailSender;
    private final TemplateEngine templateEngine;

    public FeedbackController(EmailCfg emailCfg, EmailSenderImpl emailSender, TemplateEngine templateEngine) {
        this.emailCfg = emailCfg;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailCfg.getHost());
        mailSender.setPort(emailCfg.getPort());
        mailSender.setUsername(emailCfg.getUsername());
        mailSender.setPassword(emailCfg.getPassword());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("saxatachi@gmail.com");
        mailMessage.setSubject("New feedback from "+ feedback.getName());
        mailMessage.setText(feedback.getFeedback());
        mailSender.send(mailMessage);
    }
    @PostMapping("/registermail")
    public String send() {
        Context context = new Context();
        context.setVariable("header", "Nowy artykuł na CodeCouple");
        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
        context.setVariable("description", "Tutaj jakis opis...");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail("your.email.here@gmail.com", "CodeCouple Newsletter", body);
        return "index";
}
}
