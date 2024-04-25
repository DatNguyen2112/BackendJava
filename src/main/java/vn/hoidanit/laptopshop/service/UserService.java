package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.DTO.RegisterDTO;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User handleSaveUser(User newUser) {
        User newEntity = this.userRepository.save(newUser);
        System.out.println(newEntity);
        return newEntity;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
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

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public User transformToAnotherObj(RegisterDTO registerDTO) {
        User userDTO = new User();
        userDTO.setFullName(registerDTO.getFirstName() + registerDTO.getLastName());
        userDTO.setEmail(registerDTO.getEmail());
        userDTO.setPassword(registerDTO.getPassword());
        return userDTO;
    }

    public boolean checkEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
