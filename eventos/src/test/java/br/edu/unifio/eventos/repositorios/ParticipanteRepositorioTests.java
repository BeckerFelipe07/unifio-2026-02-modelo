package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.eventos.entidades.Participante;

@SpringBootTest
public class ParticipanteRepositorioTests {

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Test
    public void deveSalvarUmParticipanteNovo() {

        Participante participante = new Participante();

        participante.setNome("João Silva");
        participante.setEmail("joao@email.com");
        participante.setTelefone("14999999999");

        participanteRepositorio.save(participante);

        assertNotNull(participante.getId());
    }

    @Test
    public void deveBuscarUmParticipantePorId() {

        Participante participante = new Participante();

        participante.setNome("Maria Souza");
        participante.setEmail("maria@email.com");
        participante.setTelefone("14988888888");

        participanteRepositorio.save(participante);

        Participante resultado = participanteRepositorio.findById(participante.getId()).orElseThrow();

        assertNotNull(resultado);
        assertEquals("Maria Souza", resultado.getNome());
        assertEquals("maria@email.com", resultado.getEmail());
        assertEquals("14988888888", resultado.getTelefone());
    }

    @Test
    public void deveBuscarTodosOsParticipantes() {

        Participante participante1 = new Participante();

        participante1.setNome("João Silva");
        participante1.setEmail("joao@email.com");
        participante1.setTelefone("14999999999");

        Participante participante2 = new Participante();

        participante2.setNome("Maria Souza");
        participante2.setEmail("maria@email.com");
        participante2.setTelefone("14988888888");

        participanteRepositorio.save(participante1);
        participanteRepositorio.save(participante2);

        List<Participante> participantes = participanteRepositorio.findAll(Sort.by("nome"));

        assertTrue(participantes.size() >= 2);
        assertTrue(participantes.stream().anyMatch(p -> p.getNome().equals("João Silva")));
        assertTrue(participantes.stream().anyMatch(p -> p.getNome().equals("Maria Souza")));
    }

    @Test
    public void deveAtualizarONomeDeUmParticipante() {

        Participante participante = new Participante();

        participante.setNome("Nome Teste");
        participante.setEmail("teste@email.com");
        participante.setTelefone("14999999999");

        participanteRepositorio.save(participante);

        participante.setNome("Outro Nome Teste");

        participanteRepositorio.save(participante);

        Participante resultado = participanteRepositorio.findById(participante.getId()).orElseThrow();

        assertEquals("Outro Nome Teste", resultado.getNome());
    }

    @Test
    public void deveExcluirUmParticipantePorId() {

        Participante participante = new Participante();

        participante.setNome("Participante Teste");
        participante.setEmail("teste@email.com");
        participante.setTelefone("14999999999");

        participanteRepositorio.save(participante);

        assertTrue(participanteRepositorio.existsById(participante.getId()));

        participanteRepositorio.deleteById(participante.getId());

        assertFalse(participanteRepositorio.existsById(participante.getId()));
    }
}