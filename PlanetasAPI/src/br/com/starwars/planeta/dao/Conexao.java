package br.com.starwars.planeta.dao;

import com.mongodb.MongoClient;



public class Conexao {
	
	public static String BASEDADOS = "JOGO";
	public static String COLECAO = "PLANETA";
	
	public MongoClient getConMongo() throws Exception {
		
		try {
						
			MongoClient mongo = new MongoClient("localhost", 27017);
			
			System.out.println("Conectado ao banco...");
			
			return mongo;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
