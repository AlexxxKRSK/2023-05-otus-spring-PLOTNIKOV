package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/create")
    public String createBook() {
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(BookDto dto) {
        bookService.createBook(dto.getName(), dto.getAuthor(), dto.getGenre());
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/edit")
    public String editBookById(@RequestParam(value = "id") Long id, Model model) {
        BookDto dto = bookService.getBookById(id);
        model.addAttribute("book", dto);
        return "book/edit";
    }

    @DeleteMapping("/delete")
    public String deleteBookById(@RequestParam("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/list";
    }

    @PutMapping("/update")
    public String updateBook(BookDto dto) {
        bookService.updateBook(dto.getId(), dto.getName(), dto.getAuthor(), dto.getGenre());
        return "redirect:/list";
    }

    @GetMapping("/comment-list")
    public String listCommentsByBookId(@RequestParam(value = "id") Long id, Model model) {
        BookDto dto = bookService.getBookById(id);
        model.addAttribute("book", dto);
        return "comment/list";
    }
}
