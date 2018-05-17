package za.co.jkhoza.coding.assessment.coins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.jkhoza.coding.assessment.coins.controllers.dto.UserRegistrationDto;
import za.co.jkhoza.coding.assessment.coins.model.Role;
import za.co.jkhoza.coding.assessment.coins.model.User;
import za.co.jkhoza.coding.assessment.coins.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));

        //this is the logic that makes sure that the first user is admin!
        if (userRepository.findAll().isEmpty()) {
            user.setRoles(Arrays.asList(new Role("ROLE_USER"), new Role("ADMIN")));
        } else {
            user.setRoles(Arrays.asList(new Role("ROLE_USER")));

        }

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllButNotCurrentUser(User currentUser) {
        return userRepository.findAll().stream().filter(user -> !Objects.equals(user.getId(), currentUser.getId())).collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void makeAdmin(Long id) {
        User user = findById(id);
        Role role = new Role();
        role.setName("ADMIN");
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    public void removeAdmin(Long id) {
        User user = findById(id);
        Role role = new Role();
        role.setName("ADMIN");
        user.getRoles().remove(role);
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
