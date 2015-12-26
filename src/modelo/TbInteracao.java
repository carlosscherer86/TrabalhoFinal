package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_interacao database table.
 * 
 */
@Entity
@Table(name="tb_interacao")
@NamedQuery(name="TbInteracao.findAll", query="SELECT t FROM TbInteracao t")
public class TbInteracao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_INTERACAO")
	private int pkCodInteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DTA_ENVIO")
	private Date dtaEnvio;

	@Column(name="NME_MENSAGEM")
	private String nmeMensagem;

	//bi-directional many-to-one association to TbDemanda
	@ManyToOne
	@JoinColumn(name="FK_COD_DEMANDA")
	private TbDemanda tbDemanda;

	//bi-directional many-to-one association to TbOferta
	@ManyToOne
	@JoinColumn(name="FK_COD_OFERTA")
	private TbOferta tbOferta;

	public TbInteracao() {
	}

	public int getPkCodInteracao() {
		return this.pkCodInteracao;
	}

	public void setPkCodInteracao(int pkCodInteracao) {
		this.pkCodInteracao = pkCodInteracao;
	}

	public Date getDtaEnvio() {
		return this.dtaEnvio;
	}

	public void setDtaEnvio(Date dtaEnvio) {
		this.dtaEnvio = dtaEnvio;
	}

	public String getNmeMensagem() {
		return this.nmeMensagem;
	}

	public void setNmeMensagem(String nmeMensagem) {
		this.nmeMensagem = nmeMensagem;
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