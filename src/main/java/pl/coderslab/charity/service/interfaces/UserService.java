package pl.coderslab.charity.service.interfaces;


import pl.coderslab.charity.entity.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    User saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> findAllUsers();

    boolean existsByEmail(String email);

    User findByEmail(String email);

    String getPrincipal();

    User getCurrentUser();

}
