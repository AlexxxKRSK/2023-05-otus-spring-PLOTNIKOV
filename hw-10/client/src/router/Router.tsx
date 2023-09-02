import {BrowserRouter, Route, Routes} from "react-router-dom";
import * as React from "react";
import App from "../App";
import PageNotFound from "../component/PageNotFound";
import Welcome from "../component/Welcome";
import BooksList from "../component/BooksList";
import CreateBookForm from "../component/CreateBookForm";


function Router() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path={"/"} element={<App/>}>
          <Route index element={<Welcome/>}/>
          <Route path={"book/:bookId/edit"} element={<CreateBookForm/>}></Route>
          <Route path="book/list" element={<BooksList/>}></Route>
          <Route path="book/create" element={<CreateBookForm/>}></Route>
        </Route>
        <Route path="*" element={<PageNotFound/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
