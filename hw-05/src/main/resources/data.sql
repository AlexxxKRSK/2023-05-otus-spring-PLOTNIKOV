INSERT INTO AUTHORS (NAME)
values ('Михаил Булгаков'),
       ('Александр Пушкин'),
       ('Лев Толстой');
INSERT INTO GENRES (NAME)
values ('Роман'),
       ('Фантастика');

INSERT INTO BOOKS (NAME, AUTHOR_ID, GENRE_ID)
values ('Мастер и Маргарита', 1, 2),
       ('Евгений Онегин', 2, 1),
       ('Война и мир', 3, 1);
