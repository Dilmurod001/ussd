package uz.dilmurod.appussd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.payload.PakaketDTO;
import uz.dilmurod.appussd.repository.PacketRepository;
import uz.dilmurod.appussd.service.PacketService;

@RestController
@RequestMapping("/api/packet")
public class PacketController {
    @Autowired
    PacketService packetService;

    @PostMapping
    public HttpEntity<?> addPacket(@RequestBody PakaketDTO packageDTO) {
        ApiResponse apiResponse = packetService.add(packageDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

}
