import React, {useContext} from 'react';
import './App.css';
import {
  Box,
  CssBaseline,
  useTheme
} from "@mui/material";
import {Outlet} from "react-router-dom";
import Header from "./component/Header";
import DrawerHeader from "./component/DrawerHeader";
import Main from "./component/Main"
import SidePanel from "./component/SidePanel";
import {observer} from "mobx-react";
import StoreContext from "./store/StoreContext";

const drawerWidth = 240;

function App() {
  const theme = useTheme();
  const store = useContext(StoreContext);

  return (
    <div className="App">
      <Box sx={{display: 'flex'}}>
        <CssBaseline/>
        <Header/>
        <SidePanel theme={theme}/>
        <Main className="main" open={store.drawerOpen} width={drawerWidth}>
          <DrawerHeader/>
          <Outlet/>
        </Main>
      </Box>
    </div>
  )
    ;
}

export default observer(App);
