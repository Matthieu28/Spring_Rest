package wildcodeschool.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wildcodeschool.book.entity.Book;
import wildcodeschool.book.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepositoryInjected) {
        this.bookRepository = bookRepositoryInjected;
    }

    @GetMapping("")
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public List<Book> getByKeyword(@RequestParam String keyword) {
        return bookRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    @PostMapping("")
    public Book create(@RequestBody Book newBook) {
        return this.bookRepository.save(newBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.bookRepository.deleteById(id);
    }
}
