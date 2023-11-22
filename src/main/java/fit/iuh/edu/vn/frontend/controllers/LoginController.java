package fit.iuh.edu.vn.frontend.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/user/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/Loginform")
    public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        if ("phuvan@gmail.com".equals(email) && "123".equals(password)) {
            return "redirect:/products";
        }
        else if ("admin@gmail.com".equals(email) && "123".equals(password)) {
            return "redirect:/admin/home";
        }
        else {
            return "redirect:/user/login?error";
        }
    }

}
