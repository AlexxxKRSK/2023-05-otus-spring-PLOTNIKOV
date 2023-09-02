import React, {useContext} from "react";
import {IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import AppBar from "./AppBar";
import {observer} from "mobx-react";
import StoreContext from "../store/StoreContext";

const Header = () => {

  const store = useContext((StoreContext));
  const drawerWidth = store.drawerWidth;
  const drawerOpen = store.drawerOpen;

  const handleDrawerOpen = () => {
    store.drawerOpen = true;
  };

  return (
    <AppBar position="fixed" open={drawerOpen} width={drawerWidth}>
      <Toolbar>
        <IconButton
          color="inherit"
          aria-label="open drawer"
          onClick={handleDrawerOpen}
          edge="start"
          sx={{mr: 2, ...(drawerOpen && {display: 'none'})}}
        >
          <MenuIcon/>
        </IconButton>
        <Typography variant="h6" noWrap component="div">
          Library App
        </Typography>
      </Toolbar>
    </AppBar>
  )
};

export default observer(Header);