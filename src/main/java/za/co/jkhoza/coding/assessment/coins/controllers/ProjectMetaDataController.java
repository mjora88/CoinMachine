package za.co.jkhoza.coding.assessment.coins.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectMetaDataController extends AbstractController {

    @GetMapping("/aboutProject")
    public String aboutPage() {

        return "about";
    }


}
