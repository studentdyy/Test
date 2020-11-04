package learnjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    @Autowired
    MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    private List<User> users = new ArrayList<>(List.of(
            new User(1,"bob@example.com","password","Bob"),
            new User(2,"alice@example.com","123","Alice"),
            new User(3,"tom@example.com","123","Tom")));

    public User login(String email,String password){
        for(User user : users){
            if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)){
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed");
    }

    public User getUser(long id){
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    @MetricTime("register")
    public User register(String email,String password,String name){
        users.forEach((user) ->{
            if(user.getEmail().equalsIgnoreCase(email)){
                throw new RuntimeException("email exist.");
            }
        });
        User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong()+1,email,password,name);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}