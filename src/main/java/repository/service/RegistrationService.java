package repository.service;

import entity.user.User;
import mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by guilherme on 11/03/16.
 * Service for Registering logic
 */
@Service
public class RegistrationService extends AbstractRepositoryService<UserRepository> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public String generateActivationKey(){
        return UUID.randomUUID().toString();
    }

    public void sendConfirmationEmailToUser(User user){
        final String key = (user.getActivateKey().split("-"))[4];
        Map<String,String> attributes = new HashMap<>();
        attributes.put("message","localhost:9090/register/confirm?k="+key);
        emailService.sendEmail(user,attributes);
    }
}