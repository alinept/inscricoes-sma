package br.com.eventomeioambiente.conexao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;

public class Conexao {
	/**
	 * @parameter expression="${encoding}" default-value="${project.build.sourceEncoding}"
	 */
	private String encoding;
	private Connection con = null;

	public Statement stmt;

	public Connection conectar() {

		String endereco = "jdbc:postgres://xalmripwnslzof:0696121188bd798e39bb2fdad8f3a51f81fb91a6044bd3b14918db57bbc6a393@ec2-75-101-142-91.compute-1.amazonaws.com:5432/d9ar29pvfs8jli";
		String usuario = "postgres";
		String senha = "admin";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(endereco, usuario, senha);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;
	}

	public void desconectar() {
		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	
}
