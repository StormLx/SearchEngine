package avenir.ass6.service;

import avenir.ass6.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
