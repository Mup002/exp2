package exp2.com.utils.response;

import exp2.com.model.Book;
import exp2.com.model.Publisher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<String> bookNames =  new ArrayList<>();
    private Publisher publisher;
}
