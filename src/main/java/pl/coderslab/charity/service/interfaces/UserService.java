package pl.coderslab.charity.service.interfaces;


import pl.coderslab.charity.entity.AppUser;

import java.util.List;

public interface UserService {

    AppUser findById(long id);

    AppUser saveUser(AppUser appUser);

    AppUser saveAdmin(AppUser appUser);

    void updateUser(AppUser appUser);

    void deleteUser(long id);

    List<AppUser> findAllUsers();

    List<AppUser> findAllByRoleEquals(String role);

    boolean existsByEmail(String email);

    AppUser findByEmail(String email);

    String getPrincipal();

    AppUser getCurrentUser();

}
