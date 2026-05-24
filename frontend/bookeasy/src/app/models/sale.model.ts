export interface SalaDTO {
  nome: string;
  capienza: number;
  idSede: number;
}

export interface Sala {
  idSala: number;
  nome: string;
  capienza: number;
  sede: any;
}
