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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = true)
	private BigDecimal valorHora;
	@Column(nullable = true)
	private Float qtdHorasTrabalhoDia;
	@Column(nullable = true)
	private Float qtdHorasAlmoco;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PerfilEnum perfil;
	@Column(nullable = false)
	private Date dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataAtualizacao;
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	@Transient
	public Optional<Float> qtdHorasAlmocoOpt;
	@Transient
	public Optional<Float> qtdHorasTrabalhoDiaOpt;
	@Transient
	public Optional<BigDecimal> valorHoraOpt;
	
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