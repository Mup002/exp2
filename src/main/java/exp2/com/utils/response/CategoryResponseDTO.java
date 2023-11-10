package exp2.com.utils.response;

import exp2.com.dto.BookInfoDTO;
import exp2.com.model.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<BookInfoDTO> bookInfoDTO = new ArrayList<>();
}
