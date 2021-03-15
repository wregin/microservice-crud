package com.springmicro.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springmicro.crud.data.vo.ProdutoVO;
import com.springmicro.crud.entity.Produto;
import com.springmicro.crud.exception.ResourceNotFoundException;
import com.springmicro.crud.message.ProdutoSendMessage;
import com.springmicro.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ProdutoSendMessage produtoSendMessage;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage) {
		this.produtoRepository = produtoRepository;
		this.produtoSendMessage = produtoSendMessage;
	}

	public ProdutoVO create(ProdutoVO produtoVO) {
		ProdutoVO produtoVORetorno = ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
		produtoSendMessage.sendMessage(produtoVORetorno);
		return produtoVORetorno;
	}

	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertProdutoVO);
	}

	private ProdutoVO convertProdutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}

	public ProdutoVO findById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ID não foi localizado"));
		return ProdutoVO.create(entity);
	}

	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> optionalProduto = produtoRepository.findById(produtoVO.getId()); 

		if (!optionalProduto.isPresent()) {
			new ResourceNotFoundException("Produto não foi localizado");
		}

		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));

	}

	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ID não foi localizado"));

		produtoRepository.delete(entity);

	}

}
