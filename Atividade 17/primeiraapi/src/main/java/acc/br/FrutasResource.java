package acc.br;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import acc.br.model.Fruta;

import org.jboss.logging.Logger;

@Path("/frutas")
public class FrutasResource {
	
	private static final Logger LOG = Logger.getLogger(FrutasResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
    	LOG.debug("Exibindo frutas");
        return Fruta.listAll();
    }
    
    @POST
    @Transactional
    public void novaFruta(Fruta fruta) {
    	fruta.persist();
    	LOG.debug("Fruta adicionada!");
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarFruta(@PathParam("id") long id) {
        Fruta.deleteById(id);
        LOG.debug("Fruta deletada!");
    }

}
