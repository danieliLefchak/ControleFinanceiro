export interface IUserLogin{
    username: String;
    password: String;
}

export interface ISignup{
    id?: number;
    username: String;
    email: String;
    telefone: String;
    cpf: String;
    password: String;
}

export interface ICategoria{
    id?: number;
    nome: String;
}

export interface ITransferencia{
    id?: number;
    idConta1: IConta;
    idConta2: IConta;
    valor: number;
}

export interface IConta{
    id?: number;
    agencia: String;
    banco: String;
    numero: number;
    saldo: number;
    tipoConta: String;
}

export interface IMovimentacao{
    id?: number;
    valor: number;
    data: String;
    categoria: ICategoria;
    descricao: String;
    situacao: String;
    idConta: IConta;
    tipoMovimentacao: String;
}