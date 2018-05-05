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
 * 
 */

@Path("/planetaIdWS")
public class ConsultaPlanetasIdWS {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String mostraPlanetas(@QueryParam("idPlaneta") String idPlaneta) {
		
		PlanetaDAO dao = new PlanetaDAO();
	
		try {
			
			Collection<PlanetaVO> coll;
			
			if(idPlaneta == null || idPlaneta.equals("")) {
				throw new Exception("CAMPO VAZIO - A informação do ID do planeta é obrigatória.");
			}
			
			coll = dao.consultaPlanetaPorId(idPlaneta);
			
			Gson g = new Gson();
			
			return g.toJson(coll);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	
	}	
}
