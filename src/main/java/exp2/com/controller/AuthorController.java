package exp2.com.controller;

import exp2.com.service.AuthorService;
import exp2.com.utils.ValidateObject;
import exp2.com.utils.request.AuthorRequestDTO;
import exp2.com.utils.response.AuthorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    @Autowired
    private final AuthorService authorService;

//    @PostMapping("/create")
//    public ResponseEntity<Object> create(@RequestBody AuthorRequestDTO dto){
//        Map<String, String> errorValidator = ValidateObject.validateAuthorDTO(dto);
//        if(!ObjectUtils.isEmpty(errorValidator)){
//            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
//        }
//        AuthorResponseDTO authorResponseDTO = authorService.save(dto);
//
//        return new ResponseEntity<>(authorResponseDTO, HttpStatus.CREATED);
//    }

    @GetMapping("/getAuthors")
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors(){
        List<AuthorResponseDTO> authorResponseDTOS = authorService.getAuthors();

        return new ResponseEntity<>(authorResponseDTOS,HttpStatus.OK);
    }

    @GetMapping("/getAuthor/{id}")
    public ResponseEntity<Optional<AuthorResponseDTO>> getAuthor(@PathVariable("id")Long id){
        Optional<AuthorResponseDTO> authorResponseDTO = authorService.getAuthor(id);

        return new ResponseEntity<>(authorResponseDTO, HttpStatus.OK);
    }

//    @PutMapping("/update")
//    public ResponseEntity<Object> update(@RequestBody AuthorRequestDTO dto, @PathVariable("id")Long id){
//        Map<String, String> errorValidator = ValidateObject.validateAuthorDTO(dto);
//        if(!ObjectUtils.isEmpty(errorValidator)){
//            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
//        }
//        AuthorResponseDTO authorResponseDTO = authorService.update(dto, id);
//
//        return new ResponseEntity<>(authorResponseDTO, HttpStatus.OK);
//    }

    @PostMapping("/{authorId}/add/{bookId}")
    public ResponseEntity<AuthorResponseDTO> addBook(@PathVariable("authorId")Long authorId,
                                                     @PathVariable("bookId")Long bookId){
        AuthorResponseDTO authorResponseDTO = authorService.addBookToAuthor(authorId, bookId);

        return new ResponseEntity<>(authorResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        AuthorResponseDTO authorResponseDTO  = authorService.delete(id);

        return "success";
    }
}
