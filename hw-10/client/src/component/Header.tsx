import {useEffect} from "react";
import queryService from "../service/QueryService";

const Header = () => {

  useEffect(() => {
    console.log(process.env.REACT_APP_API_HOST);
    queryService.getApi('/book/list')
      .then(resp => resp.json())
      .then(resp => console.log(resp));
  }, []);

  return (
    <div>Header</div>
  );
}

export default Header;