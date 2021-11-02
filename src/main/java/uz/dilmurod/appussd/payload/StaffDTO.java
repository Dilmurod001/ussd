package uz.dilmurod.appussd.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.dilmurod.appussd.entity.enums.Position;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    private String fullName;
    private String userName;
    private String password;
    private Position position;
    private UUID filialId;

}
