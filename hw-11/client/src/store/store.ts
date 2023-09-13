import {makeAutoObservable} from "mobx";

class Store {
    private _drawerOpen: boolean;
    private _drawerWidth: number;

    constructor() {
        this._drawerOpen = false;
        this._drawerWidth = 240;
        makeAutoObservable(this);
    }

    get drawerWidth() {
        return this._drawerWidth;
    }
    set drawerWidth(value) {
        this._drawerWidth = value;
    }

    get drawerOpen() {
        return this._drawerOpen;
    }

    set drawerOpen(value) {
        this._drawerOpen = value;
    }

}

export const store = new Store();