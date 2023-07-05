package com.example.demo.service;

import com.example.demo.converter.ProdutoConverter;
import com.example.demo.domain.Produto;
import com.example.demo.dto.ProdutoDTORequest;
import com.example.demo.dto.ProdutoDTOResponse;
import com.example.demo.exceptions.NaoEncontradoException;
import com.example.demo.exceptions.NaoExisteException;
import com.example.demo.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoConverter converter;

    public ProdutoDTOResponse salvarProduto(ProdutoDTORequest produtoDtoRequest) {
        Produto produto = converter.dtoToEntity(produtoDtoRequest);
        Produto produtoSalvo = repository.save(produto);
        return converter.entityToDto(produtoSalvo);
    }

    public List<ProdutoDTOResponse> buscarTodosProdutos() {
        List<Produto> produtos = repository.findAll();
        List<ProdutoDTOResponse> produtoDto = new ArrayList<>();
        for(Produto produto : produtos) {
            produtoDto.add(converter.entityToDto(produto));
        }
        return produtoDto;
    }

    public ProdutoDTOResponse buscarProduto(Long id) throws NaoEncontradoException{
        Optional<Produto> produto = repository.findById(id);

        if(produto.isPresent()) {
            return converter.entityToDto(produto.get());
        }
        throw new NaoEncontradoException(String.format("O produto de id:%s informado não foi encontrado!", id));
    }

    public void excluir(Long id) throws NaoExisteException {
        Optional<Produto> produto = repository.findById(id);

        if(produto.isPresent()) {
            repository.delete(produto.get());
        }else {
            throw new NaoExisteException(String.format("O produto de id:%s informado não existe!", id));
        }
    }

    public ProdutoDTOResponse atualizarProduto(ProdutoDTORequest produtoDtoRequest) {
        Produto validaExistenciaProduto =
                repository.findBySku(produtoDtoRequest.getSku())
                        .orElseThrow(() -> new NaoEncontradoException("O produto informado não foi encontrado!"));

        validaExistenciaProduto.setSku(produtoDtoRequest.getSku());
        validaExistenciaProduto.setNome(produtoDtoRequest.getNome());
        validaExistenciaProduto.setDescricao(produtoDtoRequest.getDescricao());
        validaExistenciaProduto.setQuantidade(produtoDtoRequest.getQuantidade());
        validaExistenciaProduto.setPreco(produtoDtoRequest.getPreco());
        return converter.entityToDto(repository.save(validaExistenciaProduto));
    }

}
