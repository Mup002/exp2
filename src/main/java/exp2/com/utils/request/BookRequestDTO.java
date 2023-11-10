package exp2.com.utils.request;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDTO {
    private String name;
    private Long number;
    private Long amount;
    private List<Long> userIds;
    private Long authorId;
    private Long categoryId;
}
