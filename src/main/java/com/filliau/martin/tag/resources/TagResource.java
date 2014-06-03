package com.filliau.martin.tag.resources;

import com.filliau.martin.tag.representations.Tag;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author martinfilliau
 */
@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

    @GET
    @Path("{slug}")
    public Tag getTag(@PathParam("slug") String slug) {
        return null;
    }

    @POST
    public Tag post(@Valid Tag tag) {
        return tag;
    }
}
