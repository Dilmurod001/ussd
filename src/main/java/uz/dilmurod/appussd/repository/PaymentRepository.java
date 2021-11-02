package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.dilmurod.appussd.entity.Payment;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findAllByNumber(String number);
    double countPaymentsByAmountAndDateBetween(double amount, Date date, Date date2);

}
