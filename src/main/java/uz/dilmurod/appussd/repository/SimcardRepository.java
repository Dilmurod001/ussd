package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.SimCard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SimcardRepository extends JpaRepository<SimCard, UUID> {
    boolean existsByCodeAndNumber(String code, String number);

    Optional<SimCard> findByCodeAndNumber(String code, String number);
    List<SimCard> findAllByActive(boolean active);

}
