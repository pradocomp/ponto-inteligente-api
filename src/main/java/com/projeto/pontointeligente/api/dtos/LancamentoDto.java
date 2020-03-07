package com.projeto.pontointeligente.api.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class LancamentoDto {
	
	private Optional<Long> id = Optional.empty();
	
	@NotEmpty(message = "Data não pode ser vazio.")
	private String data;
	
	private String tipo;
	
	private String descricao;
	
	private String localizacao;
	
	private Long funcionarioId;
	
}
