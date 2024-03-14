import { Marketplace } from "./Marketplace";
import { Post } from "./Post";
import {Role} from "./Role";

export class User {
    id: number=0;
    name: string='';
    lastname: string='';
    username: string='';
    email:string='';
    password: string='';
    image: string='';
    status: number=0;
    roles: Role[]=[];
    product: Marketplace[]=[];
  }
  
  export interface Credentials{
    username: string;
    password: string;
  }
  