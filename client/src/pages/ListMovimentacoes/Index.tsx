import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Heading,
  IconButton,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
  Table,
  TableContainer,
  Tbody,
  Td,
  Th,
  Thead,
  Tr,
} from "@chakra-ui/react";
import {
  BsChevronDown,
  BsPencilSquare,
  BsPlusCircle,
  BsPlusSlashMinus,
  BsTrash,
} from "react-icons/bs";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { IMovimentacao } from "../../commons/interfaces";
import MovimentacaoService from "../../service/MovimentacaoService";

export function ListMovimentacoes() {
  const [data, setData] = useState<IMovimentacao[]>([]);
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  const { contaId } = useParams();

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    if (contaId) {
      MovimentacaoService.findAll(parseInt(contaId))
        .then((response) => {
          console.log(response.data);
          setData(response.data);
          setApiError("");
        })
        .catch((error) => {
          setApiError("Falha ao carregar a lista de movimentacoes.");
        });
    } else {
      setApiError("Falha ao carregar a lista de movimentacoes.");
    }
  };

  const onEdit = (url: string) => {
    navigate(url);
  };

  const onRemove = (id: number) => {
    MovimentacaoService.remove(id)
      .then((response) => {
        loadData();
        setApiError("");
      })
      .catch((error) => {
        setApiError("Falha ao remover o movimentacao.");
      });
  };

  const onDoMovimentation = (
    id: number,
    valMov: number,
    saldo: number,
    tpMov: String
  ) => {
    if (tpMov === "Despesa" && valMov <= saldo) {
      MovimentacaoService.calculaSaldo(id)
        .then((response) => {
          loadData();
          setApiError("");
          navigate("/");
        })
        .catch((error) => {
          setApiError("Falha ao realizar o movimentacao.");
        });
    } else if (tpMov === "Receita") {
      MovimentacaoService.calculaSaldo(id)
        .then((response) => {
          loadData();
          setApiError("");
          navigate("/");
        })
        .catch((error) => {
          setApiError("Falha ao realizar o movimentacao.");
        });
    } else {
      setApiError("O valor da movimentação é maior que o saldo atual.");
    }
  };

  return (
    <div className="container">
      <Card className="bg-light mb-3">
        <CardHeader className="fs-2 text-center">Movimentações</CardHeader>

        <CardBody>
          <Card>
            <CardHeader>
              <Heading size="md">Lista de movimentações</Heading>
            </CardHeader>

            <TableContainer>
              <Table>
                <Thead>
                  <Tr>
                    <Th>#</Th>
                    <Th>Data</Th>
                    <Th>Situação</Th>
                    <Th isNumeric>valor</Th>
                    <Th>Tipo movimentção</Th>
                    <Th>Cartegoria</Th>
                    <Th>Conta</Th>
                    <Th>Descrição</Th>
                    <Th>Ações</Th>
                  </Tr>
                </Thead>

                <Tbody>
                  {data.map((movimentacao: IMovimentacao) => (
                    <Tr
                      key={movimentacao.id}
                      _hover={{ cursor: "pointer", background: "#eee" }}
                    >
                      <Td>{movimentacao.id}</Td>
                      <Td>{movimentacao.data}</Td>
                      <Td>{movimentacao.situacao}</Td>
                      <Td>{movimentacao.valor}</Td>
                      <Td>{movimentacao.tipoMovimentacao}</Td>
                      <Td>{movimentacao.categoria?.nome}</Td>
                      <Td>{movimentacao.idConta?.banco}</Td>
                      <Td>{movimentacao.descricao}</Td>
                      <Td>
                        <Menu>
                          <MenuButton
                            as={IconButton}
                            aria-label="Actions"
                            icon={<BsChevronDown />}
                            variant="ghost"
                          />
                          <MenuList>
                            <MenuItem
                              icon={<BsPencilSquare />}
                              onClick={() =>
                                onEdit(`/movimentacoes/${movimentacao.id}`)
                              }
                            >
                              Editar
                            </MenuItem>
                            <MenuItem
                              icon={<BsTrash />}
                              onClick={() => onRemove(movimentacao.id!)}
                            >
                              Remover
                            </MenuItem>
                            <MenuItem
                              icon={<BsPlusSlashMinus />}
                              onClick={() =>
                                onDoMovimentation(
                                  movimentacao.id!,
                                  movimentacao.valor,
                                  movimentacao.idConta.saldo,
                                  movimentacao.tipoMovimentacao
                                )
                              }
                            >
                              Realizar Movimentação
                            </MenuItem>
                          </MenuList>
                        </Menu>
                      </Td>
                    </Tr>
                  ))}
                </Tbody>
              </Table>
            </TableContainer>
            {apiError && <div className="alert alert-danger">{apiError}</div>}
          </Card>
        </CardBody>

        <CardFooter className="justify-content-center">
          <Button rightIcon={<BsPlusCircle />} colorScheme="purple">
            <Link to="/movimentacoes/new">Nova Movimentação</Link>
          </Button>
        </CardFooter>
      </Card>

      <Link to="/contas">Voltar para contas</Link>
    </div>
  );
}
