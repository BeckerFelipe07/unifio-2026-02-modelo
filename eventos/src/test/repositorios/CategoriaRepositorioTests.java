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

        var categoria = new Categoria();

        categoria.setNome("Tecnologia");
        categoria.setDescricao("Eventos relacionados à tecnologia");

        System.out.println("ID Antes: " + categoria.getId());

        categoriaRepositorio.save(categoria);

        System.out.println("ID Depois: " + categoria.getId());

        assertNotNull(categoria.getId());
    }

    @Test
    public void deveBuscarUmaCategoriaPorId() {

        Categoria categoria = categoriaRepositorio.findById(Integer.parseInt("1")).orElseThrow();

        assertNotNull(categoria);
        assertEquals("Tecnologia", categoria.getNome());
    }

    @Test
    public void deveBuscarTodasAsCategorias() {

        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("nome"));

        assertEquals(3, categorias.size());
        assertEquals("Tecnologia", categorias.get(0).getNome());
    }

    @Test
    public void deveExcluirUmaCategoriaPorId() {

        Categoria categoria = new Categoria();

        categoria.setNome("Nome Teste");
        categoria.setDescricao("Descrição Teste");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        categoriaRepositorio.deleteById(categoria.getId());

        assertFalse(categoriaRepositorio.existsById(categoria.getId()));
    }

    @Test
    public void deveAtualizarONomeDeUmaCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Nome Teste");
        categoria.setDescricao("Descrição Teste");
        categoriaRepositorio.save(categoria);

        categoria.setNome("Outro Nome Teste");
        categoriaRepositorio.save(categoria);

        assertEquals("Outro Nome Teste",categoriaRepositorio.findById(categoria.getId()).orElseThrow().getNome());
    }
}