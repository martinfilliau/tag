package com.filliau.martin.tag.resources;

import com.filliau.martin.tag.representations.Tag;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

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
        SolrQuery q = new SolrQuery();
        q.setQuery("name:"+name);
        QueryResponse rsp;
        try {
            rsp = solr.query(q);
            SolrDocumentList docs = rsp.getResults();
            List<Tag> tags = new ArrayList<Tag>();
            Iterator<SolrDocument> iter = rsp.getResults().iterator();
            SolrDocument resultDoc;
            while (iter.hasNext()) {
                resultDoc = iter.next();
                tags.add(Tag.fromSolrDocument(resultDoc));
            }
            return tags;
        } catch (SolrServerException ex) {
            Logger.getLogger(TagsResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(500);
        }
    }

}
