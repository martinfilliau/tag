package com.filliau.martin.tag.configuration;

import io.dropwizard.Configuration;

/**
 *
 * @author martinfilliau
 */
public class AppConf extends Configuration {
    
    private String solrLocation = new String();

    public String getSolrLocation() {
        return solrLocation;
    }

    public void setSolrLocation(String solrLocation) {
        this.solrLocation = solrLocation;
    }
}
