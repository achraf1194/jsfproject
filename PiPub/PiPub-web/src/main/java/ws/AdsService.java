package ws;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.PiPub.util.AdvertisementServiceRemote;

@RequestScoped
@Path("/ads")
public class AdsService {
	
	@EJB
	AdvertisementServiceRemote adsService;
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(){
		return Response.ok().entity(adsService.findAllByLikes()).build();
	}

}
