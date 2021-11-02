package uz.dilmurod.appussd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dilmurod.appussd.entity.SimCard;
import uz.dilmurod.appussd.entity.TariffSimcard;
import uz.dilmurod.appussd.entity.UssdCode;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.payload.USSDDTO;
import uz.dilmurod.appussd.repository.SimcardRepository;
import uz.dilmurod.appussd.repository.TariffSimcardRepository;
import uz.dilmurod.appussd.repository.USSDRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class USSDService {
    @Autowired
    USSDRepository ussdRepository;
   @Autowired
    SimcardRepository simcardRepository;
   @Autowired
    TariffSimcardRepository tariffSimcardRepository;


    //
    public ApiResponse getBalance(UUID id){
        Optional<SimCard> byId = simcardRepository.findById(id);

        SimCard simCard = byId.get();
        return new ApiResponse("success",true,simCard.getBalance() );


    }

    //mb  va daq
    public ApiResponse getMb(UUID id){
        Optional<SimCard> byId = simcardRepository.findById(id);
        SimCard simCard = byId.get();
        Optional<TariffSimcard> bySimCard_id = tariffSimcardRepository.findBySimCard_Id(id);

        return new ApiResponse("success",true,Arrays.asList(bySimCard_id.get().getLeftOverDAQ(),bySimCard_id.get().getLeftOverMB()));
    }

    //all code

}
