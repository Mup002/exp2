package exp2.com.utils;

import exp2.com.dto.BookInfoDTO;
import exp2.com.dto.UserInfoDTO;
import exp2.com.model.*;
import exp2.com.utils.response.*;

import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static BookResponseDTO bookToBookResponse(Book book){
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setName(book.getName());
        bookResponseDTO.setNumber(book.getNumber());
        bookResponseDTO.setAmount(book.getAmount());
        List<UserInfoDTO> userInfoDTO = new ArrayList<>();
        if(!book.getUsers().isEmpty()){
            for(User user : book.getUsers()){
                UserInfoDTO userInfo = new UserInfoDTO(user.getName(), user.getEmail());
                userInfoDTO.add(userInfo);
            }
        }
        bookResponseDTO.setUserInfoDTO(userInfoDTO);
        if(book.getCategory() != null && book.getCategory().getName() != null){
            bookResponseDTO.setCategoryName(book.getCategory().getName());
        }
        if(book.getAuthor() != null && book.getAuthor().getName() != null){
            bookResponseDTO.setAuthorName(book.getAuthor().getName());
        }
        return bookResponseDTO;
    }

    public static List<BookResponseDTO> bookToResponses(List<Book> books){
        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        for(Book book : books){
            bookResponseDTOS.add(bookToBookResponse(book));
        }
        return bookResponseDTOS;
    }

    public static UserResponseDTO userToUserResponse(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPassword(user.getPassword());
        List<BookInfoDTO> bookInfoList = new ArrayList<>();
        if(!user.getBooks().isEmpty()){
            for(Book book : user.getBooks()) {
                BookInfoDTO bookInfoDTO = new BookInfoDTO(book.getName(), book.getNumber());
                bookInfoList.add(bookInfoDTO);
            }
        }
        userResponseDTO.setBookInfoDTO(bookInfoList);
        return userResponseDTO;
    }

    public static List<UserResponseDTO> userToUserResponses(List<User> users){
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for(User user : users){
            userResponseDTOList.add(userToUserResponse(user));
        }

        return userResponseDTOList;
    }

    public static List<CategoryResponseDTO> categoryToCategoryResponses(List<Category> categories){
       List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
       for(Category category : categories){
           categoryResponseDTOList.add(mapper.categoryToCategoryResponse(category));
       }

       return categoryResponseDTOList;
    }
    public static CategoryResponseDTO categoryToCategoryResponse(Category category){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        List<BookInfoDTO> bookList = new ArrayList<>();
        if(!category.getBooks().isEmpty()){
            for(Book book : category.getBooks()){
                BookInfoDTO bookInfoDTO = new BookInfoDTO(book.getName(), book.getNumber(), book.getAmount());
                bookList.add(bookInfoDTO);
            }
        }
        categoryResponseDTO.setBookInfoDTO(bookList);

        return categoryResponseDTO;
    }
    public static PublisherResponseDTO publisherToPublisher(Publisher publisher){
        PublisherResponseDTO publisherResponseDTO = new PublisherResponseDTO();
        publisherResponseDTO.setId(publisher.getId());
        publisherResponseDTO.setName(publisher.getName());
        publisherResponseDTO.setCity(publisher.getCity());
        List< Author> authorList = new ArrayList<>();
        if(!publisher.getAuthors().isEmpty()){
            for(Author author : publisher.getAuthors()){
                authorList.add(author);
            }
        }
        publisherResponseDTO.setAuthors(authorList);

        return publisherResponseDTO;

    }

    public static AuthorResponseDTO authorToAuthorResponse(Author author){
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setName(author.getName());
        authorResponseDTO.setEmail(author.getEmail());
        authorResponseDTO.setPhone(author.getPhone());

        List<String> bookNames = new ArrayList<>();
        if(!author.getBooks().isEmpty()){
            for(Book book : author.getBooks()){
                bookNames.add(book.getName());
            }
        }
        authorResponseDTO.setBookNames(bookNames);

        return authorResponseDTO;
    }

    public static List<AuthorResponseDTO> authorToAuthorResponses(List<Author> authors){
        List<AuthorResponseDTO> authorResponseDTOS = new ArrayList<>();
        for(Author author : authors){
            authorResponseDTOS.add(authorToAuthorResponse(author));
        }

        return authorResponseDTOS;
    }

    public static ZipcodeResponseDTO zipcodeToZipcodeResponse(Zipcode zipcode){
        ZipcodeResponseDTO zipcodeResponseDTO = new ZipcodeResponseDTO();
        zipcodeResponseDTO.setId(zipcode.getId());
        zipcodeResponseDTO.setCode(zipcode.getCode());

        return zipcodeResponseDTO;
    }
}
