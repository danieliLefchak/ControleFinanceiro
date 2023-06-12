import {
  Button,
  Card,
  CardBody,
  CardHeader,
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
  Select,
} from "@chakra-ui/react";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import { IConta, ITransferencia } from "../../commons/interfaces";
import ContaService from "../../service/ContaService";
import { useNavigate } from "react-router-dom";
import TransferenciaService from "../../service/TransferenciaService";

export function FormTransferencia() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    reset,
  } = useForm<ITransferencia>();
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  const [contas, setContas] = useState<IConta[]>([]);
  const [entityTrans, setEntityTrans] = useState<ITransferencia>({
    id: undefined,
    idConta1: {
      id: undefined,
      agencia: "",
      banco: "",
      numero: 0,
      saldo: 0,
      tipoConta: "",
    },
    idConta2: {
      id: undefined,
      agencia: "",
      banco: "",
      numero: 0,
      saldo: 0,
      tipoConta: "",
    },
    valor: 0,
  });

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    await ContaService.findAll()
      .then((responseConta) => {
        setContas(responseConta.data);
        setApiError("");
      })
      .catch((error) => {
        setApiError("Falha ao carregar o combo de contas.");
      });

    setEntityTrans((previousEntity) => {
      return {
        ...previousEntity,
        idConta1: {
          ...contas[0],
        },
        idConta2: {
          ...contas[1],
        },
      };
    });
  };

  useEffect(() => {
    reset(entityTrans);
  }, [entityTrans, reset]);

  const onSubmit = (data: ITransferencia) => {
    const transferencia: ITransferencia = {
      ...data,
      id: entityTrans.id,
      idConta1: {
        id: data.idConta1.id,
        ...data.idConta1,
      },
      idConta2: {
        id: data.idConta2.id,
        ...data.idConta2,
      },
    };

    ContaService.findOne(transferencia.idConta2.id)
      .then((responseCnt) => {
        //console.log("RESPOSTA FIND ONE ", responseCnt);

        if (data.valor <= responseCnt.data.saldo) {
          //console.log("ESTA NO IF");
          TransferenciaService.realizaTransferencia(transferencia)
            .then((response) => {
              navigate("/");
            })
            .catch((error) => {
              setApiError("Falha ao salvar tranferencia.");
            });
        } else {
          //console.log("ESTA NO ELSE");
          setApiError(
            "Não é possivel realizar a transferencia pois o valor é maior que o saldo  atual."
          );
        }
      })
      .catch((error) => {
        setApiError("Falha ao carregar as contas.");
      });
  };

  return (
    <div className="container">
      <Card className="mb-3 mt-3 bg-light">
        <CardHeader className="fs-2 text-center fw-bold">
          Nova Transferencia
        </CardHeader>

        <CardBody>
          <form className="mb-2" onSubmit={handleSubmit(onSubmit)}>
            <FormControl isInvalid={errors.idConta1 && true}>
              <FormLabel htmlFor="idConta1">
                Conta que receberá a transferencia
              </FormLabel>

              <Select
                id="idConta1"
                {...register("idConta1.id", {
                  required: "O campo conta é obrigatório",
                })}
                size="sm"
              >
                {contas.map((conta: IConta) => (
                  <option key={conta.id} value={conta.id}>
                    {conta.banco}
                  </option>
                ))}
              </Select>

              <FormErrorMessage>
                {errors.idConta1 && errors.idConta1.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.idConta2 && true}>
              <FormLabel htmlFor="idConta2">
                Conta que enviará a transferencia
              </FormLabel>

              <Select
                id="idConta2"
                {...register("idConta2.id", {
                  required: "O campo conta é obrigatório",
                })}
                size="sm"
              >
                {contas.map((conta: IConta) => (
                  <option key={conta.id} value={conta.id}>
                    {conta.banco}
                  </option>
                ))}
              </Select>

              <FormErrorMessage>
                {errors.idConta2 && errors.idConta2.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.valor && true}>
              <FormLabel>Valor</FormLabel>
              <Input
                id="valor"
                placeholder="0.0"
                {...register("valor", {
                  required: "O campo valor é obrigatório",
                })}
                type="number"
              />
              <FormErrorMessage>
                {errors.valor && errors.valor.message}
              </FormErrorMessage>
            </FormControl>

            <div className="text-center mb-2">
              <Button
                mt={4}
                colorScheme="purple"
                isLoading={isSubmitting}
                type="submit"
              >
                Salvar
              </Button>
            </div>
            {apiError && <div className="alert alert-danger">{apiError}</div>}
          </form>
        </CardBody>
      </Card>
    </div>
  );
}
