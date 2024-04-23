package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User newUser) {
        User newEntity = this.userRepository.save(newUser);
        System.out.println(newEntity);
        return newEntity;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll().;
    }

    public List<User> getAllUserByEmail(String email) {
        List<User> arrUsers = this.userRepository.findOneByEmail(email);
        // System.out.println(arrUsers);
        return arrUsers;
    }

    public User getAllUserById(long id) {
        User arrUsers = this.userRepository.findOneById(id);
        System.out.println(arrUsers);
        return arrUsers;
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

}
