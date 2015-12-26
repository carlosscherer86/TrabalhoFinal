package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ModeloRepositorio.TbCategoriaRepository;
import ModeloRepositorio.TbImagemRepository;
import ModeloRepositorio.TbPessoaFisicaRepository;
import ModeloRepositorio.TbOfertaRepository;
import diversas.Entity;
import diversas.Sessao;
import modelo.TbCategoria;
import modelo.TbImagem;
import modelo.TbOferta;

@SessionScoped
@ManagedBean
public class TbImagemBean {
	private TbImagem Imagem = new TbImagem();
	private int CodigoOferta;
	private TbImagem ImagemTemp;
	private ListDataModel lista;
	private Part file;

	public TbImagem getImagem() {
		return Imagem;
	}

	public void setImagem(TbImagem imagem) {
		Imagem = imagem;
	}

	public int getCodigoOferta() {
		return CodigoOferta;
	}

	public void setCodigoOferta(int codigoOferta) {
		CodigoOferta = codigoOferta;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public DataModel<TbImagem> getimagens() {
		HttpSession ses = Sessao.RetornaSessao();
		EntityManager manager = Entity.RetornaEntityManager();
		TbImagemRepository repository = new TbImagemRepository(manager);
		List<TbImagem> AnuncioList;
		AnuncioList = repository.BuscaimagensPorIdOferta((Integer) ses
				.getAttribute("idOfertaUser"));
		lista = new ListDataModel(AnuncioList);
		return lista;
	}

	public void carregarImagemExcluir() {
		ImagemTemp = (TbImagem) (lista.getRowData());
		EntityManager manager = Entity.RetornaEntityManager();
		TbImagemRepository repository = new TbImagemRepository(manager);
		repository.deletar(ImagemTemp);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Categoria Excluída com sucesso!",
						"Categoria Excluída com sucesso!"));
	}

	// Valida imagem
	public void validarImagem(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		if (file.getSize() > 1000000) {
			msgs.add(new FacesMessage("Imagem muito grande"));
		}
		if (!"image/jpeg".equals(file.getContentType())) {
			msgs.add(new FacesMessage(file.getContentType()));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}

	// Realiza upload da imagem
	public void RealizarUpload() throws IOException, InterruptedException {
		Imagem = new TbImagem();
		if (file != null) {
			HttpSession ses = Sessao.RetornaSessao();
			EntityManager manager = Entity.RetornaEntityManager();
			TbImagemRepository repository = new TbImagemRepository(manager);
			TbOfertaRepository repositoryOferta = new TbOfertaRepository(
					manager);
			int id = repository.RetornaMax() + 1;
			String diretorio = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("imagem_produto");
			try {
				diretorio = diretorio.substring(0,
						diretorio.indexOf("\\.metadata"));
				diretorio = diretorio
						+ "\\TrabalhoFinal\\WebContent\\imagem_produto\\";
			} catch (Exception e) {
				diretorio = FacesContext.getCurrentInstance()
						.getExternalContext().getRealPath("imagem_produto")+"\\";
			}
			System.out.println(diretorio);
			diretorio = diretorio.replace("\\", "/");
			System.out.println(diretorio);
			System.out.println(diretorio + id + getNomeImagem(file));
			file.write(diretorio + id + getNomeImagem(file));
			System.out.println(diretorio + id + getNomeImagem(file));
			Imagem.setNmeImagem(id + getNomeImagem(file));
			System.out.println(id + getNomeImagem(file));
			Imagem.setTbOferta(repositoryOferta.BuscaOfertaPorId((Integer) ses
					.getAttribute("idOfertaUser")));
			repository.adiciona(Imagem);
			Imagem = new TbImagem();
		}
	}

	private static String getNomeImagem(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1);
			}
		}
		return "ok";
	}

}
