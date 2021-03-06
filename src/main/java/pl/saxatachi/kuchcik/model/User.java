package pl.saxatachi.kuchcik.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@Entity
//@Getter
//@Setter
//@Table(name="users")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {

    @SequenceGenerator(name = "user_sequence"
            ,sequenceName = "user_sequence"
            ,allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator = "user_sequence")

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    @OneToMany
    @JoinColumn(name = "userId")
    private List<Address> address;

    public User(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    //    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(name="id")
//    private Long id;
//    @Column(name="name")
//    private String name;
//    @Column(name="lastname")
//    private String lastname;
//    @Column(name="username")
//    private String username;
//    @Column(name="password")
//    private String password;
//    @Column(name="enabled")
//    private String enabled;
//    @Column(name="email")
//    private  String email;
}
