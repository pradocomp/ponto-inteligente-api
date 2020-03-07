package com.projeto.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.pontointeligente.api.dtos.CadastroPJDto;
import com.projeto.pontointeligente.api.entities.Empresa;
import com.projeto.pontointeligente.api.entities.Funcionario;
import com.projeto.pontointeligente.api.response.Response;
import com.projeto.pontointeligente.api.services.EmpresaService;
import com.projeto.pontointeligente.api.services.FuncionarioService;
import com.projeto.pontointeligente.api.utils.ConverterUtils;
import com.projeto.pontointeligente.api.utils.ValidatorUtils;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
@NoArgsConstructor
public class CadastroPJController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ConverterUtils converterUtils;
	
	@Autowired
	private ValidatorUtils validatorUtils;
	
	/**
	 * Cadastra uma pessoa jurídica no sistema.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	ResponseEntity<Response<?>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando PJ: {}", cadastroPJDto.toString());
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();

		validatorUtils.validarDadosPJExistentes(cadastroPJDto, result);
		Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDto);
		Funcionario funcionario = converterUtils.converterDtoParaFuncionario(cadastroPJDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistir(funcionario);

		response.setData(converterUtils.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Converte os dados do DTO para empresa.
	 * 
	 * @param cadastroPJDto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(CadastroPJDto cadastroPJDto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());

		return empresa;
	}
	
}
