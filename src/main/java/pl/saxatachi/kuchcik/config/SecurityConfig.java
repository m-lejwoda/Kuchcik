//package pl.saxatachi.kuchcik.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user1")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/swagger-ui").hasRole("USER")
//                .antMatchers("/").hasRole("USER")
//                .and().formLogin().and().logout().permitAll();
//    }
//}
