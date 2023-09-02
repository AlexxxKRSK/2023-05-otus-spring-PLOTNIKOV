import {Box, List, ListItem, ListItemButton, ListItemText, Typography} from "@mui/material";
import React from "react";
import {Book} from "../types/Interfaces";

interface IProps {
  book?: Book
}

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

const BookCard = ({book}: IProps) => {
  return (
    <Box sx={style}>
      <Typography id="modal-modal-title" variant="h6" component="h2">
        id: {book?.id}
      </Typography>
      <Typography id="modal-modal-description" sx={{mt: 2}}> name: {book?.name}</Typography>
      <Typography id="modal-modal-description" sx={{mt: 2}}> author: {book?.author}</Typography>
      <Typography id="modal-modal-description" sx={{mt: 2}}> genre: {book?.genre}</Typography>
      <List>
        <Typography>comments:</Typography>
        {book?.commentList?.map((c, i) =>
          <ListItem disablePadding key={`comment-${i}`}>
            <ListItemButton>
              <ListItemText primary={c.text}/>
            </ListItemButton>
          </ListItem>
        )}
      </List>
    </Box>
  );
}

export default BookCard;
