package exp2.com.service.impl;

import exp2.com.errors.ResourceNotFoundException;
import exp2.com.model.Book;
import exp2.com.model.Category;
import exp2.com.repository.CategoryRepository;
import exp2.com.service.BookService;
import exp2.com.service.CategoryService;
import exp2.com.utils.mapper;
import exp2.com.utils.request.CategoryRequestDTO;
import exp2.com.utils.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final BookService bookService;
    @Override
    public List<CategoryResponseDTO> getCategories() {

        return mapper.categoryToCategoryResponses(StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public Optional<CategoryResponseDTO> getCategory(Long id) {
        return Optional.of(mapper.categoryToCategoryResponse(categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category not found by id: " + id))));
    }

    @Override
    public CategoryResponseDTO save(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return mapper.categoryToCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDTO update(CategoryRequestDTO dto, Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Category not found by id: " + id));
        category.setName(dto.getName());
        return mapper.categoryToCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDTO delete(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Category not found by id: " + id));
        categoryRepository.delete(category);
        return mapper.categoryToCategoryResponse(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Category not found by id: " + id));
        return category;
    }

    @Override
    public CategoryResponseDTO addBookToCategory(Long categoryId, Long bookId) {
        Category category = getCategoryById(categoryId);
        Book book = bookService.getBookById(bookId);
        book.setCategory(category);
        bookService.savedBook(book);
        category.AddBook(book);
        return mapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponseDTO removeBookToCategory(Long categoryId, Long bookId) {
        Category category = getCategoryById(categoryId);
        Book book = bookService.getBookById(bookId);
        book.setCategory(null);
        bookService.savedBook(book);
        category.RemoveBook(book);
        return mapper.categoryToCategoryResponse(category);
    }
}
