import { IMovimentacao } from "../commons/interfaces";
import { api } from "../lib/axios";

const findAll = (id: number) => {
    return api.get(`/movimentacoes/findAll/${id}`)
}

const save = (movimentacao: IMovimentacao) => {
    return api.post('/movimentacoes', movimentacao);
}

const remove = (id: number) => {
    return api.delete(`/movimentacoes/${id}`);
}

const findOne = (id: number) => {
    return api.get(`/movimentacoes/${id}`);
}

const calculaSaldo = (id: number) => {
    return api.get(`/movimentacoes/mov/${id}`)
}

const findDespesa = () => {
    return api.get(`/movimentacoes/desp`)
}

const findReceita = () => {
    return api.get(`/movimentacoes/rec`)
}

const valRecebido = (movimentacao: IMovimentacao) => {
    return api.get('/movimentacoes/recebido', movimentacao);
}

const valPago = (movimentacao: IMovimentacao) => {
    return api.get('/movimentacoes/pago', movimentacao);
}

const MovimentacaoService = {
    findAll,
    save,
    remove,
    findOne,
    calculaSaldo,
    findDespesa,
    findReceita,
    valRecebido,
    valPago,
}

export default MovimentacaoService;