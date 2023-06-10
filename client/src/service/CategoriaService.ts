import { ICategoria } from "../commons/interfaces"
import { api } from "../lib/axios"

const findAll = () => {
    return api.get("/categorias")
}

const save = (categoria: ICategoria) => {
    return api.post('/categorias', categoria);
}

const remove = (id: number) => {
    return api.delete(`/categorias/${id}`);
}

const findOne = (id: number) => {
    return api.get(`/categorias/${id}`);
}

const CategoriaService = {
    findAll,
    save,
    remove,
    findOne,
}

export default CategoriaService;