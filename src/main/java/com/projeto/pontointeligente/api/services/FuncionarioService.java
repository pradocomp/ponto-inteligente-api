package com.projeto.pontointeligente.api.services;

import java.util.Optional;

import com.projeto.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Cadastra um funcionario na base de dados
	 * @param funcionario
	 * @return
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Retorna um funcionario pelo cpf encontrado
	 * @param cpf
	 * @return
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * Retorna um funcionario pelo e-mail encontrado
	 * @param email
	 * @return
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Retorna um funcionario por id
	 * @param id
	 * @return
	 */
	Optional<Funcionario> buscarPorId(Long id);

}
