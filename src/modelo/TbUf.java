package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_uf database table.
 * 
 */
@Entity
@Table(name="tb_uf")
@NamedQuery(name="TbUf.findAll", query="SELECT t FROM TbUf t")
public class TbUf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_UF")
	private int pkCodUf;

	@Column(name="NME_ABREV")
	private String nmeAbrev;

	@Column(name="NME_DESCRICAO")
	private String nmeDescricao;

	//bi-directional many-to-one association to TbEndereco
	@OneToMany(mappedBy="tbUf")
	private List<TbEndereco> tbEnderecos;

	//bi-directional many-to-one association to TbPessoaFisica
	@OneToMany(mappedBy="tbUf")
	private List<TbPessoaFisica> tbPessoaFisicas;

	public TbUf() {
	}

	public int getPkCodUf() {
		return this.pkCodUf;
	}

	public void setPkCodUf(int pkCodUf) {
		this.pkCodUf = pkCodUf;
	}

	public String getNmeAbrev() {
		return this.nmeAbrev;
	}

	public void setNmeAbrev(String nmeAbrev) {
		this.nmeAbrev = nmeAbrev;
	}

	public String getNmeDescricao() {
		return this.nmeDescricao;
	}

	public void setNmeDescricao(String nmeDescricao) {
		this.nmeDescricao = nmeDescricao;
	}

	public List<TbEndereco> getTbEnderecos() {
		return this.tbEnderecos;
	}

	public void setTbEnderecos(List<TbEndereco> tbEnderecos) {
		this.tbEnderecos = tbEnderecos;
	}

	public TbEndereco addTbEndereco(TbEndereco tbEndereco) {
		getTbEnderecos().add(tbEndereco);
		tbEndereco.setTbUf(this);

		return tbEndereco;
	}

	public TbEndereco removeTbEndereco(TbEndereco tbEndereco) {
		getTbEnderecos().remove(tbEndereco);
		tbEndereco.setTbUf(null);

		return tbEndereco;
	}

	public List<TbPessoaFisica> getTbPessoaFisicas() {
		return this.tbPessoaFisicas;
	}

	public void setTbPessoaFisicas(List<TbPessoaFisica> tbPessoaFisicas) {
		this.tbPessoaFisicas = tbPessoaFisicas;
	}

	public TbPessoaFisica addTbPessoaFisica(TbPessoaFisica tbPessoaFisica) {
		getTbPessoaFisicas().add(tbPessoaFisica);
		tbPessoaFisica.setTbUf(this);

		return tbPessoaFisica;
	}

	public TbPessoaFisica removeTbPessoaFisica(TbPessoaFisica tbPessoaFisica) {
		getTbPessoaFisicas().remove(tbPessoaFisica);
		tbPessoaFisica.setTbUf(null);

		return tbPessoaFisica;
	}

}