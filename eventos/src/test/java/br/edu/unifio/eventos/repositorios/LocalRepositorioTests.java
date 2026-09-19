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

import br.edu.unifio.eventos.entidades.Local;

@SpringBootTest
public class LocalRepositorioTests {

    @Autowired
    private LocalRepositorio localRepositorio;

    @Test
    public void deveSalvarUmLocalNovo() {

        Local local = new Local();

        local.setNome("Auditório Principal");
        local.setEndereco("Rua das Flores, 100");
        local.setCapacidade(300);

        localRepositorio.save(local);

        assertNotNull(local.getId());
    }

    @Test
    public void deveBuscarUmLocalPorId() {

        Local local = new Local();

        local.setNome("Sala de Eventos");
        local.setEndereco("Rua Brasil, 200");
        local.setCapacidade(150);

        localRepositorio.save(local);

        Local resultado = localRepositorio.findById(local.getId()).orElseThrow();

        assertNotNull(resultado);
        assertEquals("Sala de Eventos", resultado.getNome());
        assertEquals("Rua Brasil, 200", resultado.getEndereco());
        assertEquals(150, resultado.getCapacidade());
    }

    @Test
    public void deveBuscarTodosOsLocais() {

        Local local1 = new Local();

        local1.setNome("Auditório Principal");
        local1.setEndereco("Rua das Flores, 100");
        local1.setCapacidade(300);

        Local local2 = new Local();

        local2.setNome("Sala de Eventos");
        local2.setEndereco("Rua Brasil, 200");
        local2.setCapacidade(150);

        localRepositorio.save(local1);
        localRepositorio.save(local2);

        List<Local> locais = localRepositorio.findAll(Sort.by("nome"));

        assertTrue(locais.size() >= 2);
        assertTrue(locais.stream().anyMatch(l -> l.getNome().equals("Auditório Principal")));
        assertTrue(locais.stream().anyMatch(l -> l.getNome().equals("Sala de Eventos")));
    }

    @Test
    public void deveAtualizarONomeDeUmLocal() {

        Local local = new Local();

        local.setNome("Nome Teste");
        local.setEndereco("Endereço Teste");
        local.setCapacidade(100);

        localRepositorio.save(local);

        local.setNome("Outro Nome Teste");

        localRepositorio.save(local);

        Local resultado = localRepositorio.findById(local.getId()).orElseThrow();

        assertEquals("Outro Nome Teste", resultado.getNome());
    }

    @Test
    public void deveExcluirUmLocalPorId() {

        Local local = new Local();

        local.setNome("Local Teste");
        local.setEndereco("Endereço Teste");
        local.setCapacidade(100);

        localRepositorio.save(local);

        assertTrue(localRepositorio.existsById(local.getId()));

        localRepositorio.deleteById(local.getId());

        assertFalse(localRepositorio.existsById(local.getId()));
    }
}