/*
 * Copyright 2018 rolhai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.racemanager.api.resource;

import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.RmException;
import at.racemanager.data.store.DriverStore;

import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource for drivers
 *
 * @author rolhai
 */
@Path("drivers")
@Consumes
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RequestScoped
@Interceptors(ResourceExceptionInterceptor.class)
public class DriverResource {

    private static final Logger logger = Logger.getLogger(DriverResource.class.getName());

    @Inject
    private DriverStore driverStore;

    @GET
    public Response findAll() {
        logger.info("find all drivers sync");
        return Response.ok(driverStore.findAll()).build();
    }

    @GET
    @Path("async")
    @Asynchronous
    public void findAllAsync(@Suspended AsyncResponse asyncResponse) {
        logger.info("find all drivers async");
        asyncResponse.resume(Response.ok(driverStore.findAll()).build());
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.info(String.format("find driver with id %d", id));
        return Response.ok(driverStore.findById(id)).build();
    }

    @POST
    public Response create(Driver driver) throws RmException {
        if (driver == null) {
            throw new BadRequestException(ResourceError.NO_DRIVER);
        }
        logger.info(String.format("create driver %s", driver.toString()));
        Driver createdDriver = driverStore.create(driver);
        return Response.ok(createdDriver).build();
    }

    @PUT
    public Response update(Driver driver) throws RmException {
        if (driver == null) {
            throw new BadRequestException(ResourceError.NO_DRIVER);
        }
        logger.info(String.format("update driver %s", driver.toString()));
        return Response.ok(driverStore.update(driver)).build();
    }

    @DELETE
    @Path("{id")
    public Response removeById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.info(String.format("remove driver with id %d", id));
        driverStore.removeById(id);
        return Response.ok().build();
    }
}
