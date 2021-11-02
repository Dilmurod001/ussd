package uz.dilmurod.appussd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.dilmurod.appussd.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
