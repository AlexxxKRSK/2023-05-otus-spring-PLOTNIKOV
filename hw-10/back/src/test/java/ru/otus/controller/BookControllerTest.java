package ru.otus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.TestDataProvider;
import ru.otus.service.BookService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testCreateBook() throws Exception {
        var dto = TestDataProvider.getNewBookDto();

        when(bookService.createBook(any(), any(), any())).thenReturn(dto);

        mvc.perform(post("/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(dto)))
                .andExpect(content().contentType(APPLICATION_JSON));

        verify(bookService, times(1)).createBook(dto.getName(), dto.getAuthor(), dto.getGenre());
    }

    @Test
    void getAllBooks() throws Exception {
        var existingBooks = List.of(TestDataProvider.getExistingBookDto());
        when(bookService.getAllBooks()).thenReturn(existingBooks);

        mvc.perform(get("/book/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(mapper.writeValueAsString(existingBooks)))
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void deleteBookById() throws Exception {
        var existingBook = TestDataProvider.getExistingBook();
        when(bookService.deleteBookById(existingBook.getId())).thenReturn(Boolean.TRUE);

        mvc.perform(delete(String.format("/book/%s/delete", existingBook.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(Boolean.TRUE)))
                .andExpect(content().contentType(APPLICATION_JSON));

        verify(bookService, times(1)).deleteBookById(existingBook.getId());
    }

    @Test
    void updateBook() throws Exception {
        var reqDto = TestDataProvider.getExistingBookDto();

        var dto = TestDataProvider.getExistingBookWithCommentDto();
        String NEW_NAME = "NEW BOOK NAME";
        reqDto.setName(NEW_NAME);
        dto.setName(NEW_NAME);

        when(bookService.updateBook(reqDto.getId(), reqDto.getName(), reqDto.getAuthor(), reqDto.getGenre())).thenReturn(dto);

        mvc.perform(put("/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reqDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(dto)))
                .andExpect(content().contentType(APPLICATION_JSON));

        verify(bookService, times(1))
                .updateBook(dto.getId(), NEW_NAME, dto.getAuthor(), dto.getGenre());
    }

}