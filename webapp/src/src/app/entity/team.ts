import { Resource } from "./resource";
import { Country } from "./country";
import { Driver } from "./driver";

export class Team extends Resource { 

    year: number;
    name: string;
    motor: string;
    country: Country
    drivers: Driver[];
}