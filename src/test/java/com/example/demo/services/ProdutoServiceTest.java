package com.example.demo.services;

import com.example.demo.converter.ProdutoConverter;
import com.example.demo.exceptions.NaoEncontradoException;
import com.example.demo.exceptions.NaoExisteException;
import com.example.demo.factory.ProdutoFactory;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

@DisplayName("Service de produto")
class ProdutoServiceTest {

    private ProdutoRepository repository;
    private ProdutoConverter converter;
    private ProdutoService sut;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(ProdutoRepository.class);
        this.converter = Mockito.mock(ProdutoConverter.class);
        this.sut = new ProdutoService(repository, converter);
    }

    @Test
    @DisplayName("Deve salvar um produto com sucesso.")
    void deveSalvarUmProduto() {
        final var produtoDTOResponse = ProdutoFactory.novoProdutoDTOResponse();
        final var produto = ProdutoFactory.novoProduto();
        final var produtoDTORequest = ProdutoFactory.novoProdutoDTORequest();
        Mockito.when(converter.dtoToEntity(produtoDTORequest))
                .thenReturn(produto);
        Mockito.when(repository.save(produto))
                .thenReturn(produto);
        Mockito.when(converter.entityToDto(produto))
                .thenReturn(produtoDTOResponse);

        final var response = sut.salvarProduto(produtoDTORequest);

        Mockito.verify(converter, Mockito.times(1))
                .dtoToEntity(produtoDTORequest);
        Mockito.verify(converter, Mockito.times(1))
                .entityToDto(produto);
        Mockito.verify(repository, Mockito.times(1))
                .save(produto);
        Assertions.assertEquals(produtoDTORequest.getSku(), response.getSku());
        Assertions.assertEquals(produtoDTORequest.getNome(), response.getNome());
        Assertions.assertEquals(produtoDTORequest.getDescricao(), response.getDescricao());
        Assertions.assertEquals(produtoDTORequest.getPreco(), response.getPreco());
    }

    @Test
    @DisplayName("Deve buscar produtos com sucesso.")
    void deveBuscarProdutos() {
        final var produtoDTOResponse = ProdutoFactory.novoProdutoDTOResponse();
        final var listaProdutosResponse = List.of(produtoDTOResponse);
        final var produto = ProdutoFactory.novoProduto();
        final var listaProdutos = List.of(produto);
        Mockito.when(repository.findAll())
                .thenReturn(listaProdutos);
        Mockito.when(converter.entityToDto(listaProdutos.get(0)))
                .thenReturn(produtoDTOResponse);

        final var response = sut.buscarProdutos();

        Mockito.verify(repository, Mockito.times(1))
                .findAll();
        Assertions.assertEquals(listaProdutosResponse.get(0).getSku(), response.get(0).getSku());
        Assertions.assertEquals(listaProdutosResponse.get(0).getNome(), response.get(0).getNome());
        Assertions.assertEquals(listaProdutosResponse.get(0).getDescricao(), response.get(0).getDescricao());
        Assertions.assertEquals(listaProdutosResponse.get(0).getPreco(), response.get(0).getPreco());
    }

    @Test
    @DisplayName("Deve buscar produto com sucesso pelo id com sucesso.")
    void deveBuscarProdutoPeloIdComSucesso() {
        final var idProduto = 1L;
        final var produtoDTOResponse = ProdutoFactory.novoProdutoDTOResponse();
        final var produto = ProdutoFactory.novoProduto();
        Mockito.when(repository.findById(idProduto))
                .thenReturn(Optional.of(produto));
        Mockito.when(converter.entityToDto(produto))
                .thenReturn(produtoDTOResponse);

        final var response = sut.buscarProduto(idProduto);

        Mockito.verify(repository, Mockito.times(1))
                .findById(idProduto);
        Mockito.verify(converter, Mockito.times(1))
                .entityToDto(produto);
        Assertions.assertEquals(produtoDTOResponse.getSku(), response.getSku());
        Assertions.assertEquals(produtoDTOResponse.getNome(), response.getNome());
        Assertions.assertEquals(produtoDTOResponse.getDescricao(), response.getDescricao());
        Assertions.assertEquals(produtoDTOResponse.getPreco(), response.getPreco());
    }

    @Test
    @DisplayName("Deve lançar NaoEncontradoException ao buscar produto pelo id.")
    void deveLancarNaoEncontradoExceptionAoBuscarProdutoPeloId() {
        final var idProduto = 1L;
        Mockito.when(repository.findById(idProduto))
                .thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(NaoEncontradoException.class,
                () -> sut.buscarProduto(idProduto));

        Assertions.assertEquals("O produto de id:1 informado não foi encontrado!", exception.getMessage());
        Mockito.verify(repository, Mockito.times(1))
                .findById(idProduto);
        Mockito.verifyNoInteractions(converter);
    }

    @Test
    @DisplayName("Deve excluir um produto com sucesso.")
    void deveExcluirUmProdutoComSucesso() {
        final var idProduto = 1L;
        final var produto = ProdutoFactory.novoProduto();
        Mockito.when(repository.findById(idProduto))
                .thenReturn(Optional.of(produto));

        sut.excluir(idProduto);

        Mockito.verify(repository, Mockito.times(1))
                .findById(idProduto);
        Mockito.verify(repository, Mockito.times(1))
                .delete(produto);
    }

    @Test
    @DisplayName("Deve lançar NaoExisteException ao buscar produto pelo id.")
    void deveLancarNaoExisteExceptionAoBuscarProdutoPeloId() {
        final var idProduto = 1L;
        Mockito.when(repository.findById(idProduto))
                .thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(NaoExisteException.class,
                () -> sut.excluir(idProduto));

        Assertions.assertEquals("O produto de id:1 informado não existe!", exception.getMessage());
        Mockito.verify(repository, Mockito.times(1))
                .findById(idProduto);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Deve atualizar um produto pelo IdSku com sucesso.")
    void deveAtualizarUmProduto() {
        final var idSku = "SK1";
        final var produtoDTOResponse = ProdutoFactory.novoProdutoDTOResponse();
        final var produto = ProdutoFactory.novoProduto();
        final var produtoDTORequest = ProdutoFactory.novoProdutoDTORequest();
        Mockito.when(repository.findBySku(idSku))
                .thenReturn(Optional.of(produto));
        Mockito.when(repository.save(produto))
                .thenReturn(produto);
        Mockito.when(converter.entityToDto(produto))
                .thenReturn(produtoDTOResponse);

        final var response = sut.atualizarProduto(produtoDTORequest);

        Mockito.verify(repository, Mockito.times(1))
                .findBySku(idSku);
        Mockito.verify(repository, Mockito.times(1))
                .save(produto);
        Mockito.verify(converter, Mockito.times(1))
                .entityToDto(produto);
        Assertions.assertEquals(produtoDTORequest.getSku(), response.getSku());
        Assertions.assertEquals(produtoDTORequest.getNome(), response.getNome());
        Assertions.assertEquals(produtoDTORequest.getDescricao(), response.getDescricao());
        Assertions.assertEquals(produtoDTORequest.getPreco(), response.getPreco());
    }

    @Test
    @DisplayName("Deve lançar NaoEncontradoException quando não encontrar produto a ser atulizado pelo IdSku.")
    void deveLancarNaoEncontradoExceptionQuandoNaoEncontrarProdutoPeloIdSku() {
        final var idSku = "SK1";
        final var produtoDTORequest = ProdutoFactory.novoProdutoDTORequest();
        Mockito.when(repository.findBySku(idSku))
                .thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(NaoEncontradoException.class,
                () -> sut.atualizarProduto(produtoDTORequest));

        Assertions.assertEquals("O produto informado não foi encontrado!", exception.getMessage());
        Mockito.verify(repository, Mockito.times(1))
                .findBySku(idSku);
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoInteractions(converter);
    }

}
