import { Album } from './album.model';
export class Chanson {
    idChanson!: number;
    title!: string;
    artist!: string;
    releaseDate!: Date;
    album?: Album;
}
