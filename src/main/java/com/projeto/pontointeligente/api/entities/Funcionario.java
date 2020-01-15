package com.projeto.pontointeligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.projeto.pontointeligente.api.enums.PerfilEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "funcionario")
@Data
@ToString
@NoArgsConstructor
public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 7123327503060649652L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "senha", nullable = false)
	private String senha;
	@Column(name = "cpf", nullable = false)
	private String cpf;
	@Column(name = "valor_hora", nullable = true)
	private BigDecimal valorHora;
	@Column(name = "qtd_horas_trabalho_dia", nullable = true)
	private Float qtdHorasTrabalhoDia;
	@Column(name = "qtd_horas_almoco", nullable = true)
	private Float qtdHorasAlmoco;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PerfilEnum perfil;
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	@Transient
	public Optional<Float> qtdHorasAlmocoOpt = Optional.ofNullable(qtdHorasAlmoco);
	@Transient
	public Optional<Float> qtdHorasTrabalhoDiaOpt  = Optional.ofNullable(qtdHorasTrabalhoDia);
	@Transient
	public Optional<BigDecimal> valorHoraOpt  = Optional.ofNullable(valorHora);
	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

}