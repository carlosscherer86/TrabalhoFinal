package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_imagem database table.
 * 
 */
@Entity
@Table(name="tb_imagem")
@NamedQuery(name="TbImagem.findAll", query="SELECT t FROM TbImagem t")
public class TbImagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_IMAGEM")
	private int pkCodImagem;

	@Column(name="NME_IMAGEM")
	private String nmeImagem;

	//bi-directional many-to-one association to TbOferta
	@ManyToOne
	@JoinColumn(name="FK_COD_OFERTA")
	private TbOferta tbOferta;

	public TbImagem() {
	}

	public int getPkCodImagem() {
		return this.pkCodImagem;
	}

	public void setPkCodImagem(int pkCodImagem) {
		this.pkCodImagem = pkCodImagem;
	}

	public String getNmeImagem() {
		return this.nmeImagem;
	}

	public void setNmeImagem(String nmeImagem) {
		this.nmeImagem = nmeImagem;
	}

	public TbOferta getTbOferta() {
		return this.tbOferta;
	}

	public void setTbOferta(TbOferta tbOferta) {
		this.tbOferta = tbOferta;
	}

}