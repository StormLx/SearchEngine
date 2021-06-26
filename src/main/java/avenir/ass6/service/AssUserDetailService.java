package avenir.ass6.service;

import avenir.ass6.model.user.AssUserDetails;
import avenir.ass6.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssUserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User u = userDAO.findByUserName(userName);
        if (u == null) {
            throw new UsernameNotFoundException("Not found: " + userName);
        }
        return new AssUserDetails(u);
    }

}
