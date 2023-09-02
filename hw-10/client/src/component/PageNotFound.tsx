import {Button, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";

const PageNotFound = () => {
  const nav = useNavigate();

  const navToMain = () => {
    nav('/');
  }

  return (
    <div style={{
      display: 'flex',
      flexGrow: '1',
      height: '100vh',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
    }}>
      <Typography>Page not found</Typography>
      <Button variant="contained" onClick={navToMain}>Main page</Button>
    </div>
  )
}

export default PageNotFound;