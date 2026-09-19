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

import br.edu.unifio.eventos.entidades.Palestrante;

@SpringBootTest
public class PalestranteRepositorioTests {

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    @Test
    public void deveSalvarUmPalestranteNovo() {

        Palestrante palestrante = new Palestrante();

        palestrante.setNome("Carlos Silva");
        palestrante.setMiniBio("Especialista em tecnologia");
        palestrante.setEmail("carlos@email.com");

        palestranteRepositorio.save(palestrante);

        assertNotNull(palestrante.getId());
    }

    @Test
    public void deveBuscarUmPalestrantePorId() {

        Palestrante palestrante = new Palestrante();

        palestrante.setNome("Ana Souza");
        palestrante.setMiniBio("Professora e pesquisadora");
        palestrante.setEmail("ana@email.com");

        palestranteRepositorio.save(palestrante);

        Palestrante resultado = palestranteRepositorio.findById(palestrante.getId()).orElseThrow();

        assertNotNull(resultado);
        assertEquals("Ana Souza", resultado.getNome());
        assertEquals("Professora e pesquisadora", resultado.getMiniBio());
        assertEquals("ana@email.com", resultado.getEmail());
    }

    @Test
    public void deveBuscarTodosOsPalestrantes() {

        Palestrante palestrante1 = new Palestrante();

        palestrante1.setNome("Carlos Silva");
        palestrante1.setMiniBio("Especialista em tecnologia");
        palestrante1.setEmail("carlos@email.com");

        Palestrante palestrante2 = new Palestrante();

        palestrante2.setNome("Ana Souza");
        palestrante2.setMiniBio("Professora e pesquisadora");
        palestrante2.setEmail("ana@email.com");

        palestranteRepositorio.save(palestrante1);
        palestranteRepositorio.save(palestrante2);

        List<Palestrante> palestrantes = palestranteRepositorio.findAll(Sort.by("nome"));

        assertTrue(palestrantes.size() >= 2);
        assertTrue(palestrantes.stream().anyMatch(p -> p.getNome().equals("Carlos Silva")));
        assertTrue(palestrantes.stream().anyMatch(p -> p.getNome().equals("Ana Souza")));
    }

    @Test
    public void deveAtualizarONomeDeUmPalestrante() {

        Palestrante palestrante = new Palestrante();

        palestrante.setNome("Nome Teste");
        palestrante.setMiniBio("Mini Bio Teste");
        palestrante.setEmail("teste@email.com");

        palestranteRepositorio.save(palestrante);

        palestrante.setNome("Outro Nome Teste");

        palestranteRepositorio.save(palestrante);

        Palestrante resultado = palestranteRepositorio.findById(palestrante.getId()).orElseThrow();

        assertEquals("Outro Nome Teste", resultado.getNome());
    }

    @Test
    public void deveExcluirUmPalestrantePorId() {

        Palestrante palestrante = new Palestrante();

        palestrante.setNome("Palestrante Teste");
        palestrante.setMiniBio("Mini Bio Teste");
        palestrante.setEmail("teste@email.com");

        palestranteRepositorio.save(palestrante);

        assertTrue(palestranteRepositorio.existsById(palestrante.getId()));

        palestranteRepositorio.deleteById(palestrante.getId());

        assertFalse(palestranteRepositorio.existsById(palestrante.getId()));
    }
}