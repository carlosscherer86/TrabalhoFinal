package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_stop_word database table.
 * 
 */
@Entity
@Table(name="tb_stop_word")
@NamedQuery(name="TbStopWord.findAll", query="SELECT t FROM TbStopWord t")
public class TbStopWord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK_COD_STOP_WORD")
	private int pkCodStopWord;

	@Column(name="NME_STOP_WORD")
	private String nmeStopWord;

	public TbStopWord() {
	}

	public int getPkCodStopWord() {
		return this.pkCodStopWord;
	}

	public void setPkCodStopWord(int pkCodStopWord) {
		this.pkCodStopWord = pkCodStopWord;
	}

	public String getNmeStopWord() {
		return this.nmeStopWord;
	}

	public void setNmeStopWord(String nmeStopWord) {
		this.nmeStopWord = nmeStopWord;
	}

}