package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbCategoria;
import modelo.TbOferta;

public class TbCategoriaRepository {
	private EntityManager manager;

	public TbCategoriaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	//Busca categoria por id
	public TbCategoria BuscaCatPorId(int Codigo){
		return this.manager.find(TbCategoria.class, Codigo);
	}

	//Insere categoria
	public void adiciona(TbCategoria Categoria) {
		this.manager.persist(Categoria);
	}
	
	//Edita categoria
	public void atualizar(TbCategoria Categoria) {
		this.manager.merge(Categoria);
	}
	
	//Deleta categoria
	public void deletar(TbCategoria Categoria) {
		this.manager.remove(this.manager.find(TbCategoria.class, Categoria.getPkCodCategoria()));
	}

	//Busca todas as categorias
	public List<TbCategoria> buscaTodos() {
		Query query = this.manager.createQuery("select x from TbCategoria x");
		return query.getResultList();
	}
}
