package uz.dilmurod.appussd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dilmurod.appussd.entity.SimCard;
import uz.dilmurod.appussd.entity.Tariff;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.repository.SimcardRepository;
import uz.dilmurod.appussd.repository.TariffRepository;
import uz.dilmurod.appussd.repository.TariffSimcardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    @Autowired
    SimcardRepository simcardRepository;

    @Autowired
    TariffSimcardRepository tariffSimcardRepository;

    @Autowired
    TariffRepository tariffRepository;

    public ApiResponse getActive() {
        List<SimCard> allByActive = simcardRepository.findAllByActive(true);
        return new ApiResponse("Active simCards",true,allByActive);
    }

    public ApiResponse findActiveTariff() {
        Integer activeTariff = tariffSimcardRepository.findActiveTariff();
        Optional<Tariff> tariffOptional = tariffRepository.findById(activeTariff);
        Tariff tariff = tariffOptional.get();
        String tariffName = tariff.getName();
        return new ApiResponse("Most using tariff",true,tariffName);
    }

    public ApiResponse findPassiveTariff() {
        Integer passiveTariff = tariffSimcardRepository.findPassiveTariff();
        Optional<Tariff> tariffOptional = tariffRepository.findById(passiveTariff);
        Tariff tariff = tariffOptional.get();
        String tariffName = tariff.getName();
        return new ApiResponse("Passive tariff",true,tariffName);

    }

//    public ApiResponse findActivePackets() {
//
//    }
}
