package pl.javastart.restoffers.categories;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

//    TODO count category number
//    @GetMapping("/api/categories/count")
//    public Long getCategoryNumber(){
//        return categoryRepository.count();
//    }


    @PostMapping("/api/categories")
    @ResponseBody
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
            bodyBuilder.build();
        }
        Category save = categoryRepository.save(category);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/api/categories/names")
    public List<String> getAllCategory() {
        return categoryRepository.findAllNames();
    }

    @DeleteMapping("/api/categories/{id}")
    public void deleteOffer(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }

}
