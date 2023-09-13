import { createContext } from "react";
import {store} from "./store";

const StoreContext = createContext(store);

export default StoreContext;