package exp2.com.controller;

import exp2.com.service.BookService;
import exp2.com.utils.request.BookRequestDTO;
import exp2.com.utils.response.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;

    @PostMapping("/save")
    private ResponseEntity<BookResponseDTO> save(@RequestBody BookRequestDTO dto){

        BookResponseDTO bookResponseDTO = bookService.save(dto);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/getBook/{id}")
    private ResponseEntity<Optional<BookResponseDTO>> getBook(@PathVariable("id")Long id){
        Optional<BookResponseDTO> bookResponseDTO = bookService.getBook(id);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/getBooks")
    private ResponseEntity<List<BookResponseDTO>> getBooks(){
        List<BookResponseDTO> bookResponseDTOS = bookService.getBooks();
        return new ResponseEntity<>(bookResponseDTOS, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<BookResponseDTO> update(@PathVariable("id")Long id, @RequestBody BookRequestDTO dto){
        BookResponseDTO bookResponseDTO = bookService.update(dto,id);
        return  new ResponseEntity<>(bookResponseDTO,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<BookResponseDTO> delete(@PathVariable("id")Long id){
        BookResponseDTO bookResponseDTO = bookService.delete(id);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }
}
