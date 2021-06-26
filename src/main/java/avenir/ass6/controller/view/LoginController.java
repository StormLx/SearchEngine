package avenir.ass6.controller.view;

import avenir.ass6.model.user.User;
import avenir.ass6.service.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    /**
     * User DAO Service.
     */
    private final UserDAO userDAO;
    /**
     * Login display.
     * @return view
     */
    @RequestMapping({"/", "login"})
    public String getLogin(final Principal principal) {
        if (principal != null) {
            final User verif = userDAO.findByUserName(currentUserName(principal));
            if (verif.getRoles().equals("USER")) {
                LOGGER.trace("Successfully logged in");
                return "redirect:/submit";
            }
        }
        return "login";
    }
    /**
     * All mapping redirect
     * @return view
     */
    @RequestMapping("*")
    public String def() {
        return "login";
    }

    /**
     * Get current username.
     * @return username
     */
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public static String currentUserName(final Principal principal) {
        return principal.getName();
    }
}
