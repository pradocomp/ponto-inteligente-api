package com.projeto.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.projeto.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {
	
	/**
	 * Cadastra um lancamento na base de dados
	 * @param lancamento
	 * @return
	 */
	Lancamento persistir(Lancamento lancamento);
	
	/**
	 * Retorna um lancamento por id
	 * @param empresa
	 * @return
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	/**
	 * Retorna um funcionario pelo cpf encontrado
	 * @param funcionarioId
	 * @param pageRequest
	 * @return
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, Pageable paging);
	
	/**
	 * Remove um lancamento pelo id
	 * @param id
	 * @return
	 */
	void remover(Long id);

}
