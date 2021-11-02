package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.Staff;

import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, UUID> {

}
