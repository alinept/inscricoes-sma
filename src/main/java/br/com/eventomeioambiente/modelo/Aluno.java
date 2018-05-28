package br.com.eventomeioambiente.modelo;

import java.io.Serializable;
import java.util.List;



public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idAluno;
	private String matricula;
	private String senha;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private String qrcode;
	Minicurso minicurso;
	
		
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public Minicurso getMinicurso() {
		return minicurso;
	}
	public void setMinicurso(Minicurso minicurso) {
		this.minicurso = minicurso;
	}
	
		
}