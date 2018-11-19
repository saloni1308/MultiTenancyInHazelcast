package com.architecture.prod.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.architecture.prod.model.UserRegionContext;

@Path("/userContext")
public class UserContextSwitchingController {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String test() {
    return UserRegionContext.getRegionId();
  }

  @PUT
  @Consumes(MediaType.TEXT_PLAIN)
  public void test(final String context) {
    UserRegionContext.setRegionId(context);
  }
}
