package exp2.com.service;

import exp2.com.model.Author;
import exp2.com.utils.request.AuthorRequestDTO;
import exp2.com.utils.response.AuthorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<AuthorResponseDTO> getAuthors();
    Optional<AuthorResponseDTO> getAuthor(Long id);
    AuthorResponseDTO save(AuthorRequestDTO dto);
    AuthorResponseDTO update(AuthorRequestDTO dto, Long id);
    AuthorResponseDTO delete(Long id);

    Author getAuthorById(Long id);

    AuthorResponseDTO addBookToAuthor(Long authorId, Long bookId);

    boolean isEmailDuplicated(String email);


}
