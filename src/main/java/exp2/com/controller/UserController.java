package exp2.com.controller;

import exp2.com.model.User;
import exp2.com.service.UserService;
import exp2.com.utils.ValidateObject;
import exp2.com.utils.request.UserRequestDTO;
import exp2.com.utils.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody UserRequestDTO dto){
        Map<String, String> errorValidator = ValidateObject.validateUserDTO(dto);
        if(!ObjectUtils.isEmpty(errorValidator)){
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
        UserResponseDTO userResponseDTO = userService.save(dto);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> getUser(@PathVariable("id")Long id){
        Optional<UserResponseDTO> userResponseDTO =userService.getUser(id);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);

    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        List<UserResponseDTO> userResponseDTOList = userService.getUsers();
        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@PathVariable("id")Long id, @RequestBody UserRequestDTO dto){
        Map<String, String> errorValidator = ValidateObject.validateUserDTO(dto);
        if(!ObjectUtils.isEmpty(errorValidator)){
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
        UserResponseDTO userResponseDTO = userService.update(dto,id);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        UserResponseDTO userResponseDTO = userService.delete(id);
        return  "success";
    }

    @PostMapping("/{userId}/To/{bookId}")
    public ResponseEntity<UserResponseDTO> addBookToUser(@PathVariable("userId")Long userId,
                                                         @PathVariable("bookId")Long bookId){
        UserResponseDTO userResponseDTO = userService.addBookToUser(userId,bookId);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{userId}/From/{bookId}")
    public ResponseEntity<UserResponseDTO> removeBookFromUser(@PathVariable("userId")Long userId,
                                                              @PathVariable("bookId")Long bookId){
        UserResponseDTO userResponseDTO = userService.removeBookFromUser(userId, bookId);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
