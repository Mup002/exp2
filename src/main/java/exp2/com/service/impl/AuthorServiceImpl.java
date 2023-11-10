package exp2.com.service.impl;

import exp2.com.errors.DataDuplicateException;
import exp2.com.errors.ResourceNotFoundException;
import exp2.com.model.Author;
import exp2.com.model.Book;
import exp2.com.repository.AuthorRepository;
import exp2.com.service.AuthorService;
import exp2.com.service.BookService;
import exp2.com.utils.mapper;
import exp2.com.utils.request.AuthorRequestDTO;
import exp2.com.utils.response.AuthorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;



@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final BookService bookService;
    @Override
    public List<AuthorResponseDTO> getAuthors() {
        return mapper.authorToAuthorResponses(StreamSupport.stream(authorRepository.findAll().spliterator(),false).collect(Collectors.toList()));
    }

    @Override
    public Optional<AuthorResponseDTO> getAuthor(Long id) {
        authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author not found by id: " + id));
        return Optional.of(mapper.authorToAuthorResponse(authorRepository.findById(id).get()));
    }

    @Override
    public AuthorResponseDTO save(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        if(isEmailDuplicated(dto.getEmail())){
            throw new DataDuplicateException("Email has already exists");
        }else{

            author.setEmail(dto.getEmail());
        }
        author.setPhone(dto.getPhone());
        return mapper.authorToAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponseDTO update(AuthorRequestDTO dto, Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found by id: "+ id));
        author.setName(dto.getName());
        author.setEmail(dto.getEmail());
        author.setPhone(dto.getPhone());
        return mapper.authorToAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponseDTO delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found by id: "+ id));
        authorRepository.delete(author);
        return mapper.authorToAuthorResponse(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Author not found by id: "+ id));
        return author;
    }

    @Override
    public AuthorResponseDTO addBookToAuthor(Long authorId, Long bookId) {
        Author author = getAuthorById(authorId);
        Book book = bookService.getBookById(bookId);
        author.AddBook(book);
        book.setAuthor(author);
        bookService.savedBook(book);
        return mapper.authorToAuthorResponse(author);
    }

    @Override
    public boolean isEmailDuplicated(String email) {
        List<Author> authors = authorRepository.findAll();
        for(Author author : authors){
            if(email.equals(author.getEmail())){
                return true;
            }
        }
        return false;

    }
}
