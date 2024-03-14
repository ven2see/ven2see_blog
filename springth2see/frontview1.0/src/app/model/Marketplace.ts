import { User } from "./User";

export class Marketplace{
    id: number=0;
    name: string='';
    description: string='';
    price: number=0;
    category: number =0;
    image: string='';
    seller:User|null=null;
}
