package pl.javastart.restoffers.categories;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    @GetMapping("/api/categories")
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }

    @PostMapping("/api/categories")
    public void addCategory(@RequestBody CategoryDto categoryDto) {
      Category category = new Category(categoryDto.getName(), categoryDto.getDescription());
      categoryRepository.save(category);
    }

    @GetMapping("/api/categories/names")
    public List<String> getAllCategory() {
        return categoryRepository.findAllNames();
    }

    @DeleteMapping("/api/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(id);
        if(categoryRepositoryById.isPresent()){
            categoryRepository.delete(categoryRepositoryById.get());
        }
    }

}
