package com.projeto.pontointeligente.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@ToString
public class CadastroPJDto {
	
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido.")
	private String email;
	
	@NotEmpty(message = "Senha não pode ser vazia.")
	private String senha;
	
	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message="CPF inválido")
	private String cpf;
	
	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message="CNPJ inválido.")
	private String cnpj;
	
	@NotEmpty(message = "Razão social não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Razão social deve conter entre 5 e 200 caracteres.")
	private String razaoSocial;
	
}
