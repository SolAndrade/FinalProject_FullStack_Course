import { Movie } from "./movie.model";

export class Ticket {
    constructor(
        private _movie: Movie | null,
        private _user_name: string,
        private _user_email: string
      ) {}
    
      public get movie(): Movie | null {
        return this._movie;
      }
    
      public set movie(value: Movie | null) {
        this._movie = value;
      }
  
    public get user_name(): string {
        return this._user_name;
    }
  
    public set user_name(value: string) {
        this._user_name = value;
    }
  
    public get user_email(): string {
        return this._user_email;
    }
  
    public set user_email(value: string) {
        this._user_email = value;
    }
}
