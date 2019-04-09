package pl.javastart.restoffers.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select name from category", nativeQuery = true)
    List<String> findAllNames();

}
