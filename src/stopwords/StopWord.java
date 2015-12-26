package stopwords;

import javax.persistence.EntityManager;

import ModeloRepositorio.TbStopWordRepository;
import diversas.Entity;

public class StopWord {
	private TbStopWordRepository repository;
	private EntityManager manager;
	public StopWord(){
		manager = Entity.RetornaEntityManager();
		repository = new TbStopWordRepository(manager);
	}
	
	//Verifica se é uma StopWord
	public static Boolean BuscaStopWord(String pWord){
		String lWord;
		StopWord stopword = new StopWord();
		lWord= stopword.repository.BuscaStopWord(pWord);
		return !(lWord.isEmpty());
	}

}
