package br.com.eventomeioambiente.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.eventomeioambiente.conexao.Conexao;
import br.com.eventomeioambiente.modelo.Palestrante;

public class PalestranteDao {

	public int inserirPalestrante(Palestrante palestrante) throws SQLException{
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		
		String consulta = "Insert into palestrante(idPalestrante, nome, descricao,curriculo) values('"+
		                                palestrante.getIdPalestrante()+ "', '" + palestrante.getNome()+"', '" + 
				                               palestrante.getDescricao()+"', '" +palestrante.getCurriculo()+"')";
		
		Statement stm = (Statement) cc.createStatement();
		int result = stm.executeUpdate(consulta);
		
		c.desconectar();
		return result;		
}
	
	 public Palestrante buscarPalestrantePorId(int idPalestrante) throws SQLException{
    	 
 	    Conexao c = new Conexao();
			Connection cc = c.conectar();
			Palestrante p = new Palestrante();
			/**Usada para realizar as instruções SQL*/
			String consulta = "SELECT * FROM palestrante WHERE id_palestrante ="+idPalestrante;
			
			Statement stm = (Statement) cc.createStatement();
			ResultSet result = stm.executeQuery(consulta);
			
			while(result.next()){
				
				p.setIdPalestrante(result.getInt("id_palestrante"));
				p.setNome(result.getString("nome"));				
				p.setDescricao(result.getString("descricao"));
				p.setCurriculo(result.getString("curriculo"));
				p.setFoto(result.getString("foto"));
				
			}
			
			c.desconectar();
			return p;
	 }
}
