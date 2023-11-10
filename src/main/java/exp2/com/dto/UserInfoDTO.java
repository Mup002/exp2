package exp2.com.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String name;
    private String email;

    public UserInfoDTO(String name, String email){
        this.name = name;
        this.email = email;
    }
}
