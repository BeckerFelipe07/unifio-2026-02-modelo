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

import br.edu.unifio.eventos.entidades.Categoria;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveSalvarUmaCategoriaNova() {

        Categoria categoria = new Categoria();

        categoria.setNome("Tecnologia");
        categoria.setDescricao("Eventos relacionados à tecnologia");

        categoriaRepositorio.save(categoria);

        assertNotNull(categoria.getId());
    }

    @Test
    public void deveBuscarUmaCategoriaPorId() {

        Categoria categoria = new Categoria();

        categoria.setNome("Esportes");
        categoria.setDescricao("Eventos esportivos");

        categoriaRepositorio.save(categoria);

        Categoria resultado = categoriaRepositorio.findById(categoria.getId()).orElseThrow();

        assertNotNull(resultado);
        assertEquals("Esportes", resultado.getNome());
        assertEquals("Eventos esportivos", resultado.getDescricao());
    }

    @Test
    public void deveBuscarTodasAsCategorias() {

        Categoria categoria1 = new Categoria();
        categoria1.setNome("Tecnologia");
        categoria1.setDescricao("Eventos de tecnologia");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Esportes");
        categoria2.setDescricao("Eventos esportivos");

        categoriaRepositorio.save(categoria1);
        categoriaRepositorio.save(categoria2);

        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("nome"));

        assertTrue(categorias.size() >= 2);
        assertTrue(categorias.stream().anyMatch(c -> c.getNome().equals("Tecnologia")));
        assertTrue(categorias.stream().anyMatch(c -> c.getNome().equals("Esportes")));
    }

    @Test
    public void deveAtualizarONomeDeUmaCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Nome Teste");
        categoria.setDescricao("Descrição Teste");

        categoriaRepositorio.save(categoria);

        categoria.setNome("Outro Nome Teste");

        categoriaRepositorio.save(categoria);

        Categoria resultado = categoriaRepositorio.findById(categoria.getId()).orElseThrow();
        assertEquals("Outro Nome Teste", resultado.getNome());
    }

    @Test
    public void deveExcluirUmaCategoriaPorId() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Teste");
        categoria.setDescricao("Descrição Teste");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        categoriaRepositorio.deleteById(categoria.getId());
        assertFalse(categoriaRepositorio.existsById(categoria.getId()));
    }
}