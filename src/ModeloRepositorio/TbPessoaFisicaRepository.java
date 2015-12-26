package ModeloRepositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.TbPessoaFisica;


public class TbPessoaFisicaRepository {
	private EntityManager manager;

	public TbPessoaFisicaRepository(EntityManager manager) {
		this.manager = manager;
	}

	//Adiciona pessoa física
	public void adiciona(TbPessoaFisica PessoaFisica) {
		this.manager.persist(PessoaFisica);
	}
	
	//Atualiza dados de pessoa física
	public void atualizar(TbPessoaFisica PessoaFisica) {
		this.manager.merge(PessoaFisica);
	}
	
	//Busca pessoa física por id
	public TbPessoaFisica BuscaPessoaPorId(int Codigo){
		return this.manager.find(TbPessoaFisica.class, Codigo);
	}

	//Login do usuário
	public List<TbPessoaFisica> loginUsuario(String pSenha, String pEmail) {
		Query query = this.manager.createQuery("select x from TbPessoaFisica x "
				+ "where NME_EMAIL = :email and NME_SENHA = :senha" );
		query.setParameter("email", pEmail);
		query.setParameter("senha", pSenha);
		return query.getResultList();
	}
	
	//Valida email
	public TbPessoaFisica validaEmail(String pEmail) {
		String hql = "from TbPessoaFisica where NME_EMAIL = :email";
		Query query = this.manager.createQuery(hql);
		query.setParameter("email", pEmail);
		TbPessoaFisica PessoaList;
		try{
			PessoaList = (TbPessoaFisica) query.getResultList().get(0);
		}
		catch (Exception e) {
			PessoaList= null;
		}
		return PessoaList;
	}
	
	//Busca anunciante por id
	public TbPessoaFisica buscaCliente(Integer pId) {
		String hql = "from TbPessoaFisica where pkCodPessoaFisica = :id";
		Query query = this.manager.createQuery(hql);
		query.setParameter("id", pId);
		List<TbPessoaFisica> PessoaList = query.getResultList();
		return PessoaList.get(0);
	}

}
