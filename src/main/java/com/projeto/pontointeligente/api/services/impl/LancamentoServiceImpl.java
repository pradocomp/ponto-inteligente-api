package com.projeto.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.pontointeligente.api.entities.Lancamento;
import com.projeto.pontointeligente.api.repositories.LancamentoRepository;
import com.projeto.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Cadastra um novo lancamento {} na base de dados", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Busca um lancamento pelo id {}", id);
		return this.lancamentoRepository.findById(id);
	}

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, Pageable paging) {
		log.info("Busca um lancamento para um ID Funcionario {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, paging);
	}

	@Override
	public void remover(Long id) {
		log.info("Remove um lancamento com ID {}", id);
		this.lancamentoRepository.deleteById(id);
	}

}
