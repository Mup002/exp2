package exp2.com.utils.request;

import lombok.Data;

import java.util.List;

@Data
public class CategoryRequestDTO {
    private String name;
    private List<Long> bookIds;
}
