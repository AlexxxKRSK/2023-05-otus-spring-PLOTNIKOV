export interface Book {
  id?: number,
  name?: string,
  author?: string,
  genre?: string,
  commentList?: { id: number, text: string }[]
}

export interface Author {
  id: number,
  name: string,
}

export interface Genre {
  id: number,
  name: string,
}