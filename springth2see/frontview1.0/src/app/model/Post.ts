import { User } from "./User";

export class Post {
  id:number=0;
  title: string='';
  brief: string='';
  content: string='';
  image: string='';
  status: number=0;
  user: User = new User();
  created_at: string='';
  category: number=0;
  favoriteBy: User[]=[];
  liked: boolean=false;
}

