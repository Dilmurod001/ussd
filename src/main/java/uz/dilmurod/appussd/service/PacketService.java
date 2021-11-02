package uz.dilmurod.appussd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dilmurod.appussd.entity.Packet;
import uz.dilmurod.appussd.entity.Tariff;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.payload.PakaketDTO;
import uz.dilmurod.appussd.repository.PacketRepository;
import uz.dilmurod.appussd.repository.TariffRepository;

import java.util.Date;
import java.util.List;

@Service
public class PacketService {
    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    PacketRepository packetRepository;

    public ApiResponse add(PakaketDTO pakaketDTO) {
        Packet packet = new Packet();
        packet.setAmount(pakaketDTO.getAmount());
        packet.setDueDate(pakaketDTO.getDueDate());
        packet.setPrice(pakaketDTO.getPrice());
        packet.setExpiredDate(new Date(System.currentTimeMillis() + (long) pakaketDTO.getDueDate() * 86400 * 1000));
        packet.setType(pakaketDTO.getType());
        List<Tariff> allById = tariffRepository.findAllById(pakaketDTO.getTariffList());
        packet.setTariffs(allById);
        packetRepository.save(packet);
        return new ApiResponse("Success", true);
    }

}
