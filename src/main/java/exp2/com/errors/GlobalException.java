package exp2.com.errors;

import exp2.com.utils.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> handleResourceNotFound(ResourceNotFoundException ex){
        BaseResponse baseResponse = BaseResponse.builder()
                .code(String.format(HttpStatus.NOT_FOUND.toString()))
                .data(ex.getLocalizedMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<BaseResponse> emailDuplicated(DataDuplicateException ex){
        BaseResponse baseResponse = BaseResponse.builder()
                .code(String.format(HttpStatus.CONFLICT.toString()))
                .data(ex.getLocalizedMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SimilarBookException.class)
    public ResponseEntity<BaseResponse> similarBook(SimilarBookException ex){
        BaseResponse baseResponse = BaseResponse.builder()
                .code(String.format(HttpStatus.NOT_ACCEPTABLE.toString()))
                .data(ex.getLocalizedMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
