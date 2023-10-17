package com.ven2see.springth2see.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ven2see.springth2see.model.Post;
import com.ven2see.springth2see.model.ServiceResponse;
import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.service.IUserService;
import com.ven2see.springth2see.service.PostService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private PostService pservice;

    private void setUserDataAttributes(Model model) {
        List<User> result = iUserService.findAll();
        User user = iUserService.getAdmin(1);
        List<Post> posts = pservice.getAllPost();

        model.addAttribute("admin", user);
        model.addAttribute("users", result);
        model.addAttribute("posts", posts);
    }

    @GetMapping({ "/index", "/" })
    public String mainPage(Model model, Principal prin) {
        if (iUserService.getAdmin(1) != null) {
            setUserDataAttributes(model);
            if (prin != null) {
                model.addAttribute("isLoggedIn", true);
            } else {
                model.addAttribute("isLoggedIn", false);
            }
        } else {

            model.addAttribute("admin", null);
            model.addAttribute("users", null);
        }
        model.addAttribute("post", new Post());
        return "index";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        setUserDataAttributes(model);
        return "/admin/index";
    }

    @GetMapping("/signup")
    public String formRegis(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/admin/users")
    public String userTable(Model model) {
        List<User> result = iUserService.findAll();
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("users", result);
        return "/admin/usertable";
    }

    @PostMapping("/admin/users/save")
    public String save(@ModelAttribute("user") User user) {
        iUserService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/signup")
    public String sign_up(@ModelAttribute("user") User user,Model model) {
        Boolean val = iUserService.usernameExists(user.getUsername());
        
        System.out.println(val);
        if(!val){
            iUserService.save(user);
            return "redirect:/";
        }else{
            
        System.out.println(val);
            return "redirect:/signup?error=true";
        }
    }

    @PostMapping("/update")
    public String update(User user, Model model) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iUserService.update(user);
        if (result == 1) {
            serviceResponse.setMessage("Art has been updated");
        }
        model.addAttribute("serviceResponse", serviceResponse);
        return "update";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iUserService.deleteById(id);
        if (result == 1) {
            serviceResponse.setMessage("Art has been removed");
        }
        model.addAttribute("serviceResponse", serviceResponse);
        return "redirect:/admin/users";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Realiza cualquier operación de cierre de sesión necesaria aquí
        // Puedes invalidar la sesión y redirigir a la página de inicio, por ejemplo
        request.getSession().invalidate();
        return "redirect:/";
    }
}
