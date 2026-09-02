insert into categoria (nome, descricao) values ('Show', 'Rock');
insert into categoria (nome, descricao) values ('Congresso', 'Tecnologia');
insert into categoria (nome, descricao) values ('Festival', 'Música Eletrônica');
insert into categoria (nome, descricao) values ('Palestra', 'Educação e Carreira');
insert into categoria (nome, descricao) values ('Feira', 'Gastronomia');

insert into local (nome, endereco, capacidade) values ('Garden Halls', 'Avenida São Rio de Janeiro, 331 - Bairro Paraiso - Pindamanhangaba', 50000);
insert into local (nome, endereco, capacidade) values ('Arena Central', 'Rua das Palmeiras, 120 - Centro - Bauru', 15000);
insert into local (nome, endereco, capacidade) values ('Espaço Convention', 'Avenida Brasil, 900 - Jardim América - São Paulo', 8000);
insert into local (nome, endereco, capacidade) values ('Teatro Municipal', 'Praça da Sé, 45 - Centro - Campinas', 1200);
insert into local (nome, endereco, capacidade) values ('Parque de Exposições', 'Rodovia SP-300, km 12 - Marília', 30000);

insert into palestrante (nome, mini_bio, email) values ('Felipe Torini', 'Cantor e compositor de Rock', 'felipetoriniagency@gmail.com');
insert into palestrante (nome, mini_bio, email) values ('Mariana Costa', 'Especialista em Inteligência Artificial', 'mariana.costa@techtalks.com');
insert into palestrante (nome, mini_bio, email) values ('DJ Ricardo Alves', 'Produtor musical de eletrônica', 'ricardo.alves@festivalmusic.com');
insert into palestrante (nome, mini_bio, email) values ('Juliana Prado', 'Consultora de carreira e RH', 'juliana.prado@carreirapro.com');
insert into palestrante (nome, mini_bio, email) values ('Chef André Lima', 'Chef renomado especialista em gastronomia regional', 'andre.lima@sabordochef.com');

insert into participante (nome, email, telefone) values ('Felipe Becker', 'felipebecker@gmail.com', '14 99933-3399');
insert into participante (nome, email, telefone) values ('Ana Souza', 'ana.souza@gmail.com', '14 98123-5566');
insert into participante (nome, email, telefone) values ('Bruno Lima', 'bruno.lima@gmail.com', '14 97654-3322');
insert into participante (nome, email, telefone) values ('Carla Mendes', 'carla.mendes@gmail.com', '14 96543-2211');
insert into participante (nome, email, telefone) values ('Diego Santos', 'diego.santos@gmail.com', '14 95432-1100');

insert into evento (nome, descricao, data_inicio, data_fim, capacidade, status, local_id, palestrante_id) values ('Rock Rio Pardo', 'Show de Rock em Rio Pardo', '2026-08-27', '2026-08-28', 50000, 'em andamento', 1, 1);
insert into evento (nome, descricao, data_inicio, data_fim, capacidade, status, local_id, palestrante_id) values ('TechTalks IA', 'Congresso sobre Inteligência Artificial', '2026-09-10', '2026-09-11', 15000, 'agendado', 2, 2);
insert into evento (nome, descricao, data_inicio, data_fim, capacidade, status, local_id, palestrante_id) values ('Festival Eletrônico SP', 'Festival de música eletrônica', '2026-10-05', '2026-10-06', 8000, 'agendado', 3, 3);
insert into evento (nome, descricao, data_inicio, data_fim, capacidade, status, local_id, palestrante_id) values ('Palestra Carreira 2026', 'Palestra sobre desenvolvimento de carreira', '2026-09-20', '2026-09-20', 1200, 'agendado', 4, 4);
insert into evento (nome, descricao, data_inicio, data_fim, capacidade, status, local_id, palestrante_id) values ('Feira Gastronômica Marília', 'Feira de gastronomia regional', '2026-11-01', '2026-11-03', 30000, 'agendado', 5, 5);

insert into inscricao (data_inscricao, status, evento_id, participante_id) values ('2026-08-20', 'Pagamento concluído', 1, 1);
insert into inscricao (data_inscricao, status, evento_id, participante_id) values ('2026-09-01', 'Pagamento concluído', 2, 2);
insert into inscricao (data_inscricao, status, evento_id, participante_id) values ('2026-09-25', 'Pagamento pendente', 3, 3);
insert into inscricao (data_inscricao, status, evento_id, participante_id) values ('2026-09-15', 'Pagamento concluído', 4, 4);
insert into inscricao (data_inscricao, status, evento_id, participante_id) values ('2026-10-20', 'Pagamento concluído', 5, 5);