package acc.br;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.List;

import acc.br.model.Pessoa;

@Path("/pessoas")
public class PessoaResource {

    @GET
    public List<Pessoa> listarTodas() {
        return Pessoa.listAll();
    }

    @GET
    @Path("/{nome}")
    public Pessoa buscarPorNome(@PathParam("nome") String nome) {
        return Pessoa.findByName(nome);
    }
    
    @GET
    @Path("/maiores-de/{idade}")
    public List<Pessoa> listarMaioresDe(@PathParam("idade") int idade) {
        return Pessoa.findOlderThan(idade); // Chama o método findOlderThan para retornar as pessoas com idade superior ao parâmetro
    }

    @POST
    @Transactional
    public Response criarPessoa(Pessoa pessoa) {
        pessoa.persist();
        return Response.status(Response.Status.CREATED).entity(pessoa).build();
    }
}

