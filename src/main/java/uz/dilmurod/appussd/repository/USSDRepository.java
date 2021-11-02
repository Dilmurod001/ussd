package uz.dilmurod.appussd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.UssdCode;

public interface USSDRepository extends JpaRepository<UssdCode,Integer> {
}
