import React, {useEffect, useState} from "react";
import {queryService} from "../service/QueryService";
import {observer} from "mobx-react";
import {
  Box,
  Button,
  Modal,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow
} from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import PreviewIcon from '@mui/icons-material/Preview';
import BookCard from "./BookCard";
import {Book} from "../types/Interfaces";
import {useNavigate} from "react-router-dom";

const BooksList = () => {
  const nav = useNavigate();
  const [books, setBooks] = useState<Book[]>([]);
  const [selectedBook, setSelectedBook] = useState<Book | undefined>(undefined);

  useEffect(() => {
    queryBooks();
  }, []);

  const queryBooks = () => {
    queryService.getApi('/book')
      .then(resp => setBooks(resp));
  }

  const editBook = (book: Book) => {
    nav(`/book/${book.id}/edit`);
  }

  const deleteBook = (book: Book) => {
    queryService.deleteApi(`/book/${book.id}`)
      .then(queryBooks);
  }

  const handleClose = () => {
    setSelectedBook(undefined);
  }

  function showBookCard(book: Book) {
    queryService.getApi(`/book/${book.id}`)
      .then(resp => setSelectedBook(resp));
  }

  return (
    <TableContainer component={Paper}
                    sx={{
                      alignSelf: 'flex-start',
                      justifySelf: 'flex-start'
                    }}>
      <Table sx={{minWidth: 650}} size="medium" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell>id</TableCell>
            <TableCell align="center">Book name</TableCell>
            <TableCell align="right">Author</TableCell>
            <TableCell align="right">Genre</TableCell>
            <TableCell align="center">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {books?.map((row) => (
            <TableRow
              key={row.name}
              sx={{'&:last-child td, &:last-child th': {border: 0}}}
            >
              <TableCell component="th" scope="row">
                {row.id}
              </TableCell>
              <TableCell align="right">{row.name}</TableCell>
              <TableCell align="right">{row.author}</TableCell>
              <TableCell align="right">{row.genre}</TableCell>
              <TableCell align="center">
                <Button size={'small'} onClick={() => editBook(row)}> <EditIcon/> </Button>
                <Button size={'small'} onClick={() => deleteBook(row)}> <DeleteIcon/> </Button>
                <Button size={'small'} onClick={() => showBookCard(row)}> <PreviewIcon/> </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Modal
        open={selectedBook !== undefined}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box><BookCard book={selectedBook}/></Box>
      </Modal>
    </TableContainer>
  )
}
export default observer(BooksList);