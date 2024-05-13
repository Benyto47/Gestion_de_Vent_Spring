package com.j2ee.gestionVente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    @GetMapping("/") /*va nous amener a notre vue index.html*/
    public RedirectView index(RedirectAttributes attributes) {
        return new RedirectView("index.html");
    }
}
