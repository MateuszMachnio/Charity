package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.AppUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByEmail(String email);

    AppUser findByEmail(String email);

    List<AppUser> findAllByRoleEquals(String role);

}
