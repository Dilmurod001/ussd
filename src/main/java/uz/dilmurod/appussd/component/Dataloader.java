package uz.dilmurod.appussd.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.dilmurod.appussd.entity.Filial;
import uz.dilmurod.appussd.entity.Role;
import uz.dilmurod.appussd.entity.Staff;
import uz.dilmurod.appussd.entity.enums.Permission;
import uz.dilmurod.appussd.repository.FilialRepository;
import uz.dilmurod.appussd.repository.RoleRepository;
import uz.dilmurod.appussd.repository.StaffRepository;
import uz.dilmurod.appussd.repository.UserRepository;
import uz.dilmurod.appussd.util.Constants;

import java.util.Arrays;
import java.util.Collections;

import static uz.dilmurod.appussd.entity.enums.Permission.*;

@Component
public class Dataloader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FilialRepository filialRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    RoleRepository roleRepository;



    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        Permission[] values = values();
        if (initMode.equals("always")) {
            Staff director = staffRepository.save(new Staff("Director", "director", "director"));
            Staff staff = staffRepository.save(new Staff("Xodim", "xodim", "xodim"));

            filialRepository.save(new Filial("PDP",Collections.singletonList(staff),director));

             roleRepository.save(new Role(Constants.DIRECTOR,
                    Arrays.asList(values)));
             roleRepository.save(new Role(Constants.USER,
                    Arrays.asList(GET_SIMCARD)));

        }
    }
}
