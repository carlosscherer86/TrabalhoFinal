package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ParseConversionEvent;

import api.Email;
import api.GoogleMapsDirections;
import match.Comparacao;
import match.Similaridade;
import modelo.TbDemanda;
import modelo.TbImagem;
import modelo.TbMatch;
import modelo.TbOferta;
import diversas.Entity;
import diversas.Sessao;
import ModeloRepositorio.TbDemandaRepository;
import ModeloRepositorio.TbMatchRepository;
import ModeloRepositorio.TbOfertaRepository;

@SessionScoped
@ManagedBean
public class TbMatchBean {
	private List<TbOferta> ofertas;
	private List<TbDemanda> demandas;
	private DataModel lista;
	private TbMatch Match;
	private GoogleMapsDirections directions;

	public void realizarMatch() {
		int similaridade;
		boolean Enviou;
		EntityManager manager = Entity.RetornaEntityManager();
		TbOfertaRepository repositoryOferta = new TbOfertaRepository(manager);
		TbDemandaRepository repositoryDemanda = new TbDemandaRepository(manager);
		TbMatchRepository repository = new TbMatchRepository(manager);
		ofertas = repositoryOferta.buscaTodos();
		demandas = repositoryDemanda.buscaTodos();
		for (TbOferta oferta : ofertas) {
			for (TbDemanda demanda : demandas) {
				if (oferta.getTbPessoaFisica().getPkCodPessoaFisica() != demanda
						.getTbPessoaFisica().getPkCodPessoaFisica()) {
					if (oferta.getTbCategoria().getPkCodCategoria() == demanda
							.getTbCategoria().getPkCodCategoria()) {
						if (repository.verificaMatch(oferta.getPkCodOferta(),
								demanda.getPkCodDemanda())) {
							if ((oferta.getVlrUnidade().doubleValue() >= demanda
									.getVlrMin().doubleValue())
									&& (oferta.getVlrUnidade().doubleValue() <= demanda
											.getVlrMax().doubleValue())) {

								similaridade = RetornaSimilaridade(
										oferta.getNmeDescricaoMatch(),
										demanda.getNmeDescricaoMatch());

								int similaridade2 = (Comparacao.Comparar(
										oferta.getNmeDescricaoMatch(),
										demanda.getNmeDescricaoMatch()));
								similaridade = (int) ((similaridade + similaridade2) / 2);

								if (similaridade < 70) {
									similaridade = Similaridade
											.SimilaridadeCosseno(
													oferta.getNmeDescricaoMatch(),
													demanda.getNmeDescricaoMatch());

									similaridade2 = (Comparacao.Comparar(
											oferta.getNmeDescricaoMatch(),
											demanda.getNmeDescricaoMatch()));
								}

								similaridade = (int) ((similaridade + similaridade2) / 2);
								if (similaridade > 100) {
									similaridade = 100;
								}
								if (similaridade >= 70) {
									Match = new TbMatch();
									directions = new GoogleMapsDirections();
									directions.carregaDirections(
											oferta.concatenaEndereco(),
											demanda.concatenaEndereco());
									try {
										Thread.sleep(250);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}

									Match.setNumDistancia(Integer
											.parseInt(directions.result.routes[0].legs[0].distance.value));
									Match.setTbDemanda(demanda);
									Match.setTbOferta(oferta);
									Match.setStaAtivo((byte) 1);
									Match.setNumSimilaridade(similaridade);
									repository.adiciona(Match);
									Email email = new Email();
									email.enviarEmail(oferta, demanda);
									email.enviarEmailNovo(oferta, demanda);
									email = null;
								}
							}
						}
					}
				}
			}

		}
	}

	// Retorna todos os Match
	public DataModel<TbMatch> getMatchs() {
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaTodos();
		lista = new ListDataModel(MatchList);
		return lista;
	}

	public int RetornaSimilaridade(String oferta, String demanda) {
		int similaridade;
		// similaridade = Similaridade.SimilaridadeCosseno(
		// oferta.getNmeDescricaoMatch(),
		// demanda.getNmeDescricaoMatch());

		// similaridade =
		// Similaridade.DistanciaEuclidiana(oferta.getNmeDescricaoMatch(),
		// demanda.getNmeDescricaoMatch());

		similaridade = Similaridade.SmithWaterman(oferta, demanda);

		// similaridade = Similaridade.Jaro(
		// oferta.getNmeDescricaoMatch(),
		// demanda.getNmeDescricaoMatch());
		return similaridade;
	}

	public DataModel<TbMatch> getMatchsPorOferta() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		TbOfertaRepository repositoryOferta = new TbOfertaRepository(manager);
		TbOferta oferta = new TbOferta();
		oferta = repositoryOferta.BuscaOfertaPorId((int) ses
				.getAttribute("idOfertaMatch"));
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaTodosPorOferta(oferta);
		lista = new ListDataModel(MatchList);
		return lista;
	}

	public DataModel<TbMatch> getMatchsPorDemanda() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		TbDemandaRepository repositoryDemanda = new TbDemandaRepository(manager);
		TbDemanda demanda = new TbDemanda();
		demanda = repositoryDemanda.BuscaDemandaPorId((int) ses
				.getAttribute("idDemandaMatch"));
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaTodosPorDemanda(demanda);
		lista = new ListDataModel(MatchList);
		return lista;
	}

	public TbMatch getMatch() {
		return Match;
	}

	public String detalharDemanda() {
		Match = new TbMatch();
		Match = (TbMatch) (lista.getRowData());
		return "demanda";
	}

	public String detalharOferta() {
		Match = new TbMatch();
		Match = (TbMatch) (lista.getRowData());
		return "oferta";
	}

	public void setMatch(TbMatch match) {
		Match = match;
	}

	// Retorna todos os Match demanda por código de pessoa física
	public DataModel<TbMatch> getMatchDemanda() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaMatchDemanda((Integer) ses
				.getAttribute("idusuario"));
		lista = new ListDataModel(MatchList);
		return lista;
	}

	// Retorna todos os Match oferta tipo venda e por pessoa fisica
	public DataModel<TbMatch> getMatchOfertaVenda() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaMatchOferta(
				(Integer) ses.getAttribute("idusuario"), 1);
		lista = new ListDataModel(MatchList);
		return lista;
	}

	// Retorna todos os Match oferta tipo locacao e por pessoa fisica
	public DataModel<TbMatch> getMatchOfertaLocacao() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaMatchOferta(
				(Integer) ses.getAttribute("idusuario"), 2);
		lista = new ListDataModel(MatchList);
		return lista;
	}

	// Retorna todos os Match oferta tipo doacao e por pessoa fisica
	public DataModel<TbMatch> getMatchOfertaDoacao() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbMatchRepository repository = new TbMatchRepository(manager);
		List<TbMatch> MatchList = null;
		MatchList = repository.buscaMatchOferta(
				(Integer) ses.getAttribute("idusuario"), 3);
		lista = new ListDataModel(MatchList);
		return lista;
	}
}
