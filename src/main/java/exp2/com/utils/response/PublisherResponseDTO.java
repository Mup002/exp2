package exp2.com.utils.response;

import exp2.com.model.Author;
import exp2.com.model.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PublisherResponseDTO {
    private Long id;
    private String name;
    private String city;
    private List<Author> authors = new ArrayList<>();
    private String code;
}
