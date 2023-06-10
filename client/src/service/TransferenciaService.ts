import { ITransferencia } from "../commons/interfaces";
import { api } from "../lib/axios";

const realizaTransferencia = (transferencia: ITransferencia) => {
    return api.post('/trasnferencias', transferencia);
}

const TransferenciaService = {
    realizaTransferencia,
}

export default TransferenciaService;