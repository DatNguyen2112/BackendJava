package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    // Dependence Injection
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        this.userService.getAllUserByEmail("datnguyen21122001@gmail.com");
        model.addAttribute("dat", "test");
        return "index";
    }

    @RequestMapping("/admin/user")
    public String getAdminPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getDetailPage(Model model, @PathVariable long id) {
        User arrUserById = this.userService.getAllUserById(id);
        System.out.println("Detail User: " + arrUserById);
        model.addAttribute("detailUser", arrUserById);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String updateUser(Model model, @PathVariable long id) {
        User arrUserById = this.userService.getAllUserById(id);
        model.addAttribute("detailUser", arrUserById);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(@ModelAttribute("newUser") User newUser) {
        User currentUser = this.userService.getAllUserById(newUser.getId());

        if (currentUser != null) {
            currentUser.setAddress(newUser.getAddress());
            currentUser.setEmail(newUser.getEmail());
            currentUser.setPhone(newUser.getPhone());
            currentUser.setFullName(newUser.getFullName());
            this.userService.handleSaveUser(currentUser);
        }

        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/create")
    public String getCreatePage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createAdminPage(Model model, @ModelAttribute("newUser") User newUser) {
        this.userService.handleSaveUser(newUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getPageDelete(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("detailUser", new User());
        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(@ModelAttribute("newUser") User newUser) {
        this.userService.deleteUser(newUser.getId());
        return "redirect:/admin/user";
    }
}

// @RestController
// public class UserController {

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getHomePage() {
// return this.userService.getUserService();
// }
// }
