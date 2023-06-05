package danekerscode.akbulakserver.service;

import danekerscode.akbulakserver.model.Category;
import danekerscode.akbulakserver.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository; //  injecting for operations with db

    public List<Category> getAll(){  // get all exists categories
        return categoryRepository.findAll();
    }

    public void save(String name){
        categoryRepository.save(new Category(name));
    } // save new type of category

    public void delete(Integer id){ // deleting by id
        categoryRepository.deleteById(id);
    }

    public List<Category> saveAll(List<String> categoryStringList){
        categoryStringList.stream()
                .filter(c -> categoryRepository.findByName(c).isEmpty()) // if request body bas a unsaved types of category
                .forEach(this::save);  // this line will save unsaved categories

       return new ArrayList<>(){{
            categoryStringList.forEach(s -> add(categoryRepository.findByName(s).orElseThrow()));
        }};

    }


}
