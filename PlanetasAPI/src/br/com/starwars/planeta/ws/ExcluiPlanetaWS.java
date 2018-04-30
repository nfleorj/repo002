package br.com.starwars.planeta.ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import br.com.starwars.planeta.dao.PlanetaDAO;
import br.com.starwars.planeta.vo.PlanetaVO;


/**
 * 
 * @author leonardo
 * 
 * URL: http://localhost:8080/Jogo/excluiPlanetaWS?nomePlaneta=Planeta X
 *
 */

@Path("/excluiPlanetaWS")
public class ExcluiPlanetaWS {
	
	@DELETE
	public void excluiPlaneta(@QueryParam("nomePlaneta") String nomePlaneta) {
		
		try {
			
			PlanetaVO vo = new PlanetaVO();
			
			vo.setNome(nomePlaneta);
			
			if(vo.getNome() == null || vo.getNome().equals("")) {
				throw new Exception("CAMPO NULO/VAZIO - A informação de Nome do Planeta (nomePlaneta) é obrigatórias.");
			}
			
			PlanetaDAO dao = new PlanetaDAO();
			
			dao.deletaPlaneta(vo.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
