package exp2.com.service;

import exp2.com.model.Book;
import exp2.com.utils.request.BookRequestDTO;
import exp2.com.utils.response.BookResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    BookResponseDTO save(BookRequestDTO dto);
    List<BookResponseDTO> getBooks();
    Optional<BookResponseDTO> getBook(Long id);
    BookResponseDTO update(BookRequestDTO dto, Long id);
    BookResponseDTO delete(Long id);

    Book getBookById(Long id);
    Book savedBook(Book book);

    boolean isBookDuplicated(String name, Long number);
}
