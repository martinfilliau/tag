package com.filliau.martin.tag.resources;

import com.filliau.martin.tag.representations.Tag;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;

/**
 *
 * @author martinfilliau
 */
@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

    private final SolrServer solr;
    
    public TagResource(SolrServer solr) {
        this.solr = solr;
    }
    
    @GET
    @Path("{slug}")
    public Tag getTag(@PathParam("slug") String slug) {
        return null;
    }

    @POST
    public Response post(@Valid Tag tag) throws URISyntaxException {
        try {
            solr.add(tag.toSolrDocument());
            solr.commit();
        } catch (SolrServerException ex) {
            Logger.getLogger(TagResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        } catch (IOException ex) {
            Logger.getLogger(TagResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }
        return Response.created(new URI(tag.getSlug())).build();
    }
}
