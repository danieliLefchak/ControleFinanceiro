import { IConta } from "../commons/interfaces";
import { api } from "../lib/axios";

const findAll = () => {
    return api.get("/contas")
}

const save = (conta: IConta) => {
    return api.post('/contas', conta);
}

const remove = (id: number) => {
    return api.delete(`/contas/${id}`);
}

const findOne = (id?: number) => {
    return api.get(`/contas/${id}`);
}

const saldoTotal = () => {
    return api.get("/contas/saldoTotal");
}

const findTitular = (id: number) => {
    return api.get(`/contas/user/${id}`);
}

const ContaService = {
    findAll,
    save,
    remove,
    findOne,
    saldoTotal,
    findTitular,
}

export default ContaService;