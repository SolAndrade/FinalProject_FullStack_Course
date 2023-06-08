export class Movie {
    constructor(
      private _title: string,
      private _description: string,
      private _genre: string,
      private _duration: number,
      private _rating: number,
      private _ageMin: number,
      private _tickets: number,
      private _imgUrl: string
    ) {}

    public get title(): string {
        return this._title;
    }
    public set title(value: string) {
        this._title = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }

    public get genre(): string {
        return this._genre;
    }
    public set genre(value: string) {
        this._genre = value;
    }

    public get duration(): number {
        return this._duration;
    }
    public set duration(value: number) {
        this._duration = value;
    }

    public get rating(): number {
        return this._rating;
    }
    public set rating(value: number) {
        this._rating = value;
    }
    
    public get ageMin(): number {
        return this._ageMin;
    }
    public set ageMin(value: number) {
        this._ageMin = value;
    }
    
    public get tickets(): number {
        return this._tickets;
    }
    public set tickets(value: number) {
        this._tickets = value;
    }

    public get imgUrl(): string {
        return this._imgUrl;
    }
    public set imgUrl(value: string) {
        this._imgUrl= value;
    }
  }
  