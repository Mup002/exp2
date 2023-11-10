package exp2.com.utils.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorRequestDTO {
    private String name;
    private String email;
    private String phone;
    private List<Long> bookIds;
    private Long publisherId;
}
