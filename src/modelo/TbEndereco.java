package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_endereco database table.
 * 
 */
@Entity
@Table(name="tb_endereco")
@NamedQuery(name="TbEndereco.findAll", query="SELECT t FROM TbEndereco t")
public class TbEndereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_ENDERECO")
	private int pkCodEndereco;

	@Column(name="NME_BAIRRO")
	private String nmeBairro;

	@Column(name="NME_CEP")
	private String nmeCep;

	@Column(name="NME_CIDADE")
	private String nmeCidade;

	@Column(name="NME_COMPLEMENTO")
	private String nmeComplemento;

	@Column(name="NME_ENDERECO")
	private String nmeEndereco;

	@Column(name="NME_NUMERO")
	private String nmeNumero;

	//bi-directional many-to-one association to TbOferta
	@ManyToOne
	@JoinColumn(name="FK_COD_OFERTA")
	private TbOferta tbOferta;

	//bi-directional many-to-one association to TbUf
	@ManyToOne
	@JoinColumn(name="FK_COD_UF")
	private TbUf tbUf;

	public TbEndereco() {
	}

	public int getPkCodEndereco() {
		return this.pkCodEndereco;
	}

	public void setPkCodEndereco(int pkCodEndereco) {
		this.pkCodEndereco = pkCodEndereco;
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

	public String getNmeEndereco() {
		return this.nmeEndereco;
	}

	public void setNmeEndereco(String nmeEndereco) {
		this.nmeEndereco = nmeEndereco;
	}

	public String getNmeNumero() {
		return this.nmeNumero;
	}

	public void setNmeNumero(String nmeNumero) {
		this.nmeNumero = nmeNumero;
	}

	public TbOferta getTbOferta() {
		return this.tbOferta;
	}

	public void setTbOferta(TbOferta tbOferta) {
		this.tbOferta = tbOferta;
	}

	public TbUf getTbUf() {
		return this.tbUf;
	}

	public void setTbUf(TbUf tbUf) {
		this.tbUf = tbUf;
	}

}