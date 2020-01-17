package com.projeto.pontointeligente.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.pontointeligente.api.dtos.EmpresaDto;
import com.projeto.pontointeligente.api.entities.Empresa;
import com.projeto.pontointeligente.api.response.Response;
import com.projeto.pontointeligente.api.services.EmpresaService;
import com.projeto.pontointeligente.api.utils.ConverterUtils;

import lombok.ToString;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
@ToString
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ConverterUtils converterUtils;
	
	public EmpresaController() {
	}

	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ: " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(converterUtils.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}
	
}