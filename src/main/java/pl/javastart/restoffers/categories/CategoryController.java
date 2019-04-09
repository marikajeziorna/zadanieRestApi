package pl.javastart.restoffers.categories;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CategoryController {

    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/categories/names")
    public List<String> getAllCategories(){
        return categoryRepository.findAllNames();
    }



}
