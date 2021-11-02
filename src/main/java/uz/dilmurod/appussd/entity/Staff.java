package uz.dilmurod.appussd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.dilmurod.appussd.entity.enums.Position;
import uz.dilmurod.appussd.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends AbsEntity  {

    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Position position;
    private String tourniquet = "ID" + UUID.randomUUID().toString().substring(0, 8);

    @ManyToOne(fetch = FetchType.LAZY)
    private Filial filial;

    public Staff(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

}
