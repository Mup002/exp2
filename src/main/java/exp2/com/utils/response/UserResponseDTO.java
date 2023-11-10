package exp2.com.utils.response;

import exp2.com.dto.BookInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<BookInfoDTO> bookInfoDTO;

}
