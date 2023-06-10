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
import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  IConta,
} from "../../commons/interfaces";
import { useForm } from "react-hook-form";
import ContaService from "../../service/ContaService";

export function FormContasPage() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    reset,
  } = useForm<IConta>();
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const [entityConta, setEntityConta] = useState<IConta>({
    id: undefined,
    agencia: "",
    banco: "",
    numero: 0,
    saldo: 0,
    tipoConta: "",
  });

  useEffect(() => {
    loadData();
  }, []);
  
  const loadData = () => {
    if (id) {
      ContaService.findOne(parseInt(id))
        .then((response) => {
          if (response.data) {

            setEntityConta({
              id: response.data.id,
              agencia: response.data.agencia,
              banco: response.data.banco,
              numero: response.data.numero,
              saldo: response.data.saldo,
              tipoConta: response.data.tipoConta,
            });
            setApiError("");
          } else {
            setApiError("Erro ao carregar os dados da conta");
          }
        })
        .catch((error) => {
          setApiError("Falha ao carregar a conta");
        });
    } else {
      setEntityConta((previousEntityConta) => {
        return {
          ...previousEntityConta,
        };
      });
    }
  };

  useEffect(() => {
    reset(entityConta);
  }, [entityConta, reset]);

  const onSubmit = (data: IConta) => {
    const novaconta: IConta = {
      ...data,
      id: entityConta.id,
    };

    ContaService.save(novaconta)
      .then((response) => {
        navigate("/contas");
      })
      .catch((error) => {
        setApiError("Falha ao salvar conta");
      });
  };

  return (
    <div className="container">
      <Card className="mb-3 mt-3 bg-light">
        <CardHeader className="fs-2 text-center fw-bold">Nova Conta</CardHeader>

        <CardBody>
          <form className="mb-2" onSubmit={handleSubmit(onSubmit)}>
            <FormControl isInvalid={errors.agencia && true}>
              <FormLabel>Agencia</FormLabel>
              <Input
                id="agencia"
                placeholder="numero da agencia"
                {...register("agencia", {
                  required: "O campo agencia é obrigatório",
                })}
              />
              <FormErrorMessage>
                {errors.agencia && errors.agencia.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.banco && true}>
              <FormLabel>Banco</FormLabel>
              <Input
                id="banco"
                placeholder="Nome do banco"
                {...register("banco", {
                  required: "O campo banco é obrigatório",
                })}
              />
              <FormErrorMessage>
                {errors.banco && errors.banco.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.numero && true}>
              <FormLabel>Número</FormLabel>
              <Input
                id="numero"
                placeholder="Numero da conta"
                {...register("numero", {
                  required: "O campo numero é obrigatório",
                })}
                type="number"
              />
              <FormErrorMessage>
                {errors.numero && errors.numero.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.saldo && true}>
              <FormLabel>Saldo</FormLabel>
              <Input
                id="saldo"
                placeholder="R$ 0.0"
                {...register("saldo", {
                  required: "O campo preço é obrigatório",
                  min: {
                    value: 0.01,
                    message: "O valor deve ser maior que zero",
                  },
                })}
                type="number"
                step="any"
              />
              <FormErrorMessage>
                {errors.saldo && errors.saldo.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.agencia && true}>
              <FormLabel>Tipo da Conta</FormLabel>
              <Select
                id="tipoConta"
                {...register("tipoConta", {
                  required: "O campo tipo da conta é obrigatório",
                })}
                size="sm"
              >
                <option key={0} value={"Conta Corrente"}>
                  Conta Corrente
                </option>
                <option key={1} value={"Conta Poupanca"}>
                  Conta Poupança
                </option>
                <option key={2} value={"Investimento"}>
                  Investimento
                </option>
                <option key={3} value={"Casa"}>
                  Casa
                </option>

              </Select>
            </FormControl>

            <div className="text-center mt-3">
              <Button colorScheme="blue" isLoading={isSubmitting} type="submit">
                Salvar
              </Button>
            </div>
          </form>
        </CardBody>
      </Card>

      <div className="text-center">
        <Link to="/contas">Voltar para a lista de contas</Link>
      </div>
    </div>
  );
}
