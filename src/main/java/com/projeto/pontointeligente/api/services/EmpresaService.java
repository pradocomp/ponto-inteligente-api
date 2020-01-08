package com.projeto.pontointeligente.api.services;

import java.util.Optional;

import com.projeto.pontointeligente.api.entities.Empresa;

public interface EmpresaService {
	
	/**
	 * Retorna a empresa pelo CNPJ encontrado
	 * @param cnpj
	 * @return
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados
	 * @param empresa
	 * @return
	 */
	Empresa persistir(Empresa empresa);

}
