package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.eventos.entidades.Inscricao;

@SpringBootTest
public class InscricaoRepositorioTests {

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Test
    public void deveSalvarUmaInscricaoNova() {

        Inscricao inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("ATIVA");

        inscricaoRepositorio.save(inscricao);

        assertNotNull(inscricao.getId());
    }

    @Test
    public void deveBuscarUmaInscricaoPorId() {

        Inscricao inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("ATIVA");

        inscricaoRepositorio.save(inscricao);

        Inscricao resultado = inscricaoRepositorio.findById(inscricao.getId()).orElseThrow();

        assertNotNull(resultado);
        assertEquals("ATIVA", resultado.getStatus());
    }

    @Test
    public void deveBuscarTodasAsInscricoes() {

        Inscricao inscricao1 = new Inscricao();

        inscricao1.setDataInscricao(LocalDateTime.now());
        inscricao1.setStatus("ATIVA");

        Inscricao inscricao2 = new Inscricao();

        inscricao2.setDataInscricao(LocalDateTime.now());
        inscricao2.setStatus("CANCELADA");

        inscricaoRepositorio.save(inscricao1);
        inscricaoRepositorio.save(inscricao2);

        List<Inscricao> inscricoes = inscricaoRepositorio.findAll(Sort.by("status"));

        assertTrue(inscricoes.size() >= 2);
        assertTrue(inscricoes.stream().anyMatch(i -> i.getStatus().equals("ATIVA")));
        assertTrue(inscricoes.stream().anyMatch(i -> i.getStatus().equals("CANCELADA")));
    }

    @Test
    public void deveAtualizarOStatusDeUmaInscricao() {

        Inscricao inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("ATIVA");

        inscricaoRepositorio.save(inscricao);

        inscricao.setStatus("CANCELADA");

        inscricaoRepositorio.save(inscricao);

        Inscricao resultado = inscricaoRepositorio.findById(inscricao.getId()).orElseThrow();

        assertEquals("CANCELADA", resultado.getStatus());
    }

    @Test
    public void deveExcluirUmaInscricaoPorId() {

        Inscricao inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("ATIVA");

        inscricaoRepositorio.save(inscricao);

        assertTrue(inscricaoRepositorio.existsById(inscricao.getId()));

        inscricaoRepositorio.deleteById(inscricao.getId());

        assertFalse(inscricaoRepositorio.existsById(inscricao.getId()));
    }
}