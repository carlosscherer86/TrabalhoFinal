package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the tb_match database table.
 * 
 */
@Entity
@Table(name="tb_match")
@NamedQuery(name="TbMatch.findAll", query="SELECT t FROM TbMatch t")
public class TbMatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_MATCH")
	private int pkCodMatch;

	@Column(name="NUM_SIMILARIDADE")
	private int numSimilaridade;
	
	@Column(name="NUM_DISTANCIA")
	private int numDistancia;

	@Column(name="STA_ATIVO")
	private byte staAtivo;

	//bi-directional many-to-one association to TbDemanda
	@ManyToOne
	@JoinColumn(name="FK_COD_DEMANDA")
	private TbDemanda tbDemanda;

	//bi-directional many-to-one association to TbOferta
	@ManyToOne
	@JoinColumn(name="FK_COD_OFERTA")
	private TbOferta tbOferta;

	public TbMatch() {
	}

	public int getPkCodMatch() {
		return this.pkCodMatch;
	}

	public void setPkCodMatch(int pkCodMatch) {
		this.pkCodMatch = pkCodMatch;
	}

	public int getNumSimilaridade() {
		return this.numSimilaridade;
	}

	public void setNumSimilaridade(int numSimilaridade) {
		this.numSimilaridade = numSimilaridade;
	}
	
	public int getNumDistancia() {
		return numDistancia;
	}

	public void setNumDistancia(int numDistancia) {
		this.numDistancia = numDistancia;
	}


	public byte getStaAtivo() {
		return this.staAtivo;
	}

	public void setStaAtivo(byte staAtivo) {
		this.staAtivo = staAtivo;
	}

	public TbDemanda getTbDemanda() {
		return this.tbDemanda;
	}

	public void setTbDemanda(TbDemanda tbDemanda) {
		this.tbDemanda = tbDemanda;
	}

	public TbOferta getTbOferta() {
		return this.tbOferta;
	}

	public void setTbOferta(TbOferta tbOferta) {
		this.tbOferta = tbOferta;
	}

}