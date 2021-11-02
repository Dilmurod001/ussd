package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.Filial;

import java.util.UUID;

public interface FilialRepository extends JpaRepository<Filial, UUID> {

}
