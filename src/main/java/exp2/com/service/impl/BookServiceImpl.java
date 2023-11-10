package exp2.com.service.impl;

import exp2.com.errors.ResourceNotFoundException;
import exp2.com.errors.SimilarBookException;
import exp2.com.model.Book;
import exp2.com.repository.BookRepository;
import exp2.com.service.BookService;
import exp2.com.utils.mapper;
import exp2.com.utils.request.BookRequestDTO;
import exp2.com.utils.response.BookResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Override
    public BookResponseDTO save(BookRequestDTO dto) {
        Book book = new Book();
        if(isBookDuplicated(dto.getName(), dto.getNumber())){
            throw new SimilarBookException("A book has already");
        }else{
            book.setName(dto.getName());
            book.setNumber(dto.getNumber());
        }
        book.setAmount(dto.getAmount());
        return mapper.bookToBookResponse(bookRepository.save(book));
    }

    @Override
    public List<BookResponseDTO> getBooks() {
        List<Book> books = StreamSupport.stream(bookRepository.findAll().spliterator(),false).collect(Collectors.toList());
        return mapper.bookToResponses(books);
    }

    @Override
    public Optional<BookResponseDTO> getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with id: " + id));
        return Optional.of(mapper.bookToBookResponse(book));
    }

    @Override
    public BookResponseDTO update(BookRequestDTO dto, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with id: " + id));
        if(isBookDuplicated(dto.getName(), dto.getNumber())){
            throw new SimilarBookException("A book has already");
        }else{
            book.setName(dto.getName());
            book.setNumber(dto.getNumber());
        }
        book.setAmount(dto.getAmount());

        return mapper.bookToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
        return mapper.bookToBookResponse(book);
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book not found with id: " + id));
        return book;
    }

    @Override
    public Book savedBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public boolean isBookDuplicated(String name, Long number) {
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            if(name.equals(book.getName())&& number.equals(book.getNumber())){
                return true;
            }
        }
        return false;
    }

}
