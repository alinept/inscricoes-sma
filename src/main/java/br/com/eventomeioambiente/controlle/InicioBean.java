package br.com.eventomeioambiente.controlle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.TabChangeEvent;

import br.com.eventomeioambiente.dao.AlunoDao;
import br.com.eventomeioambiente.dao.MinicursoDao;
import br.com.eventomeioambiente.modelo.Aluno;
import br.com.eventomeioambiente.modelo.Minicurso;

@SessionScoped
@ManagedBean(name = "InicioBean")
public class InicioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Aluno alunologado;
	AlunoDao alunoDao;
	List<Minicurso> minicursos;
	MinicursoDao minicursoDao;
	Minicurso minicursoSelecionado;
	static String codigoQr;
	boolean isMinicursoSelecionado = false;
	private List<String> images;

	@PostConstruct
	public void init() {

		images = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			images.add("sma" + i + ".jpg");
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		alunologado = (Aluno) session.getAttribute("aluno");
		if (alunologado != null) {
			minicursoDao = new MinicursoDao();
			minicursoSelecionado = new Minicurso();
			alunoDao = new AlunoDao();
			try {
				minicursos = minicursoDao.buscarTodosMinicursos();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/EventoMeioAmbiente/index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		session.removeAttribute("aluno");
		return "/index";
	}

	public String salvarInscricao() {
		if (minicursoSelecionado.getIdPalestra() != 0) {
			if (minicursoSelecionado.getQntVagas() > 0) {
				alunologado.setMinicurso(minicursoSelecionado);

				String qr = geraStringAleatoria();
				alunologado.setQrcode(qr);

				try {

					if (!alunoDao.possuiInscricao(alunologado)) {

						minicursoDao.atualizarVagas(minicursoSelecionado);
						alunoDao.atualizaQrcode(alunologado);
						int i = alunoDao.iserirMinicursoAluno(alunologado);
						if (i != 0) {
							return "/confirmacao";
						} else {

							FacesContext ctx = FacesContext
									.getCurrentInstance();
							ctx.addMessage(null, new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Erro ao incluir minicurso", ""));
						}
					} else {
						FacesContext ctx = FacesContext.getCurrentInstance();
						ctx.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										"Já existe uma inscrição realizada para esse usuário!",
										""));

					}

				} catch (SQLException e) {

					e.printStackTrace();
				}
			} else {
				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_WARN,
						"Minicurso selecionado não possui vagas disponíveis",
						""));
			}
		} else {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"Selecione um minicurso primeiro!",
					""));
		}
		return null;
	}

	public Aluno getAlunologado() {
		return alunologado;
	}

	public void setAlunologado(Aluno alunologado) {
		this.alunologado = alunologado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Minicurso> getMinicursos() {
		return minicursos;
	}

	public void setMinicursos(List<Minicurso> minicursos) {
		this.minicursos = minicursos;
	}

	public MinicursoDao getMinicursoDao() {
		return minicursoDao;
	}

	public void setMinicursoDao(MinicursoDao minicursoDao) {
		this.minicursoDao = minicursoDao;
	}

	public Minicurso getMinicursoSelecionado() {
		return minicursoSelecionado;
	}

	public void setMinicursoSelecionado(Minicurso minicursoSelecionado) {
		isMinicursoSelecionado = true;
		this.minicursoSelecionado = minicursoSelecionado;

	}

	public void excluirMinicursoSelecionado() {
		isMinicursoSelecionado = false;
		minicursoSelecionado = new Minicurso();
	}

	public static String geraStringAleatoria() {
		// Determia as letras que poderão estar presente nas chaves
		String letras = "abcdefghijklmnopqrstuvxz0123456789";
		String qr = "";
		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			int v = random.nextInt(32);
			qr = qr + letras.substring(v, v + 1);
		}

		return qr;

	}

	public boolean getIsMinicursoSelecionado() {
		return isMinicursoSelecionado;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void onTabChange(TabChangeEvent event) {
		System.out.println(event.getTab().getId());
	}

}
