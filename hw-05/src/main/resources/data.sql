INSERT INTO AUTHORS (NAME)
values ('Hans Andersen'),
       ('Hans Bergman');
INSERT INTO GENRES (NAME)
values ('Science'),
       ('Fiction');

INSERT INTO BOOKS (NAME, AUTHOR_ID, GENRE_ID)
values ('Niels', 1, 1);
