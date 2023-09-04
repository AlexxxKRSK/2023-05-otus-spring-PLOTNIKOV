import {useEffect, useState} from "react";
import {Box, Button, MenuItem, TextField} from "@mui/material";
import {Book, Author, Genre} from "../types/Interfaces";
import {useNavigate, useParams} from "react-router-dom";
import {queryService} from "../service/QueryService";

const CreateBookForm = () => {
  const nav = useNavigate();
  const [authors, setAuthors] = useState<Author[]>([]);
  const [genres, setGenres] = useState<Genre[]>([]);
  const [book, setBook] = useState<Book>({});
  const {bookId} = useParams();

  useEffect(() => {
    if (bookId) {
      queryService.getApi(`/book/${bookId}`)
        .then(resp => setBook(resp));
    }
    queryService.getApi('/author')
      .then(resp => {
        setAuthors(resp);
        if (!book.author) {
          book.author = resp[0]?.name;
        }
      });
    queryService.getApi('/genre')
      .then(resp => {
        setGenres(resp);
        if (!book.genre) {
          book.genre = resp[0]?.name;
        }
      });
  });

  const setBookName = (ev: any) => {
    setBook({...book, name: ev.target.value});
  }

  const setBookAuthor = (ev: any) => {
    setBook({...book, author: ev.target.value});
  }

  const setBookGenre = (ev: any) => {
    setBook({...book, genre: ev.target.value});
  }

  const createBook = () => {
    if (book.id) {
      queryService.putApi(`/book`, book)
        .then(() => nav("/book/list"));
    } else {
      queryService.postApi(`/book`, book)
        .then(() => nav("/book/list"));
    }
  }

  return (
    <div>
      <Box
        component="form"
        sx={{
          '& .MuiTextField-root': {m: 1, width: '25ch'},
        }}
        noValidate
        autoComplete="off"
      >
        <div style={{
          display: 'flex',
          flexDirection: 'column'
        }}>
          {book.id &&
              <TextField
                  id="outlined-error"
                  label="Book id"
                  disabled
                  value={book.id}
              />}
          <TextField
            id="outlined-error"
            label="Book name"
            value={book.name ? book.name : ''}
            onChange={setBookName}
          />
          {authors[0] && <TextField
              id="filled-select-currency"
              select
              label="Select"
              value={book.author ? book.author : authors[0]?.name}
              helperText="Please select author"
              variant="filled"
              onChange={setBookAuthor}
          >
            {authors.map((option) => (
              <MenuItem key={option.id} value={option.name}>
                {option.name}
              </MenuItem>
            ))}
          </TextField>
          }
          {genres[0] && <TextField
              id="filled-select-currency"
              select
              label="Select"
              value={book.genre ? book.genre : genres[0]?.name}
              helperText="Please select genre"
              variant="filled"
              onChange={setBookGenre}
          >
            {
              genres.map((option) => (
                <MenuItem key={option.id} value={option.name}>
                  {option.name}
                </MenuItem>
              ))
            }
          </TextField>
          }
        </div>
      </Box>
      <Button onClick={createBook} variant="contained">{book.id ? 'Update' : 'Create book'}</Button>
    </div>
  );
}

export default CreateBookForm;