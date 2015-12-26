package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_categoria database table.
 * 
 */
@Entity
@Table(name="tb_categoria")
@NamedQuery(name="TbCategoria.findAll", query="SELECT t FROM TbCategoria t")
public class TbCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_CATEGORIA")
	private int pkCodCategoria;

	@Column(name="NME_DESCRICAO")
	private String nmeDescricao;

	@Column(name="NME_NOME")
	private String nmeNome;

	//bi-directional many-to-one association to TbDemanda
	@OneToMany(mappedBy="tbCategoria")
	private List<TbDemanda> tbDemandas;

	//bi-directional many-to-one association to TbOferta
	@OneToMany(mappedBy="tbCategoria")
	private List<TbOferta> tbOfertas;

	public TbCategoria() {
	}

	public int getPkCodCategoria() {
		return this.pkCodCategoria;
	}

	public void setPkCodCategoria(int pkCodCategoria) {
		this.pkCodCategoria = pkCodCategoria;
	}

	public String getNmeDescricao() {
		return this.nmeDescricao;
	}

	public void setNmeDescricao(String nmeDescricao) {
		this.nmeDescricao = nmeDescricao;
	}

	public String getNmeNome() {
		return this.nmeNome;
	}

	public void setNmeNome(String nmeNome) {
		this.nmeNome = nmeNome;
	}

	public List<TbDemanda> getTbDemandas() {
		return this.tbDemandas;
	}

	public void setTbDemandas(List<TbDemanda> tbDemandas) {
		this.tbDemandas = tbDemandas;
	}

	public TbDemanda addTbDemanda(TbDemanda tbDemanda) {
		getTbDemandas().add(tbDemanda);
		tbDemanda.setTbCategoria(this);

		return tbDemanda;
	}

	public TbDemanda removeTbDemanda(TbDemanda tbDemanda) {
		getTbDemandas().remove(tbDemanda);
		tbDemanda.setTbCategoria(null);

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
		tbOferta.setTbCategoria(this);

		return tbOferta;
	}

	public TbOferta removeTbOferta(TbOferta tbOferta) {
		getTbOfertas().remove(tbOferta);
		tbOferta.setTbCategoria(null);

		return tbOferta;
	}

}