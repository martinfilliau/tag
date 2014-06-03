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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryResponseParser;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

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
        SolrQuery q = new SolrQuery();
        q.setRequestHandler("/get");
        q.set("id", slug);  // id, not name of the field
        QueryRequest req = new QueryRequest(q);
        req.setResponseParser(new BinaryResponseParser());
        QueryResponse rsp;
        try {
            rsp = req.process(solr);
            SolrDocument out = (SolrDocument)rsp.getResponse().get("doc");
            if (out != null) {
                return Tag.fromSolrDocument(out);
            } else {
                throw new WebApplicationException(404);
            }
        } catch (SolrServerException ex) {
            Logger.getLogger(TagResource.class.getName()).log(Level.SEVERE, null, ex);
        }
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
