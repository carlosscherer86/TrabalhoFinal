package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_tipo_oferta database table.
 * 
 */
@Entity
@Table(name="tb_tipo_oferta")
@NamedQuery(name="TbTipoOferta.findAll", query="SELECT t FROM TbTipoOferta t")
public class TbTipoOferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_TIPO_OFERTA")
	private int pkCodTipoOferta;

	@Column(name="NME_DESCRICAO_TIPO_OFERTA")
	private String nmeDescricaoTipoOferta;

	//bi-directional many-to-one association to TbOferta
	@OneToMany(mappedBy="tbTipoOferta")
	private List<TbOferta> tbOfertas;

	public TbTipoOferta() {
	}

	public int getPkCodTipoOferta() {
		return this.pkCodTipoOferta;
	}

	public void setPkCodTipoOferta(int pkCodTipoOferta) {
		this.pkCodTipoOferta = pkCodTipoOferta;
	}

	public String getNmeDescricaoTipoOferta() {
		return this.nmeDescricaoTipoOferta;
	}

	public void setNmeDescricaoTipoOferta(String nmeDescricaoTipoOferta) {
		this.nmeDescricaoTipoOferta = nmeDescricaoTipoOferta;
	}

	public List<TbOferta> getTbOfertas() {
		return this.tbOfertas;
	}

	public void setTbOfertas(List<TbOferta> tbOfertas) {
		this.tbOfertas = tbOfertas;
	}

	public TbOferta addTbOferta(TbOferta tbOferta) {
		getTbOfertas().add(tbOferta);
		tbOferta.setTbTipoOferta(this);

		return tbOferta;
	}

	public TbOferta removeTbOferta(TbOferta tbOferta) {
		getTbOfertas().remove(tbOferta);
		tbOferta.setTbTipoOferta(null);

		return tbOferta;
	}

}