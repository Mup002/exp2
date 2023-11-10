package exp2.com.service.impl;

import exp2.com.errors.DataDuplicateException;
import exp2.com.errors.ResourceNotFoundException;
import exp2.com.model.Book;
import exp2.com.model.User;
import exp2.com.repository.UserRepository;
import exp2.com.service.BookService;
import exp2.com.service.UserService;
import exp2.com.utils.mapper;
import exp2.com.utils.request.UserRequestDTO;
import exp2.com.utils.response.UserResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookService bookService;
    @Override
    public List<UserResponseDTO> getUsers() {
        return mapper.userToUserResponses(StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserResponseDTO> getUser(Long id) {
        return Optional.of(mapper.userToUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id))));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        User user  = new User();
        user.setName(dto.getName());
        if(isDuplicatedEmail(dto.getEmail())){
            throw new DataDuplicateException("Email has already exists");
        }else {
            user.setEmail(dto.getEmail());
        }
        user.setPassword(dto.getPassword());
        return mapper.userToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO update(UserRequestDTO dto, Long id) {
        User user =  userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id: " + id)
        );
        user.setName(dto.getName());
        if(isDuplicatedEmail(dto.getEmail())){
            throw new DataDuplicateException("Email has already exists");
        }else {
            user.setEmail(dto.getEmail());
        }
        user.setPassword(dto.getPassword());
        return mapper.userToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return mapper.userToUserResponse(user);
    }
    @Transactional
    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        return user;
    }
    @Transactional
    @Override
    public UserResponseDTO addBookToUser(Long userId, Long bookId) {
        Book book = bookService.getBookById(bookId);
        User user = getUserById(userId);
        user.AddBook(book);
        book.AddUser(user);
        return mapper.userToUserResponse(user);
    }
    @Transactional
    @Override
    public UserResponseDTO removeBookFromUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId).get();
        Book book = bookService.getBookById(bookId);
        user.RemoveBook(book);
        book.RemoveUser(user);
        return mapper.userToUserResponse(user);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(email.equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }
}
