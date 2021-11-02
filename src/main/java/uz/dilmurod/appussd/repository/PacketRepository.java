package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.Packet;

public interface PacketRepository extends JpaRepository<Packet, Integer> {

}
