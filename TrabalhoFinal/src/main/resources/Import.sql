--TABELA USERS
--Senha 123
insert into users (cpf, email, username, telefone, password) values ('111.111.111-11', 'danieli_1234@test.com', 'Danieli', '2055550125', '$2a$10$zVJ0LSYTBEhX7LEc1b2ND.07mCsRJo4NNfmfP6bQrUpH3DEBZuvA2');
insert into users (cpf, email, username, telefone, password) values ('222.222.222-22', 'panda_1234@test.com', 'Panda', '3655944612', '$2a$10$zVJ0LSYTBEhX7LEc1b2ND.07mCsRJo4NNfmfP6bQrUpH3DEBZuvA2');
--TABELA CONTA
--tp_conta: 0 corrente, 1 poupança, 2 investimento, 3 casa;
insert into conta (agencia, banco, numero, saldo, tipo_conta, user_id) values ('012', 'Bradesco', 15, '1500.00', 'Conta Poupanca', 1);
insert into conta (agencia, banco, numero, saldo, tipo_conta, user_id) values ('025', 'Brasil', 36, '4562.00', 'Conta Corrente', 1);
insert into conta (agencia, banco, numero, saldo, tipo_conta, user_id) values ('166', 'Sicred', 95, '20000.00', 'Investimento', 2);
insert into conta (agencia, banco, numero, saldo, tipo_conta, user_id) values ('000', 'Casa', 25, '1565.00', 'Casa', 2);
--TABELA CATEGORIA
insert into categoria (nome) values ('Prestações');
insert into categoria (nome) values ('Pagar Aluguel');
insert into categoria (nome) values ('Pagar Energia elétrica');
insert into categoria (nome) values ('Pagara Água');
insert into categoria (nome) values ('Pagar Internet');
insert into categoria (nome) values ('Pagar Emprestimo');
insert into categoria (nome) values ('Receber Emprestimo');
insert into categoria (nome) values ('Receber Aluguel');
insert into categoria (nome) values ('Receber Salario');
insert into categoria (nome) values ('Receber Férias');
--TABELA MOVIMENTAÇÕES
insert into movimentacao (data, descricao, situacao, tipo_movimentacao, valor, categoria_id, conta_id) values ('2023-05-15', 'Pagamento da conta de internet', 'Pendente', 'Despesa', '100.00', 4, 3);
insert into movimentacao (data, descricao, situacao, tipo_movimentacao, valor, categoria_id, conta_id) values ('2023-05-15', 'Pagamento da prestação', 'Pendente', 'Despesa', '65.00', 1, 4);
insert into movimentacao (data, descricao, situacao, tipo_movimentacao, valor, categoria_id, conta_id) values ('2023-05-22', 'Receber valor emprestado', 'Pendente', 'Receita', '1000.00', 6, 4);

