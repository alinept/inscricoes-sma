package br.com.eventomeioambiente.controlle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.eventomeioambiente.dao.AlunoDao;
import br.com.eventomeioambiente.modelo.Aluno;


@RequestScoped
@ManagedBean (name = "alunoBean")
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	AlunoDao alunoDao;
	private Aluno alunoBusca;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Aluno aluno;
	
	@PostConstruct
	public void init() {
		alunoDao = new AlunoDao();
		alunoBusca = new Aluno();
		aluno = new Aluno();
	
	}//tiramos de usuarioBean

	public String buscarAluno(){
		try{
			aluno = alunoDao.buscarAluno(alunoBusca.getMatricula(), alunoBusca.getSenha());
			if(aluno != null){
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
				session.setAttribute("aluno", aluno);
				return "/inicio";
			}else{
				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Usuário ou Senha incorretos,tente novamente!", ""));
				
			}
		}catch(Exception e){
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				"Erro ao autenticar o usuário", ""));
			
		}
		return null;
	}
	public AlunoDao getAlunoDao() {
		return alunoDao;
	}
	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}
	public Aluno getAlunoBusca() {
		return alunoBusca;
	}
	public void setAlunoBusca(Aluno alunoBusca) {
		this.alunoBusca = alunoBusca;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
