package vaisselle.services;

import org.springframework.stereotype.Service;

import vaisselle.models.tables.User;
import vaisselle.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long idUser) {
        long idU = idUser;
        return userRepository.findById(idU).orElse(null);
    }
}
