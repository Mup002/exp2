package exp2.com.service;

import exp2.com.model.Category;
import exp2.com.utils.request.CategoryRequestDTO;
import exp2.com.utils.response.CategoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<CategoryResponseDTO> getCategories();
    Optional<CategoryResponseDTO> getCategory(Long id);
    CategoryResponseDTO save(CategoryRequestDTO dto);
    CategoryResponseDTO update(CategoryRequestDTO dto, Long id);
    CategoryResponseDTO delete(Long id);
    Category getCategoryById(Long id);
    CategoryResponseDTO addBookToCategory(Long categoryId, Long bookId);

    CategoryResponseDTO removeBookToCategory(Long categoryId, Long bookId);
}
