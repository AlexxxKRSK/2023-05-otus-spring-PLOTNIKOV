package ru.otus.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.TestDataProvider;
import ru.otus.dto.BookDto;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.otus.TestDataProvider.*;

@WebFluxTest(BookController.class)
@ComponentScan("ru.otus.domain.mappers")
class BookControllerTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateBook() {
        var dto = TestDataProvider.getNewBookDto();
        var testBook = getExistingBook();
        testBook.setId(null);
        var expectedBook = getExistingBook();
        var expectedResult = getExistingBookDto();

        when(authorRepository.getAuthorByName(any())).thenReturn(Mono.empty());
        when(authorRepository.save(any())).thenReturn(Mono.just(testBook.getAuthor()));
        when(genreRepository.getGenreByName(dto.getGenre())).thenReturn(Mono.empty());
        when(genreRepository.save(any())).thenReturn(Mono.just(testBook.getGenre()));
        when(bookRepository.save(any())).thenReturn(Mono.just(expectedBook));

        var actualResult = webTestClient
                .post().uri("/book")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(dto))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BookDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getAllBooks() {
        var existingBooks = List.of(TestDataProvider.getExistingBook());
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(existingBooks));

        webTestClient
                .get().uri("/book")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].name").isEqualTo(existingBooks.get(0).getName())
        ;
    }

    @Test
    void deleteBookById() {
        var existingBook = TestDataProvider.getExistingBookWithComment();
        when(bookRepository.deleteBookById(existingBook.getId())).thenReturn(Mono.just(1L));
        webTestClient
                .delete().uri("/book/{bookId}", existingBook.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
        ;
        verify(bookRepository, times(1)).deleteBookById(existingBook.getId());
    }

    @Test
    void updateBook() {
        var dto = TestDataProvider.getNewBookDto();

        var testBook = getExistingBook();
        testBook.setId(null);
        var expectedBook = getExistingBook();
        var expectedResult = getExistingBookDto();
        expectedResult.setName(dto.getName());

        when(bookRepository.getBookById(any())).thenReturn(Mono.just(expectedBook));
        when(authorRepository.getAuthorByName(any())).thenReturn(Mono.empty());
        when(authorRepository.save(any())).thenReturn(Mono.just(testBook.getAuthor()));
        when(genreRepository.getGenreByName(dto.getGenre())).thenReturn(Mono.empty());
        when(genreRepository.save(any())).thenReturn(Mono.just(testBook.getGenre()));
        when(bookRepository.save(any())).thenReturn(Mono.just(expectedBook));

        var actualResult = webTestClient
                .put().uri("/book")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(dto))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BookDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

}