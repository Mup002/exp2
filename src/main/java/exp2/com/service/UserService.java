package exp2.com.service;

import exp2.com.model.User;
import exp2.com.utils.request.UserRequestDTO;
import exp2.com.utils.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    List<UserResponseDTO> getUsers();
    Optional<UserResponseDTO> getUser(Long id);
    User getUserById(Long id);
    UserResponseDTO save(UserRequestDTO dto);
    UserResponseDTO update(UserRequestDTO dto, Long id);
    UserResponseDTO delete(Long id);

    UserResponseDTO addBookToUser(Long userId, Long bookId);

    UserResponseDTO removeBookFromUser(Long userId, Long bookId);

    boolean isDuplicatedEmail(String email);
}
