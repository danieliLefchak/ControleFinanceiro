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
import { useEffect, useState } from "react";
import {
  BsCashCoin,
  BsChevronDown,
  BsPencilSquare,
  BsPlusCircle,
  BsTrash,
} from "react-icons/bs";
import { IConta } from "../../commons/interfaces";
import { Link, useNavigate } from "react-router-dom";
import ContaService from "../../service/ContaService";

/*{<BsChevronUp size={20} />} */

export function ListContasPage() {
  const [data, setData] = useState<IConta[]>([]);
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  var found: String;

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    ContaService.findAll()
      .then((response) => {
        setData(response.data);
        setApiError("");
      })
      .catch((error) => {
        setApiError("Falha ao carregar a lista de contas.");
      });
  };

  const onEdit = (url: string) => {
    navigate(url);
  };

  const onRemove = (id: number) => {
    ContaService.remove(id)
      .then((response) => {
        loadData();
        setApiError("");
      })
      .catch((error) => {
        setApiError("Falha ao remover o Conta.");
      });
  };

  const onFindMovimentacao = (urlMov: string) => {
    navigate(urlMov);
  };

  return (
    <div className="container">
      <Card className="bg-light mb-3">
        <CardHeader>
          <Heading className="fs-2 text-center">Contas</Heading>
        </CardHeader>

        <CardBody>
          <Card>
            <CardHeader>
              <Heading size="md">Lista de contas</Heading>
            </CardHeader>

            <TableContainer>
              <Table>
                <Thead>
                  <Tr>
                    <Th>#</Th>
                    <Th>Banco</Th>
                    <Th>Agencia</Th>
                    <Th>Número</Th>
                    <Th>Tipo da conta</Th>
                    <Th isNumeric>Saldo</Th>
                    <Th>Ações</Th>
                  </Tr>
                </Thead>

                <Tbody>
                  {data.map((conta: IConta) => (
                    <Tr
                      key={conta.id}
                      _hover={{ cursor: "pointer", background: "#eee" }}
                    >
                      <Td>{conta.id}</Td>
                      <Td>{conta.banco}</Td>
                      <Td>{conta.agencia}</Td>
                      <Td>{conta.numero}</Td>
                      <Td>{conta.tipoConta}</Td>
                      <Td className="text-end" isNumeric>
                        R$ {conta.saldo}
                      </Td>
                      <Td>
                        <Menu>
                          <MenuButton 
                            as={IconButton}
                            aria-label="Actions"
                            icon={<BsChevronDown size={20} />}
                            variant="ghost"
                          />
                          <MenuList>
                            <MenuItem
                              icon={<BsPencilSquare />}
                              onClick={() => onEdit(`/contas/${conta.id}`)}
                            >
                              Editar
                            </MenuItem>
                            <MenuItem
                              icon={<BsTrash />}
                              onClick={() => onRemove(conta.id!)}
                            >
                              Remover
                            </MenuItem>
                            <MenuItem
                              icon={<BsCashCoin />}
                              onClick={() => onFindMovimentacao(`/movimentacoes/findAll/${conta.id}`)}
                            >
                              Ver Movimentações
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
          <Button onClick={() => onEdit(`/contas/new`)} rightIcon={<BsPlusCircle />} colorScheme="purple">
            Nova conta 
          </Button>
        </CardFooter>
      </Card>

      <Link to="/">
        Voltar para home
      </Link>
    </div>
  );
}
