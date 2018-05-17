package za.co.jkhoza.coding.assessment.coins.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import za.co.jkhoza.coding.assessment.coins.controllers.dto.UserRegistrationDto;
import za.co.jkhoza.coding.assessment.coins.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    List<User> findAllButNotCurrentUser(User currentUser);

    User findById(Long id);

    void makeAdmin(Long id);

    void removeAdmin(Long id);
}
