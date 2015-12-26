package modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tb_demanda database table.
 * 
 */
@Entity
@Table(name="tb_demanda")
@NamedQuery(name="TbDemanda.findAll", query="SELECT t FROM TbDemanda t")
public class TbDemanda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_DEMANDA")
	private int pkCodDemanda;

	@Column(name="NME_DESCRICAO")
	private String nmeDescricao;

	@Column(name="NME_DESCRICAO_MATCH")
	private String nmeDescricaoMatch;

	@Column(name="VLR_MAX")
	private BigDecimal vlrMax;

	@Column(name="VLR_MIN")
	private BigDecimal vlrMin;

	//bi-directional many-to-one association to TbCategoria
	@ManyToOne
	@JoinColumn(name="FK_COD_CATEGORIA")
	private TbCategoria tbCategoria;

	//bi-directional many-to-one association to TbPessoaFisica
	@ManyToOne
	@JoinColumn(name="FK_COD_PESSOA_FISICA")
	private TbPessoaFisica tbPessoaFisica;

	//bi-directional many-to-one association to TbInteracao
	@OneToMany(mappedBy="tbDemanda")
	private List<TbInteracao> tbInteracaos;

	//bi-directional many-to-one association to TbMatch
	@OneToMany(mappedBy="tbDemanda")
	private List<TbMatch> tbMatches;

	public TbDemanda() {
	}

	public int getPkCodDemanda() {
		return this.pkCodDemanda;
	}

	public void setPkCodDemanda(int pkCodDemanda) {
		this.pkCodDemanda = pkCodDemanda;
	}

	public String getNmeDescricao() {
		return this.nmeDescricao;
	}

	public void setNmeDescricao(String nmeDescricao) {
		this.nmeDescricao = nmeDescricao;
	}

	public String getNmeDescricaoMatch() {
		return this.nmeDescricaoMatch;
	}

	public void setNmeDescricaoMatch(String nmeDescricaoMatch) {
		this.nmeDescricaoMatch = nmeDescricaoMatch;
	}

	public BigDecimal getVlrMax() {
		return this.vlrMax;
	}

	public void setVlrMax(BigDecimal vlrMax) {
		this.vlrMax = vlrMax;
	}

	public BigDecimal getVlrMin() {
		return this.vlrMin;
	}

	public void setVlrMin(BigDecimal vlrMin) {
		this.vlrMin = vlrMin;
	}

	public TbCategoria getTbCategoria() {
		return this.tbCategoria;
	}

	public void setTbCategoria(TbCategoria tbCategoria) {
		this.tbCategoria = tbCategoria;
	}

	public TbPessoaFisica getTbPessoaFisica() {
		return this.tbPessoaFisica;
	}

	public void setTbPessoaFisica(TbPessoaFisica tbPessoaFisica) {
		this.tbPessoaFisica = tbPessoaFisica;
	}

	public List<TbInteracao> getTbInteracaos() {
		return this.tbInteracaos;
	}

	public void setTbInteracaos(List<TbInteracao> tbInteracaos) {
		this.tbInteracaos = tbInteracaos;
	}

	public TbInteracao addTbInteracao(TbInteracao tbInteracao) {
		getTbInteracaos().add(tbInteracao);
		tbInteracao.setTbDemanda(this);

		return tbInteracao;
	}

	public TbInteracao removeTbInteracao(TbInteracao tbInteracao) {
		getTbInteracaos().remove(tbInteracao);
		tbInteracao.setTbDemanda(null);

		return tbInteracao;
	}

	public List<TbMatch> getTbMatches() {
		return this.tbMatches;
	}

	public void setTbMatches(List<TbMatch> tbMatches) {
		this.tbMatches = tbMatches;
	}

	public TbMatch addTbMatch(TbMatch tbMatch) {
		getTbMatches().add(tbMatch);
		tbMatch.setTbDemanda(this);

		return tbMatch;
	}

	public TbMatch removeTbMatch(TbMatch tbMatch) {
		getTbMatches().remove(tbMatch);
		tbMatch.setTbDemanda(null);

		return tbMatch;
	}
	
	public String concatenaEndereco(){
		return tbPessoaFisica.getNmeEndereco()+", "+ tbPessoaFisica.getNmeNumero()+", "+ 
				tbPessoaFisica.getNmeBairro()+", "+tbPessoaFisica.getNmeCidade()+", "+tbPessoaFisica.getTbUf().getNmeAbrev();
	}

}