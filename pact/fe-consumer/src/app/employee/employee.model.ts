
export interface IEmployee {
  id?: number;
  name?: string;
  title?: string;
  email?: string;
}

export class Employee implements IEmployee {
  constructor(public id?: number, public name?: string, public title?: string, public email?: string) {}
}
