package com.filliau.martin.tag.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author martinfilliau
 */
public class Tag {
    
    @JsonProperty
    @NotEmpty
    private String slug;
    
    @JsonProperty
    @NotEmpty
    private String name;
    
    @JsonProperty
    private String description;
    
    @JsonProperty
    private List<String> sameAs;
    
    @JsonProperty
    private List<String> broader;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSameAs() {
        return sameAs;
    }

    public void setSameAs(List<String> sameAs) {
        this.sameAs = sameAs;
    }

    public List<String> getBroader() {
        return broader;
    }

    public void setBroader(List<String> broader) {
        this.broader = broader;
    }

    public SolrInputDocument toSolrDocument() {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("slug", slug);
        doc.addField("name", name);
        doc.addField("description", description);
        doc.addField("sameAs", sameAs);
        doc.addField("broader", broader);
        return doc;
    }
}
