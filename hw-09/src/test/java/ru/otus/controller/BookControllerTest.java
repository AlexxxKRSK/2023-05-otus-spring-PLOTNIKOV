package ru.otus.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.otus.TestDataProvider;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

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
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private MockMvc mvc;

    @Test
    void createBookView() throws Exception {
        when(authorService.getAllAuthors()).thenReturn(List.of(TestDataProvider.getExistingAuthorDto()));
        when(genreService.getAllGenres()).thenReturn(List.of(TestDataProvider.getExistingGenrerDto()));

        mvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/create-edit"));
    }

    @Test
    void testCreateBook() throws Exception {
        var dto = TestDataProvider.getNewBookDto();

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
        var existingBooks = List.of(TestDataProvider.getExistingBookDto());
        when(bookService.getAllBooks()).thenReturn(existingBooks);

        mvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", existingBooks))
                .andExpect(view().name("book/list"));
    }

    @Test
    void editBookById() throws Exception {
        var existingBook = TestDataProvider.getExistingBookWithCommentDto();
        when(bookService.getBookById(existingBook.getId())).thenReturn(existingBook);
        when(authorService.getAllAuthors()).thenReturn(List.of(TestDataProvider.getExistingAuthorDto()));
        when(genreService.getAllGenres()).thenReturn(List.of(TestDataProvider.getExistingGenrerDto()));

        mvc.perform(get("/edit").param("id", String.valueOf(existingBook.getId())))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", existingBook))
                .andExpect(view().name("book/create-edit"));
    }

    @Test
    void deleteBookById() throws Exception {
        var existingBook = TestDataProvider.getExistingBook();
        mvc.perform(delete("/delete").param("id", String.valueOf(existingBook.getId())))
                .andExpect(redirectedUrl("/list"));

        verify(bookService, times(1)).deleteBookById(existingBook.getId());
    }

    @Test
    void updateBook() throws Exception {
        var dto = TestDataProvider.getExistingBookWithCommentDto();
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

}