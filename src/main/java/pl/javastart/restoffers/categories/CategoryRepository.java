package pl.javastart.restoffers.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select category.name from Category category")
    List<String> findAllNames();

    Category findByName(String name);
}
