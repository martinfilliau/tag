package com.filliau.martin.tag;

import com.filliau.martin.tag.configuration.AppConf;
import com.filliau.martin.tag.resources.TagResource;
import com.filliau.martin.tag.resources.TagsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author martinfilliau
 */
public class TagApplication extends Application<AppConf>{

    @Override
    public void initialize(Bootstrap<AppConf> bootstrap) {
        // nothin
    }

    @Override
    public void run(AppConf configuration, Environment environment) throws Exception {
        final TagResource tag = new TagResource();
        final TagsResource tags = new TagsResource();
        environment.jersey().register(tag);
        environment.jersey().register(tags);
    }

    
    public static void main(String[] args) throws Exception {
        new TagApplication().run(args);
    }
}
