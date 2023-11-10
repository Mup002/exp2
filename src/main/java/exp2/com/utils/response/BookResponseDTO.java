package exp2.com.utils.response;

import exp2.com.dto.UserInfoDTO;
import exp2.com.model.Author;
import exp2.com.model.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookResponseDTO {
    private Long id;
    private String name;
    private Long number;
    private Long amount;
    private List<UserInfoDTO> userInfoDTO = new ArrayList<>();
    private String authorName;
    private String categoryName;
}
