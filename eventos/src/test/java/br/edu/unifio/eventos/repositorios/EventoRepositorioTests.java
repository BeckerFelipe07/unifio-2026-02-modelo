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

import br.edu.unifio.eventos.entidades.Evento;

@SpringBootTest
public class EventoRepositorioTests {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Test
    public void deveSalvarUmEventoNovo() {

        Evento evento = new Evento();

        evento.setNome("Evento de Tecnologia");
        evento.setDescricao("Evento sobre tecnologia e inovação");
        evento.setDataInicio(LocalDateTime.of(2026, 10, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 10, 10, 22, 0));
        evento.setCapacidade(100);
        evento.setStatus("ATIVO");

        eventoRepositorio.save(evento);

        assertNotNull(evento.getId());
    }

    @Test
    public void deveBuscarUmEventoPorId() {

        Evento evento = new Evento();

        evento.setNome("Evento de Esportes");
        evento.setDescricao("Evento esportivo");
        evento.setDataInicio(LocalDateTime.of(2026, 11, 10, 14, 0));
        evento.setDataFim(LocalDateTime.of(2026, 11, 10, 18, 0));
        evento.setCapacidade(200);
        evento.setStatus("ATIVO");

        eventoRepositorio.save(evento);

        Evento resultado = eventoRepositorio.findById(evento.getId()).orElseThrow();

        assertNotNull(resultado);
        assertEquals("Evento de Esportes", resultado.getNome());
        assertEquals("Evento esportivo", resultado.getDescricao());
        assertEquals(200, resultado.getCapacidade());
        assertEquals("ATIVO", resultado.getStatus());
    }

    @Test
    public void deveBuscarTodosOsEventos() {

        Evento evento1 = new Evento();
        evento1.setNome("Evento de Tecnologia");
        evento1.setDescricao("Evento de tecnologia");
        evento1.setDataInicio(LocalDateTime.of(2026, 10, 10, 19, 0));
        evento1.setDataFim(LocalDateTime.of(2026, 10, 10, 22, 0));
        evento1.setCapacidade(100);
        evento1.setStatus("ATIVO");

        Evento evento2 = new Evento();
        evento2.setNome("Evento de Esportes");
        evento2.setDescricao("Evento esportivo");
        evento2.setDataInicio(LocalDateTime.of(2026, 11, 10, 14, 0));
        evento2.setDataFim(LocalDateTime.of(2026, 11, 10, 18, 0));
        evento2.setCapacidade(200);
        evento2.setStatus("ATIVO");

        eventoRepositorio.save(evento1);
        eventoRepositorio.save(evento2);

        List<Evento> eventos = eventoRepositorio.findAll(Sort.by("nome"));

        assertTrue(eventos.size() >= 2);
        assertTrue(eventos.stream().anyMatch(e -> e.getNome().equals("Evento de Tecnologia")));
        assertTrue(eventos.stream().anyMatch(e -> e.getNome().equals("Evento de Esportes")));
    }

    @Test
    public void deveAtualizarONomeDeUmEvento() {

        Evento evento = new Evento();

        evento.setNome("Nome Teste");
        evento.setDescricao("Descrição Teste");
        evento.setDataInicio(LocalDateTime.of(2026, 10, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 10, 10, 22, 0));
        evento.setCapacidade(100);        
        evento.setStatus("ATIVO");

        eventoRepositorio.save(evento);

        evento.setNome("Outro Nome Teste");

        eventoRepositorio.save(evento);
        Evento resultado = eventoRepositorio.findById(evento.getId()).orElseThrow();

        assertEquals("Outro Nome Teste", resultado.getNome());
    }

    @Test
    public void deveExcluirUmEventoPorId() {

        Evento evento = new Evento();

        evento.setNome("Evento Teste");
        evento.setDescricao("Descrição Teste");
        evento.setDataInicio(LocalDateTime.of(2026, 10, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 10, 10, 22, 0));
        evento.setCapacidade(100);
        evento.setStatus("ATIVO");

        eventoRepositorio.save(evento);

        assertTrue(eventoRepositorio.existsById(evento.getId()));

        eventoRepositorio.deleteById(evento.getId());

        assertFalse(eventoRepositorio.existsById(evento.getId()));
    }
}
