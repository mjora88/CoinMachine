package za.co.jkhoza.coding.assessment.coins.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import za.co.jkhoza.coding.assessment.coins.model.User;

import java.util.List;

@Controller
public class SystemUserController extends AbstractController {

    @ModelAttribute("allSystemUsers")
    public List<User> getAllUsers() {
        return userService.findAllButNotCurrentUser(getCurrentUser());
    }


    @GetMapping("/manageusers")
    public String getManageUsersPage(Model model) {

        return "manageusers";
    }

    @GetMapping("/makeAdmin/{id}")
    public String makeAdmin(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);

        if (user != null) {
            userService.makeAdmin(id);
            model.addAttribute("updateM", user.getFirstName() + " has now been granted admin powers!");
        } else {
            model.addAttribute("errorM", "User with ID " + id + " could not be found!");
            return "manageusers";
        }

        return "redirect:/manageusers";
    }

    @GetMapping("/removeAdmin/{id}")
    public String removeAdmin(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);

        if (user != null) {
            userService.removeAdmin(id);
            model.addAttribute("updateM", user.getFirstName() + "'s Admin powers have been revoked and updated!");
        } else {
            model.addAttribute("errorM", "User with ID " + id + " could not be found!");
            return "manageusers";
        }

        return "redirect:/manageusers";
    }
}
