package ru.otus.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.otus.BookProvider;
import ru.otus.service.BookService;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mvc;

    @Test
    void createBookView() throws Exception {
        mvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/create"));
    }

    @Test
    void testCreateBook() throws Exception {
        var dto = BookProvider.getNewBookDto();

        when(bookService.createBook(any(), any(), any())).thenReturn(dto);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap(Map.of(
                "name", List.of(dto.getName()),
                "author", List.of(dto.getAuthor()),
                "genre", List.of(dto.getGenre())
        ));
        mvc.perform(post("/create").params(params))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));
        verify(bookService, times(1)).createBook(dto.getName(), dto.getAuthor(), dto.getGenre());
    }

    @Test
    void getAllBooks() throws Exception {
        var existingBooks = List.of(BookProvider.getExistingBookDto());
        when(bookService.getAllBooks()).thenReturn(existingBooks);

        mvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", existingBooks))
                .andExpect(view().name("book/list"));
    }

    @Test
    void editBookById() throws Exception {
        var existingBook = BookProvider.getExistingBookWithCommentDto();
        when(bookService.getBookById(existingBook.getId())).thenReturn(existingBook);

        mvc.perform(get("/edit").param("id", String.valueOf(existingBook.getId())))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", existingBook))
                .andExpect(view().name("book/edit"));
    }

    @Test
    void deleteBookById() throws Exception {
        var existingBook = BookProvider.getExistingBook();
        mvc.perform(delete("/delete").param("id", String.valueOf(existingBook.getId())))
                .andExpect(redirectedUrl("/list"));

        verify(bookService, times(1)).deleteBookById(existingBook.getId());
    }

    @Test
    void updateBook() throws Exception {
        var dto = BookProvider.getExistingBookWithCommentDto();
        String NEW_NAME = "NEW BOOK NAME";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap(Map.of(
                "id", List.of(String.valueOf(dto.getId())),
                "name", List.of(NEW_NAME),
                "author", List.of(dto.getAuthor()),
                "genre", List.of(dto.getGenre())
        ));

        mvc.perform(put("/update").params(params))
                .andExpect(redirectedUrl("/list"));

        verify(bookService, times(1))
                .updateBook(dto.getId(), NEW_NAME, dto.getAuthor(), dto.getGenre());
    }

    @Test
    void listCommentsByBookId() throws Exception {
        var dto = BookProvider.getExistingBookWithCommentDto();

        when(bookService.getBookById(dto.getId())).thenReturn(dto);

        mvc.perform(get("/comment-list").param("id", String.valueOf(dto.getId())))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", dto))
                .andExpect(view().name("comment/list"));

        verify(bookService, times(1)).getBookById(dto.getId());
    }
}