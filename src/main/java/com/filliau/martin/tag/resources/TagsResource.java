package com.filliau.martin.tag.resources;

import com.filliau.martin.tag.representations.Tag;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.solr.client.solrj.SolrServer;

/**
 *
 * @author martinfilliau
 */
@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagsResource {
    
    private final SolrServer solr;
    
    public TagsResource(SolrServer solr) {
        this.solr = solr;
    }
    
    @GET
    public List<Tag> getTag(@QueryParam("name") String name) {
        return null;
    }

}
