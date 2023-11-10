package exp2.com.utils.request;

import lombok.Data;

import java.util.List;

@Data
public class PublisherRequestDTO {
    private String name;
    private String city;
    private List<Long> authorIds;
    private Long zipcodeId;
}
