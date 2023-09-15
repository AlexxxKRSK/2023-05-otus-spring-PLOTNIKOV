import DrawerHeader from "./DrawerHeader";
import {Divider, Drawer, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText} from "@mui/material";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import InboxIcon from "@mui/icons-material/Inbox";
import MailIcon from "@mui/icons-material/Mail";
import React, {useContext} from "react";
import {useNavigate} from "react-router-dom";
import {observer} from "mobx-react";
import StoreContext from "../store/StoreContext";

interface IProps {
  theme: any;
}

const panelItems: { route: string; name: string }[] = [
  {
    name: 'Main',
    route: '/'
  },
  {
    name: 'Show Books',
    route: "/book/list"
  },
  {
    name: 'Create Book',
    route: "/book/create"
  }
]

const SidePanel = ({theme}: IProps) => {
  const store = useContext(StoreContext);
  const nav = useNavigate();
  const drawerWidth = store.drawerWidth;

  const toRoot = (path: string) => {
    nav(path);
  }

  const handleDrawerClose = () => {
    store.drawerOpen = false;
  };

  return (
    <Drawer
      sx={{
        width: drawerWidth,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: drawerWidth,
          boxSizing: 'border-box',
        },
      }}
      variant="persistent"
      anchor="left"
      open={store.drawerOpen}
    >
      <DrawerHeader>
        <IconButton onClick={handleDrawerClose}>
          {theme.direction === 'ltr' ? <ChevronLeftIcon/> : <ChevronRightIcon/>}
        </IconButton>
      </DrawerHeader>
      <Divider/>
      <List>
        {panelItems.map((item, index) => (
          <ListItem key={item?.name} disablePadding>
            <ListItemButton
              onClick={() => toRoot(item.route)}>
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon/> : <MailIcon/>}
              </ListItemIcon>
              <ListItemText primary={item?.name}/>
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Drawer>
  )
};

export default observer(SidePanel);