package avenir.ass6.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeniedController {
    /**
     * Access Denied Page
     * @return view
     */
    @RequestMapping("/accessDenied")
    public String deny() {
        return "accessDenied";
    }
}
