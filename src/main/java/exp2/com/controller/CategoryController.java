package exp2.com.controller;

import exp2.com.service.CategoryService;
import exp2.com.utils.ValidateObject;
import exp2.com.utils.request.CategoryRequestDTO;
import exp2.com.utils.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CategoryRequestDTO dto){
        Map<String, String> errorValidate = ValidateObject.validateCategory(dto);
        if(!ObjectUtils.isEmpty(errorValidate)){
            return new ResponseEntity<>(errorValidate, HttpStatus.BAD_REQUEST);
        }
        CategoryResponseDTO categoryResponseDTO = categoryService.save(dto);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<Optional<CategoryResponseDTO>> getCategory(@PathVariable("id")Long id){
        Optional<CategoryResponseDTO> categoryResponseDTO = categoryService.getCategory(id);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryResponseDTO>> getCategories(){
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.getCategories();

        return new ResponseEntity<>(categoryResponseDTOList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody CategoryRequestDTO dto, @PathVariable("id")Long id){
        Map<String, String> errorValidator = ValidateObject.validateCategory(dto);
        if(!ObjectUtils.isEmpty(errorValidator)){
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
        CategoryResponseDTO categoryResponseDTO = categoryService.update(dto, id);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        CategoryResponseDTO categoryResponseDTO = categoryService.delete(id);

        return "success";
    }

    @PostMapping("/{categoryId}/add/{bookId}")
    public ResponseEntity<CategoryResponseDTO> addBookToCategory(@PathVariable("categoryId")Long categoryId,
                                                                 @PathVariable("bookId")Long bookid){
        CategoryResponseDTO categoryResponseDTO = categoryService.addBookToCategory(categoryId,bookid);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{categoryId}/remove/{bookId}")
    public ResponseEntity<CategoryResponseDTO> removeBookFromCategory(@PathVariable("categoryId")Long categoryId,
                                                                      @PathVariable("bookId")Long bookId){
        CategoryResponseDTO categoryResponseDTO = categoryService.removeBookToCategory(categoryId,bookId);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }
}
