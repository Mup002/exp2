package exp2.com.utils;

import exp2.com.utils.request.AuthorRequestDTO;
import exp2.com.utils.request.CategoryRequestDTO;
import exp2.com.utils.request.UserRequestDTO;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {
    public static Map<String, String> validateAuthorDTO(UserRequestDTO dto){
        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                .fieldName("name")
                .value(dto.getName())
                .required(true)
                        .min(Long.valueOf(3))
                        .max(Long.valueOf(20))
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("email")
                .value(dto.getEmail())
                .required(true)
                .isValidEmail(true)
                .build().validate());



        return errors;
    }

    public static Map<String , String> validateUserDTO(UserRequestDTO dto){
        Map<String, String> errors=  new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                        .fieldName("name")
                        .value(dto.getName())
                        .required(true)
                .build().validate()
        );

        errors.putAll(ValidateUtils.builder()
                .fieldName("email")
                        .value(dto.getEmail())
                        .isValidEmail(true)
                        .required(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                        .fieldName("password")
                        .value(dto.getPassword())
                        .required(true)
                .build().validate());

        return errors;
    }

    public static Map<String, String> validateCategory(CategoryRequestDTO dto){
        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                        .fieldName("name")
                        .value(dto.getName())
                        .required(true)
                .build().validate());

        return errors;
    }
}
