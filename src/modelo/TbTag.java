package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_tags database table.
 * 
 */
@Entity
@Table(name="tb_tags")
@NamedQuery(name="TbTag.findAll", query="SELECT t FROM TbTag t")
public class TbTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_TAG")
	private int pkCodTag;

	@Column(name="NME_DESCRICAO")
	private String nmeDescricao;

	@Column(name="NME_TAG")
	private String nmeTag;

	public TbTag() {
	}

	public int getPkCodTag() {
		return this.pkCodTag;
	}

	public void setPkCodTag(int pkCodTag) {
		this.pkCodTag = pkCodTag;
	}

	public String getNmeDescricao() {
		return this.nmeDescricao;
	}

	public void setNmeDescricao(String nmeDescricao) {
		this.nmeDescricao = nmeDescricao;
	}

	public String getNmeTag() {
		return this.nmeTag;
	}

	public void setNmeTag(String nmeTag) {
		this.nmeTag = nmeTag;
	}

}