package com.projeto.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

import com.projeto.pontointeligente.api.dtos.CadastroPFDto;
import com.projeto.pontointeligente.api.entities.Empresa;
import com.projeto.pontointeligente.api.entities.Funcionario;
import com.projeto.pontointeligente.api.response.Response;
import com.projeto.pontointeligente.api.services.EmpresaService;
import com.projeto.pontointeligente.api.services.FuncionarioService;
import com.projeto.pontointeligente.api.utils.ConverterUtils;
import com.projeto.pontointeligente.api.utils.ValidatorUtils;

import lombok.ToString;

@RestController
@RequestMapping("/api/cadastrar-pf")
@CrossOrigin(origins = "*")
@ToString
public class CadastroPFController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPFController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private ConverterUtils converterUtils;
	
	@Autowired
	private ValidatorUtils validatorUtils;

	public CadastroPFController() {
	}

	/**
	 * Cadastra um funcionário pessoa física no sistema.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPFDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPFDto>> cadastrar(@Valid @RequestBody CadastroPFDto cadastroPFDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando PF: {}", cadastroPFDto.toString());
		Response<CadastroPFDto> response = new Response<CadastroPFDto>();

		validatorUtils.validarDadosPFExistentes(cadastroPFDto, result);
		Funcionario funcionario = converterUtils.converterDtoParaFuncionario(cadastroPFDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PF: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.persistir(funcionario);

		response.setData(converterUtils.converterCadastroPFDto(funcionario));
		return ResponseEntity.ok(response);
	}

}
