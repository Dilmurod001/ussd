package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.Tariff;

import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
    boolean existsByName(String name);

    Optional<Tariff> findByName(String name);

    void deleteByName(String name);
}
