import { useState, useEffect } from "react";
import { Card, CardHeader, CardBody, Text, Heading, SimpleGrid, CardFooter, Button, TableContainer, Table, Tbody, Tr, Td, Thead, Th} from '@chakra-ui/react';
import { BsArrowRight } from "react-icons/bs";
import { IConta, IMovimentacao } from "../../commons/interfaces";
import { Link, useNavigate } from "react-router-dom";
import ContaService from "../../service/ContaService";
import MovimentacaoService from "../../service/MovimentacaoService";

export function HomePage(){
    const [data, setData] = useState<IConta[]>([]);
    const [dataMovDesp, setDataMovDesp] = useState<IMovimentacao[]>([]);
    const [dataMovRec, setDataMovRec] = useState<IMovimentacao[]>([]);
    const [saldoTotal, setSaldoTotal] = useState();
    const [apiError, setApiError] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        loadData();
        calculaSaldoTotal();
    }, []);

    const loadData = () => {
        ContaService.findAll()
        .then((response) => {
            setData(response.data);
            setApiError("");
        })
        .catch((error) => {
            setApiError("Falha ao carregar a lista de contas.")
        });

        MovimentacaoService.findDespesa()
        .then((responseMovDesp) => {
            setDataMovDesp(responseMovDesp.data);
            setApiError("");
        })
        .catch((error) => {
            setApiError("Falha ao carregar a lista de despesas.")
        });

        MovimentacaoService.findReceita()
        .then((responseMovRec) => {
            setDataMovRec(responseMovRec.data);
            setApiError("");
        })
        .catch((error) => {
            setApiError("Falha ao carregar a lista de despesas.")
        });
    };

    const calculaSaldoTotal = () =>{
        ContaService.saldoTotal()
        .then((response) => {
            setSaldoTotal(response.data);
            setApiError("");
        })
        .catch((error) => {
            setApiError("Falha ao carregar saldo total.")
        })
    }

    return(
        <div className="container">
            <h1 className="fs-2 text-center">Home Page</h1>

            <Card className='bg-light'>
                <CardHeader>
                    <Heading size='lg' className="text-center">
                        Contas
                    </Heading>

                    <Card>
                        <CardHeader>
                            <Heading size='md'>Lista de contas</Heading>
                        </CardHeader>

                        <TableContainer>
                            <Table>
                                <Thead>
                                    <Tr>
                                    <Th>Banco</Th>
                                    <Th isNumeric>Saldo</Th>
                                    </Tr>
                                </Thead>

                                <Tbody>
                                    {data.map((conta: IConta) => (
                                        <Tr key={conta.id}
                                            _hover={{ cursor: "pointer", background: "#eee" }} >

                                            <Td>{conta.banco}</Td>
                                            <Td className="text-end" isNumeric>R$ {conta.saldo}</Td>
                                        </Tr>
                                    ))}
                                    <Td>Saldo total:</Td>
                                    <Td className="text-end" isNumeric>R$ {saldoTotal} </Td>
                                </Tbody>
                            </Table>
                        </TableContainer>

                        {apiError && <div className="alert alert-danger">{apiError}</div>}

                    </Card>

                    <CardFooter className='justify-content-center'>
                        <Button rightIcon={<BsArrowRight/>} colorScheme='blue'>
                            <Link to="/contas">
                                Ir para contas
                            </Link>
                        </Button>
                    </CardFooter>
                </CardHeader>
            </Card>

            <Card className='bg-light mt-3'>
                <CardHeader>
                    <Heading size='lg' className="text-center">
                        Últimas 5 despesas
                    </Heading>

                    <Card>
                        <CardHeader>
                            <Heading size='md'>Lista de despesas</Heading>
                        </CardHeader>

                        <TableContainer>
                            <Table>
                                <Thead>
                                    <Tr>
                                    <Th>Data</Th>
                                    <Th>Descrição</Th>
                                    <Th>Tipo Movimentação</Th>
                                    <Th isNumeric>Valor</Th>
                                    </Tr>
                                </Thead>

                                <Tbody>
                                    {dataMovDesp.map((moimentacao: IMovimentacao) => (
                                        <Tr key={moimentacao.id}
                                            _hover={{ cursor: "pointer", background: "#eee" }} >
                                            
                                            <Td>{moimentacao.data}</Td>
                                            <Td>{moimentacao.descricao}</Td>
                                            <Td>{moimentacao.tipoMovimentacao}</Td>
                                            <Td className="text-end" isNumeric>R$ {moimentacao.valor}</Td>
                                        </Tr>
                                    ))}
                                </Tbody>
                            </Table>
                        </TableContainer>

                        {apiError && <div className="alert alert-danger">{apiError}</div>}
                    </Card>
                </CardHeader>
            </Card>

            <Card className='bg-light mt-3'>
                <CardHeader>
                    <Heading size='lg' className="text-center">
                        Últimas 5 receitas
                    </Heading>

                    <Card>
                        <CardHeader>
                            <Heading size='md'>Lista de receitas</Heading>
                        </CardHeader>

                        <TableContainer>
                            <Table>
                                <Thead>
                                    <Tr>
                                    <Th>Data</Th>
                                    <Th>Descrição</Th>
                                    <Th>Tipo Movimentação</Th>
                                    <Th isNumeric>Valor</Th>
                                    </Tr>
                                </Thead>

                                <Tbody>
                                    {dataMovRec.map((moimentacao: IMovimentacao) => (
                                        <Tr key={moimentacao.id}
                                            _hover={{ cursor: "pointer", background: "#eee" }} >
                                            
                                            <Td>{moimentacao.data}</Td>
                                            <Td>{moimentacao.descricao}</Td>
                                            <Td>{moimentacao.tipoMovimentacao}</Td>
                                            <Td className="text-end" isNumeric>R$ {moimentacao.valor}</Td>
                                        </Tr>
                                    ))}
                                </Tbody>
                            </Table>
                        </TableContainer>

                        {apiError && <div className="alert alert-danger">{apiError}</div>}
                    </Card>
                </CardHeader>
            </Card>
        </div>
    )
}