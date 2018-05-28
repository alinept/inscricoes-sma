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

	private Connection con = null;

	public Statement stmt;

	public Connection conectar() {

		String endereco = "jdbc:postgresql://localhost:5432/BDEventoMeioAmbiente";
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
