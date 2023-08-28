package ru.otus.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final BookService bookService;

    @PostMapping("comment/create")
    public String addCommentToBook(@RequestParam("bookId") @NonNull Long bookId,
                                   String text) {
        commentService.addComment(bookId, text);
        return "redirect:/comment/list?id=" + bookId;
    }

    @GetMapping("/comment/create")
    public String createComment(@RequestParam("id") Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "comment/create";
    }

    @DeleteMapping("/comment/delete/{book-id}")
    public String deleteCommentById(@PathVariable("book-id") Long bookId,
                                    @RequestParam("id") @NonNull Long commentId) {
        commentService.deleteCommentById(commentId);
        return "redirect:/comment/list?id=" + bookId;
    }

    @GetMapping("/comment/list")
    public String listCommentsByBookId(@RequestParam(value = "id") Long id, Model model) {
        BookDto dto = bookService.getBookById(id);
        model.addAttribute("book", dto);
        return "comment/list";
    }
}
