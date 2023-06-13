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
  Textarea,
} from "@chakra-ui/react";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import { ICategoria, IConta, IMovimentacao } from "../../commons/interfaces";
import CategoriaService from "../../service/CategoriaService";
import ContaService from "../../service/ContaService";
import { useNavigate, useParams } from "react-router-dom";
import MovimentacaoService from "../../service/MovimentacaoService";

export function FormMovimentacoes() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    reset,
  } = useForm<IMovimentacao>();
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const [categorias, setCategorias] = useState<ICategoria[]>([]);
  const [contas, setContas] = useState<IConta[]>([]);
  const [entityMov, setEntityMov] = useState<IMovimentacao>({
    id: undefined,
    valor: 0,
    data: "",
    categoria: {
      id: undefined,
      nome: "",
    },
    descricao: "",
    situacao: "",
    idConta: {
      id: undefined,
      agencia: "",
      banco: "",
      numero: 0,
      saldo: 0,
      tipoConta: "",
    },
    tipoMovimentacao: "",
  });

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    await CategoriaService.findAll()
      .then((resposeCateg) => {
        setCategorias(resposeCateg.data);
        //console.log("Resp categ", resposeCateg.data);
        setApiError("");
      })
      .catch((error) => {
        setApiError("Falha ao carregar a combo de categorias.");
      });

    await ContaService.findAll()
      .then((responseConta) => {
        setContas(responseConta.data);
        setApiError("");
      })
      .catch((error) => {
        setApiError("Falha ao carregar o combo de contas.");
      });

    if (id) {
      MovimentacaoService.findOne(parseInt(id)).then((response) => {
        if (response.data) {
          //console.log(response.data);
          setEntityMov({
            id: response.data.id,
            valor: response.data.valor,
            data: response.data.data,
            categoria: {
              id: response.data.categoria.id,
              nome: response.data.categoria.nome,
            },
            descricao: response.data.descricao,
            situacao: response.data.situacao,
            idConta: {
              id: response.data.idConta.id,
              agencia: response.data.idConta.agencia,
              banco: response.data.idConta.banco,
              numero: response.data.idConta.numero,
              saldo: response.data.idConta.saldo,
              tipoConta: response.data.idConta.tipoConta,
            },
            tipoMovimentacao: response.data.tipoMovimentacao,
          });
        }
      });
    } else {
      setEntityMov((previousEntity) => {
        return {
          ...previousEntity,
          categoria: {
            ...categorias[0],
          },
          idConta: {
            ...contas[0],
          },
        };
      });
    }
  };

  useEffect(() => {
    reset(entityMov);
  }, [entityMov, reset]);

  const onSubmit = (data: IMovimentacao) => {
    if (id) {
      if (data.situacao === "Recebido" && data.tipoMovimentacao == "Receita") {
        const novoSaldo = data.idConta.saldo + data.valor;

        const movimentacao: IMovimentacao = {
          ...data,
          id: entityMov.id,
          categoria: {
            id: data.categoria.id,
            nome: "",
          },
          idConta: {
            ...data.idConta,
            saldo: novoSaldo,
          },
        };

        //console.log("Movimentacao ", movimentacao);
        MovimentacaoService.save(movimentacao)
          .then((response) => {
            ContaService.save(movimentacao.idConta)
              .then((response) => {
                navigate("/");
              })
              .catch((error) => {
                setApiError("Falha ao salvar conta");
              });
          })
          .catch((error) => {
            setApiError("Falha ao salvar movimentação.");
          });
      } else if (
        data.situacao === "Pago" &&
        data.valor <= data.idConta.saldo &&
        data.tipoMovimentacao == "Despesa"
      ) {
        const novoSaldo = data.idConta.saldo - data.valor;

        const movimentacao: IMovimentacao = {
          ...data,
          id: entityMov.id,
          categoria: {
            id: data.categoria.id,
            nome: "",
          },
          idConta: {
            id: data.idConta.id,
            agencia: data.idConta.agencia,
            banco: data.idConta.banco,
            numero: data.idConta.numero,
            saldo: novoSaldo,
            tipoConta: data.idConta.tipoConta,
          },
        };

        MovimentacaoService.save(movimentacao)
          .then((response) => {
            ContaService.save(movimentacao.idConta)
              .then((response) => {
                navigate("/");
              })
              .catch((error) => {
                setApiError("Falha ao salvar conta");
              });
          })
          .catch((error) => {
            setApiError("Falha ao salvar movimentação.");
          });
      } else if (data.situacao === "Pago" && data.tipoMovimentacao == "Receita") {
        setApiError("Falha ao modificar movimentação. Receita não deve ser marcada como pago, apenas como recebida.");
      } else if (data.situacao === "Recebido" && data.tipoMovimentacao == "Despesa") {
        setApiError("Falha ao modificar movimentação. Despesa não deve ser marcada como recebido apenas como pago.");
      }
    } else{
      const movimentacao: IMovimentacao = {
        ...data,
        id: entityMov.id,
        categoria: {
          id: data.categoria.id,
          nome: "",
        },
        idConta: {
          id: data.idConta.id,
          agencia: data.idConta.agencia,
          banco: data.idConta.banco,
          numero: data.idConta.numero,
          saldo: data.idConta.saldo,
          tipoConta: data.idConta.tipoConta,
        },
      };
  
      //console.log("Movimentacao ", movimentacao);
      MovimentacaoService.save(movimentacao)
        .then((response) => {
          navigate("/");
        })
        .catch((error) => {
          setApiError("Falha ao salvar movimentação.");
        });
    }
  };

  return (
    <div className="container">
      <Card className="mb-3 mt-3 bg-light">
        <CardHeader className="fs-2 text-center fw-bold">
          Nova Movimentação
        </CardHeader>

        <CardBody>
          <form className="mb-2" onSubmit={handleSubmit(onSubmit)}>
            <FormControl isInvalid={errors.data && true}>
              <FormLabel>Data</FormLabel>
              <Input
                id="data"
                placeholder="yyyy-mm-dd"
                {...register("data", {
                  required: "O campo data é obrigatório",
                })}
                type="date"
              />
              <FormErrorMessage>
                {errors.data && errors.data.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.situacao && true}>
              <FormLabel>Situação</FormLabel>
              <Select
                id="situacao"
                {...register("situacao", {
                  required: "O campo situação é obrigatório",
                })}
                size="sm"
              >
                <option key={0} value={"Pendente"}>
                  Pendente
                </option>
                <option key={1} value={"Pago"}>
                  Pago
                </option>
                <option key={2} value={"Recebido"}>
                  Recebido
                </option>
              </Select>
              <FormErrorMessage>
                {errors.situacao && errors.situacao.message}
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

            <FormControl isInvalid={errors.tipoMovimentacao && true}>
              <FormLabel>Tipo da movimentação</FormLabel>
              <Select
                id="tipoMovimentacao"
                {...register("tipoMovimentacao", {
                  required: "O campo tipo de movimentação é obrigatório",
                })}
                size="sm"
              >
                <option key={0} value={"Despesa"}>
                  Despesa
                </option>
                <option key={1} value={"Receita"}>
                  Receita
                </option>
              </Select>
              <FormErrorMessage>
                {errors.tipoMovimentacao && errors.tipoMovimentacao.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.categoria && true}>
              <FormLabel htmlFor="categoria">Categoria</FormLabel>

              <Select
                id="categorias"
                {...register("categoria.id", {
                  required: "O campo conta é obrigatório",
                })}
                size="sm"
              >
                {categorias.map((categoria: ICategoria) => (
                  <option key={categoria.id} value={categoria.id}>
                    {categoria.nome}
                  </option>
                ))}
              </Select>

              <FormErrorMessage>
                {errors.categoria && errors.categoria.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.idConta && true}>
              <FormLabel htmlFor="idConta">Conta</FormLabel>

              <Select
                id="idConta"
                {...register("idConta.id", {
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
                {errors.idConta && errors.idConta.message}
              </FormErrorMessage>
            </FormControl>

            <FormControl isInvalid={errors.descricao && true}>
              <FormLabel htmlFor="descricao">Descrição</FormLabel>
              <Textarea
                id="descricao"
                placeholder="Descrição da movimentação"
                {...register("descricao", {
                  required: "O campo descrição é obrigatório",
                  minLength: {
                    value: 2,
                    message: "O tamanho deve ser entre 2 e 1024 caracteres",
                  },
                  maxLength: {
                    value: 1024,
                    message: "O tamanho deve ser entre 2 e 1024 caracteres",
                  },
                })}
                size="sm"
              />
              <FormErrorMessage>
                {errors.descricao && errors.descricao.message}
              </FormErrorMessage>
            </FormControl>

            <div className="text-center mb-3">
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
