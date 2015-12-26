package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbCategoria;
import modelo.TbImagem;

public class TbImagemRepository {
	private EntityManager manager;

	public TbImagemRepository(EntityManager manager) {
		this.manager = manager;
	}

	//Insere imagem
	public void adiciona(TbImagem Imagem) {
		this.manager.persist(Imagem);
	}
	
	//Atuliza dados da imagem
	public void atualizar(TbImagem Imagem) {
		this.manager.merge(Imagem);
	}
	
	//Deleta imagem
	public void deletar(TbImagem Imagem) {
		this.manager.remove(this.manager.find(TbImagem.class, Imagem.getPkCodImagem()));
	}
	
	//Retorna maior id
	public int RetornaMax(){
		int max= 0;
		Query query = this.manager.createQuery("select max(ev.pkCodImagem) as pkCodImagem from TbImagem as ev");
		List<Integer> List= query.getResultList();
		if (List.get(0) != null){
			max= List.get(0);
		}
		return max;
		
	}

	//Busca todas as categorias
	public List<TbImagem> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbImagem x");
		return query.getResultList();
	}
	
	//Busca imagem por id do Oferta
	public TbImagem BuscaOfertaPorIdOferta(int Codigo){
		String hql = "from TbImagem where tbOferta = "+ Codigo; 
		Query query = this.manager.createQuery(hql); 
		List<TbImagem> ImagemList = query.getResultList();
		return ImagemList.get(0);
	}
	
	//Busca imagem por id do Oferta
		public List<TbImagem> BuscaimagensPorIdOferta(int Codigo){
			String hql = "from TbImagem where tbOferta = "+ Codigo; 
			Query query = this.manager.createQuery(hql); 
			List<TbImagem> ImagemList = query.getResultList();
			return ImagemList;
		}

}
