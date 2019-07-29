package warsztaty.spring.ailleron.service;

import org.springframework.stereotype.Service;
import warsztaty.spring.ailleron.exception.UserAlreadyExistsException;
import warsztaty.spring.ailleron.exception.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByName(final String name){
        return userRepository.findUserByName(name);
    }

    private Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        Optional<User> userByName = getUserByName(user.getName());
        if (userByName.isPresent()){
            throw new UserAlreadyExistsException("User with name: " + user.getName() + " is already in database");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void  modifyUser(User user, Long id){
        Optional<User> userByID = getUserById(id);
        if(!userByID.isPresent()){
            throw new UserNotFoundException("User with id: " + user.getId() + "not found.");
        }
        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User userByID = getUserById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
        userRepository.deleteById(id);
    }
}
