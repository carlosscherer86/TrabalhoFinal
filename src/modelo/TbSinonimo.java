package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_sinonimo database table.
 * 
 */
@Entity
@Table(name="tb_sinonimo")
@NamedQuery(name="TbSinonimo.findAll", query="SELECT t FROM TbSinonimo t")
public class TbSinonimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_SINONIMO")
	private int pkCodSinonimo;

	@Column(name="NME_SINONIMO")
	private String nmeSinonimo;

	@Column(name="NUM_GRUPO")
	private int numGrupo;

	@Column(name="NUM_SEQUENCIA")
	private int numSequencia;

	public TbSinonimo() {
	}

	public int getPkCodSinonimo() {
		return this.pkCodSinonimo;
	}

	public void setPkCodSinonimo(int pkCodSinonimo) {
		this.pkCodSinonimo = pkCodSinonimo;
	}

	public String getNmeSinonimo() {
		return this.nmeSinonimo;
	}

	public void setNmeSinonimo(String nmeSinonimo) {
		this.nmeSinonimo = nmeSinonimo;
	}

	public int getNumGrupo() {
		return this.numGrupo;
	}

	public void setNumGrupo(int numGrupo) {
		this.numGrupo = numGrupo;
	}

	public int getNumSequencia() {
		return this.numSequencia;
	}

	public void setNumSequencia(int numSequencia) {
		this.numSequencia = numSequencia;
	}

}