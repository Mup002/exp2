package exp2.com.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.CONFLICT)
public class DataDuplicateException extends RuntimeException{
    private String message;

    public DataDuplicateException(String message){
        this.message =  message;
    }
}
