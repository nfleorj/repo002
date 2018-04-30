package br.com.starwars.planeta.ws;

import java.util.Collection;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.starwars.planeta.dao.PlanetaDAO;
import br.com.starwars.planeta.vo.PlanetaVO;

/**
 * 
 * @author leonardo
 * Na URL http://localhost:8080/Jogo/planetaWS vai trazer todos os planetas. 
 * Na URL URL: http://localhost:8080/Jogo/planetaWS?nomePlaneta=Terra vai trazer somente com nome = Terra
 * 
 * 
 */

@Path("/planetaWS")
public class ConsultaPlanetasWS {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String mostraPlanetas(@QueryParam("nomePlaneta") String nomePlaneta) {
		
		PlanetaDAO dao = new PlanetaDAO();
		
		PlanetaVO vo = new PlanetaVO();
		
		vo.setNome(nomePlaneta);
	
		try {
			
			Collection<PlanetaVO> coll;
			
			if(vo.getNome() == null) {
				coll = dao.listaPlanetas();
			}else {
				coll = dao.consultaPlanetaPorNome(vo.getNome());
			}
			
			Gson g = new Gson();
			
			return g.toJson(coll);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	
	}	
}
