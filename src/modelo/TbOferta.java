package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tb_oferta database table.
 * 
 */
@Entity
@Table(name="tb_oferta")
@NamedQuery(name="TbOferta.findAll", query="SELECT t FROM TbOferta t")
public class TbOferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_OFERTA")
	private int pkCodOferta;

	@Column(name="NME_DESCRICAO")
	private String nmeDescricao;

	@Column(name="NME_DESCRICAO_MATCH")
	private String nmeDescricaoMatch;

	@Column(name="NUM_QUANTIDADE")
	private int numQuantidade;

	@Column(name="VLR_UNIDADE")
	private BigDecimal vlrUnidade;

	//bi-directional many-to-one association to TbEndereco
	@OneToMany(mappedBy="tbOferta")
	private List<TbEndereco> tbEnderecos;

	//bi-directional many-to-one association to TbImagem
	@OneToMany(mappedBy="tbOferta")
	private List<TbImagem> tbImagems;

	//bi-directional many-to-one association to TbInteracao
	@OneToMany(mappedBy="tbOferta")
	private List<TbInteracao> tbInteracaos;

	//bi-directional many-to-one association to TbCategoria
	@ManyToOne
	@JoinColumn(name="FK_COD_CATEGORIA")
	private TbCategoria tbCategoria;

	//bi-directional many-to-one association to TbPessoaFisica
	@ManyToOne
	@JoinColumn(name="FK_COD_PESSOA_FISICA")
	private TbPessoaFisica tbPessoaFisica;

	//bi-directional many-to-one association to TbTipoOferta
	@ManyToOne
	@JoinColumn(name="FK_COD_TIPO_OFERTA")
	private TbTipoOferta tbTipoOferta;

	//bi-directional many-to-one association to TbMatch
	@OneToMany(mappedBy="tbOferta")
	private List<TbMatch> tbMatches;

	public TbOferta() {
	}

	public int getPkCodOferta() {
		return this.pkCodOferta;
	}

	public void setPkCodOferta(int pkCodOferta) {
		this.pkCodOferta = pkCodOferta;
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

	public int getNumQuantidade() {
		return this.numQuantidade;
	}

	public void setNumQuantidade(int numQuantidade) {
		this.numQuantidade = numQuantidade;
	}

	public BigDecimal getVlrUnidade() {
		return this.vlrUnidade;
	}

	public void setVlrUnidade(BigDecimal vlrUnidade) {
		this.vlrUnidade = vlrUnidade;
	}

	public List<TbEndereco> getTbEnderecos() {
		return this.tbEnderecos;
	}

	public void setTbEnderecos(List<TbEndereco> tbEnderecos) {
		this.tbEnderecos = tbEnderecos;
	}

	public TbEndereco addTbEndereco(TbEndereco tbEndereco) {
		getTbEnderecos().add(tbEndereco);
		tbEndereco.setTbOferta(this);

		return tbEndereco;
	}

	public TbEndereco removeTbEndereco(TbEndereco tbEndereco) {
		getTbEnderecos().remove(tbEndereco);
		tbEndereco.setTbOferta(null);

		return tbEndereco;
	}

	public List<TbImagem> getTbImagems() {
		return this.tbImagems;
	}

	public void setTbImagems(List<TbImagem> tbImagems) {
		this.tbImagems = tbImagems;
	}

	public TbImagem addTbImagem(TbImagem tbImagem) {
		getTbImagems().add(tbImagem);
		tbImagem.setTbOferta(this);

		return tbImagem;
	}

	public TbImagem removeTbImagem(TbImagem tbImagem) {
		getTbImagems().remove(tbImagem);
		tbImagem.setTbOferta(null);

		return tbImagem;
	}

	public List<TbInteracao> getTbInteracaos() {
		return this.tbInteracaos;
	}

	public void setTbInteracaos(List<TbInteracao> tbInteracaos) {
		this.tbInteracaos = tbInteracaos;
	}

	public TbInteracao addTbInteracao(TbInteracao tbInteracao) {
		getTbInteracaos().add(tbInteracao);
		tbInteracao.setTbOferta(this);

		return tbInteracao;
	}

	public TbInteracao removeTbInteracao(TbInteracao tbInteracao) {
		getTbInteracaos().remove(tbInteracao);
		tbInteracao.setTbOferta(null);

		return tbInteracao;
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

	public TbTipoOferta getTbTipoOferta() {
		return this.tbTipoOferta;
	}

	public void setTbTipoOferta(TbTipoOferta tbTipoOferta) {
		this.tbTipoOferta = tbTipoOferta;
	}

	public List<TbMatch> getTbMatches() {
		return this.tbMatches;
	}

	public void setTbMatches(List<TbMatch> tbMatches) {
		this.tbMatches = tbMatches;
	}

	public TbMatch addTbMatch(TbMatch tbMatch) {
		getTbMatches().add(tbMatch);
		tbMatch.setTbOferta(this);

		return tbMatch;
	}

	public TbMatch removeTbMatch(TbMatch tbMatch) {
		getTbMatches().remove(tbMatch);
		tbMatch.setTbOferta(null);

		return tbMatch;
	}
	
	public String concatenaEndereco(){
		return tbEnderecos.get(0).getNmeEndereco()+", "+ tbEnderecos.get(0).getNmeNumero()+", "+ 
				tbEnderecos.get(0).getNmeBairro()+", "+tbEnderecos.get(0).getNmeCidade()+", "+tbEnderecos.get(0).getTbUf().getNmeAbrev();
	}

}