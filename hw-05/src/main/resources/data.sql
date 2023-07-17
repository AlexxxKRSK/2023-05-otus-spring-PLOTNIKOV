INSERT INTO AUTHORS (FIRST_NAME, LAST_NAME)
values ('Hans', 'Andersen'),
       ('Hans', 'Bergman');
INSERT INTO GENRES (NAME)
values ('Science'),
       ('fiction');

INSERT INTO BOOKS (NAME, AUTHOR_ID, GENRE_ID)
values ('Test book', 1, 1);
