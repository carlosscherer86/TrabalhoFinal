package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_pessoa_fisica database table.
 * 
 */
@Entity
@Table(name="tb_pessoa_fisica")
@NamedQuery(name="TbPessoaFisica.findAll", query="SELECT t FROM TbPessoaFisica t")
public class TbPessoaFisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_PESSOA_FISICA")
	private int pkCodPessoaFisica;

	@Column(name="DTA_NASCIMENTO")
	private String dtaNascimento;

	@Column(name="NME_BAIRRO")
	private String nmeBairro;

	@Column(name="NME_CEP")
	private String nmeCep;

	@Column(name="NME_CIDADE")
	private String nmeCidade;

	@Column(name="NME_COMPLEMENTO")
	private String nmeComplemento;

	@Column(name="NME_EMAIL")
	private String nmeEmail;

	@Column(name="NME_ENDERECO")
	private String nmeEndereco;

	@Column(name="NME_NOME")
	private String nmeNome;

	@Column(name="NME_NUMERO")
	private String nmeNumero;

	@Column(name="NME_SENHA")
	private String nmeSenha;

	@Column(name="NME_SOBRENOME")
	private String nmeSobrenome;

	@Column(name="STA_ADMIN")
	private byte staAdmin;

	//bi-directional many-to-one association to TbDemanda
	@OneToMany(mappedBy="tbPessoaFisica")
	private List<TbDemanda> tbDemandas;

	//bi-directional many-to-one association to TbOferta
	@OneToMany(mappedBy="tbPessoaFisica")
	private List<TbOferta> tbOfertas;

	//bi-directional many-to-one association to TbUf
	@ManyToOne
	@JoinColumn(name="FK_COD_UF")
	private TbUf tbUf;

	public TbPessoaFisica() {
	}

	public int getPkCodPessoaFisica() {
		return this.pkCodPessoaFisica;
	}

	public void setPkCodPessoaFisica(int pkCodPessoaFisica) {
		this.pkCodPessoaFisica = pkCodPessoaFisica;
	}

	public String getDtaNascimento() {
		return this.dtaNascimento;
	}

	public void setDtaNascimento(String dtaNascimento) {
		this.dtaNascimento = dtaNascimento;
	}

	public String getNmeBairro() {
		return this.nmeBairro;
	}

	public void setNmeBairro(String nmeBairro) {
		this.nmeBairro = nmeBairro;
	}

	public String getNmeCep() {
		return this.nmeCep;
	}

	public void setNmeCep(String nmeCep) {
		this.nmeCep = nmeCep;
	}

	public String getNmeCidade() {
		return this.nmeCidade;
	}

	public void setNmeCidade(String nmeCidade) {
		this.nmeCidade = nmeCidade;
	}

	public String getNmeComplemento() {
		return this.nmeComplemento;
	}

	public void setNmeComplemento(String nmeComplemento) {
		this.nmeComplemento = nmeComplemento;
	}

	public String getNmeEmail() {
		return this.nmeEmail;
	}

	public void setNmeEmail(String nmeEmail) {
		this.nmeEmail = nmeEmail;
	}

	public String getNmeEndereco() {
		return this.nmeEndereco;
	}

	public void setNmeEndereco(String nmeEndereco) {
		this.nmeEndereco = nmeEndereco;
	}

	public String getNmeNome() {
		return this.nmeNome;
	}

	public void setNmeNome(String nmeNome) {
		this.nmeNome = nmeNome;
	}

	public String getNmeNumero() {
		return this.nmeNumero;
	}

	public void setNmeNumero(String nmeNumero) {
		this.nmeNumero = nmeNumero;
	}

	public String getNmeSenha() {
		return this.nmeSenha;
	}

	public void setNmeSenha(String nmeSenha) {
		this.nmeSenha = nmeSenha;
	}

	public String getNmeSobrenome() {
		return this.nmeSobrenome;
	}

	public void setNmeSobrenome(String nmeSobrenome) {
		this.nmeSobrenome = nmeSobrenome;
	}

	public byte getStaAdmin() {
		return this.staAdmin;
	}

	public void setStaAdmin(byte staAdmin) {
		this.staAdmin = staAdmin;
	}

	public List<TbDemanda> getTbDemandas() {
		return this.tbDemandas;
	}

	public void setTbDemandas(List<TbDemanda> tbDemandas) {
		this.tbDemandas = tbDemandas;
	}

	public TbDemanda addTbDemanda(TbDemanda tbDemanda) {
		getTbDemandas().add(tbDemanda);
		tbDemanda.setTbPessoaFisica(this);

		return tbDemanda;
	}

	public TbDemanda removeTbDemanda(TbDemanda tbDemanda) {
		getTbDemandas().remove(tbDemanda);
		tbDemanda.setTbPessoaFisica(null);

		return tbDemanda;
	}

	public List<TbOferta> getTbOfertas() {
		return this.tbOfertas;
	}

	public void setTbOfertas(List<TbOferta> tbOfertas) {
		this.tbOfertas = tbOfertas;
	}

	public TbOferta addTbOferta(TbOferta tbOferta) {
		getTbOfertas().add(tbOferta);
		tbOferta.setTbPessoaFisica(this);

		return tbOferta;
	}

	public TbOferta removeTbOferta(TbOferta tbOferta) {
		getTbOfertas().remove(tbOferta);
		tbOferta.setTbPessoaFisica(null);

		return tbOferta;
	}

	public TbUf getTbUf() {
		return this.tbUf;
	}

	public void setTbUf(TbUf tbUf) {
		this.tbUf = tbUf;
	}

}