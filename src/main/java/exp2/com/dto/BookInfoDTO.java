package exp2.com.dto;

import lombok.Data;

@Data
public class BookInfoDTO {
    private String bookName ;
    private Long bookNumber;

    private Long bookAmount;
    public BookInfoDTO (String bookName, Long bookNumber) {
        this.bookName = bookName;
        this.bookNumber = bookNumber;
    }

    public BookInfoDTO(String bookName, Long bookNumber, Long bookAmount){
        this.bookName = bookName;
        this.bookNumber = bookNumber;
        this.bookAmount = bookAmount;
    }

}
