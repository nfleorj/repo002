package br.com.starwars.planeta.dao;

import java.util.ArrayList;

import java.util.Collection;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.starwars.planeta.vo.PlanetaVO;
import br.com.starwars.planeta.ws.ConsultaFilmeWS;

public class PlanetaDAO {
	

	/**
	 * Insere novo planeta
	 * @param vo
	 * @throws Exception
	 */
	public void inserePlaneta(PlanetaVO vo) throws Exception {

		Conexao con = new Conexao();
		
		MongoClient mongo = con.getConMongo();

		try {
			
			MongoDatabase database = mongo.getDatabase(Conexao.BASEDADOS);
			
			MongoCollection<Document> planeta = database.getCollection(Conexao.COLECAO);	
			
			Document doc = new Document();

			doc.put("NOME", vo.getNome());
			doc.put("CLIMA", vo.getClima());
			doc.put("TERRENO", vo.getTerreno());
			
			planeta.insertOne(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongo.close();	
		}
		
	}
	
	
	/**
	 * Lista todos os planetas
	 * @return
	 * @throws Exception
	 */
	public Collection<PlanetaVO> listaPlanetas() throws Exception {
		
		Collection<PlanetaVO> coll = new ArrayList<PlanetaVO>();
		
		Conexao con = new Conexao();
		
		MongoClient mongo = con.getConMongo();

		try {
			
			MongoDatabase database = mongo.getDatabase(Conexao.BASEDADOS);
			
			MongoCollection<Document> planeta = database.getCollection(Conexao.COLECAO);	
			
			FindIterable<Document> resultadoDaBusca = planeta.find();
		
			for (Document document : resultadoDaBusca) {
				
				PlanetaVO vo = new PlanetaVO();
				
				vo.setNome(document.getString("NOME"));
				vo.setClima(document.getString("CLIMA"));
				vo.setTerreno(document.getString("TERRENO"));
				vo.setFilmes(ConsultaFilmeWS.consultaFilmes(document.getString("NOME")));
				
				coll.add(vo);
				
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			mongo.close();
		}
		
		return coll;
	}
	
	
	
	/**
	 * Consulta planeta pelo NOME
	 * @param nome
	 * @return
	 * @throws Exception
	 */
	public Collection<PlanetaVO> consultaPlanetaPorNome(String nome) throws Exception {
		
		Collection<PlanetaVO> coll = new ArrayList<PlanetaVO>();
		
		Conexao con = new Conexao();
		
		MongoClient mongo = con.getConMongo();

		try {
				
			MongoDatabase database = mongo.getDatabase(Conexao.BASEDADOS);
			
			MongoCollection<Document> planeta = database.getCollection(Conexao.COLECAO);	
			
			FindIterable<Document> resultadoDaBusca = planeta.find(new Document("NOME", nome));
							
			for (Document document : resultadoDaBusca) {
				PlanetaVO vo = new PlanetaVO();
				vo.setNome(document.getString("NOME"));
				vo.setClima(document.getString("CLIMA"));
				vo.setTerreno(document.getString("TERRENO"));
				vo.setFilmes(ConsultaFilmeWS.consultaFilmes(document.getString("NOME")));
				coll.add(vo);
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			mongo.close();			
		}

		return coll;
	
	}
	
	/**
	 * Consulta planeta por ID
	 * @param idPlaneta
	 * @return
	 * @throws Exception
	 */
	public Collection<PlanetaVO> consultaPlanetaPorId(String idPlaneta) throws Exception {
		
		Collection<PlanetaVO> coll = new ArrayList<PlanetaVO>();
		
		Conexao con = new Conexao();
		
		// idPlaneta = "ObjectId(\""+idPlaneta+"\")";	
		
		MongoClient mongo = con.getConMongo();

		try {
			
			ObjectId obj = new ObjectId(idPlaneta);
				
			MongoDatabase database = mongo.getDatabase(Conexao.BASEDADOS);
			
			MongoCollection<Document> planeta = database.getCollection(Conexao.COLECAO);	
			
			FindIterable<Document> resultadoDaBusca = planeta.find(new Document("_id", obj));
							
			for (Document document : resultadoDaBusca) {
				PlanetaVO vo = new PlanetaVO();
				vo.setNome(document.getString("NOME"));
				vo.setClima(document.getString("CLIMA"));
				vo.setTerreno(document.getString("TERRENO"));
				vo.setFilmes(ConsultaFilmeWS.consultaFilmes(document.getString("NOME")));
				coll.add(vo);
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			mongo.close();			
		}

		return coll;
	
	}
	
	
	/**
	 * Exclui planetas
	 * @param nome
	 * @throws Exception
	 */
	public void deletaPlaneta(String nome) throws Exception {
		
		Conexao con = new Conexao();
		
		MongoClient mongo = con.getConMongo();
		
		try {
			
			MongoDatabase database = mongo.getDatabase(Conexao.BASEDADOS);
			
			MongoCollection<Document> planeta = database.getCollection(Conexao.COLECAO);	
			
			planeta.deleteOne(new Document("NOME", nome));
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			mongo.close();
		}
		
	}

}
