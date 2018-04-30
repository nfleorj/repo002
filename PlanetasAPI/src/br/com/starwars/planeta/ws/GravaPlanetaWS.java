package br.com.starwars.planeta.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import br.com.starwars.planeta.dao.PlanetaDAO;
import br.com.starwars.planeta.vo.PlanetaVO;

/**
 * 
 * @author leonardo
 * 
 * URL: http://localhost:8080/Jogo/gravaPlanetaWS?nomePlaneta=Planeta X&climaPlaneta=Sem presença de Oxigênio. Impossivel para a vida&terrenoPlaneta=Terreno acidentado, muito rochoso e miuto vermelho
 *
 */

@Path("/gravaPlanetaWS")
public class GravaPlanetaWS {
	
	@POST
	public void gravaPlaneta(@QueryParam("nomePlaneta") String nomePlaneta, 
			@QueryParam("climaPlaneta") String climaPlaneta,
			@QueryParam("terrenoPlaneta") String terrenoPlaneta) {
	
		try {
			
			if(nomePlaneta == null || climaPlaneta == null || terrenoPlaneta == null) {
				throw new Exception("CAMPOS NULOS - As informações de Nome, Clima e Terreno são obrigatórias.");
			}

			PlanetaVO vo = new PlanetaVO();
			
			vo.setNome(nomePlaneta);
			vo.setClima(climaPlaneta);
			vo.setTerreno(terrenoPlaneta);
			
			if(vo.getNome().equals("") || vo.getClima().equals("") || vo.getTerreno().equals("")) {
				throw new Exception("CAMPOS VAZIOS - As informações de Nome, Clima e Terreno são obrigatórias.");
			}
			
			PlanetaDAO dao = new PlanetaDAO();

			dao.inserePlaneta(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		

	}

}
