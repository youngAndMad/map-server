package danekerscode.akbulakserver.controller;

import danekerscode.akbulakserver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping("new")
    public ResponseEntity<?> save(@RequestParam("name") String name) {
        categoryService.save(name);
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Integer id) {
        this.categoryService.delete(id);
    }

}
