package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.TbEndereco;

public class TbEnderecoRepository {
	private EntityManager manager;

	public TbEnderecoRepository(EntityManager manager) {
		this.manager = manager;
	}

	//Adiciona pessoa física
	public void adiciona(TbEndereco Endereco) {
		this.manager.persist(Endereco);
	}
	
	//Atualiza dados de pessoa física
	public void atualizar(TbEndereco Endereco) {
		this.manager.merge(Endereco);
	}
	
	//Busca pessoa física por id
	public TbEndereco BuscaPessoaPorId(int Codigo){
		return this.manager.find(TbEndereco.class, Codigo);
	}
	
	//Busca anunciante por id
	public TbEndereco buscaEndereco(Integer pId) {
		String hql = "from TbEndereco where tbOferta.pkCodOferta = :id";
		Query query = this.manager.createQuery(hql);
		query.setParameter("id", pId);
		List<TbEndereco> PessoaList = query.getResultList();
		return PessoaList.get(0);
	}

}
