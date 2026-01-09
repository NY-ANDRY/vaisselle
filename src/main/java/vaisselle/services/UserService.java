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
        return userRepository.findById(idUser).orElse(null);
    }

    public User updateUser(User user) {
        User original = userRepository.findById(user.getId()).orElseThrow();
        if (user.getImg() == null || user.getImg().isEmpty()) {
            user.setImg(original.getImg());
        }
        return userRepository.save(user);
    }
}
