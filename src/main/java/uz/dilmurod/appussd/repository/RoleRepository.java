package uz.dilmurod.appussd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
