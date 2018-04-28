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
import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("drivers")
@Consumes
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RequestScoped
@Interceptors(ResourceExceptionInterceptor.class)
public class DriversResource {

    private static final Logger logger = LogManager.getLogger(DriversResource.class);

    @Inject
    private DriverStore driverStore;

    @PersistenceContext
    protected EntityManager em;

    @GET
    public Response getDrivers() {
        logger.debug("get all drivers sync");
        return Response.ok(driverStore.findAll()).build();
    }

    @GET
    @Path("async")
    @Asynchronous
    public void getDriversAsync(@Suspended AsyncResponse asyncResponse) {
        logger.debug("get all drivers async");
        asyncResponse.resume(Response.ok(driverStore.findAll()).build());
    }

    @GET
    @Path("{driverId}")
    public Response getDriver(@PathParam("driverId") long driverId) throws RmException {
        if (driverId <= 0) {
            throw new BadRequestException();
        }
        logger.debug(String.format("get driver with id %d", driverId));
        return Response.ok(driverStore.findById(driverId)).build();
    }

    @POST
    public Response createDriver(Driver driver) throws RmException {
        if (driver == null) {
            throw new BadRequestException();
        }
        logger.debug(String.format("create driver %s", driver.toString()));
        Driver createdDriver = driverStore.create(driver);
        return Response.ok(createdDriver).build();
    }

    @DELETE
    @Path("{driverId")
    public Response deleteDriver(@PathParam("driverId") long driverId) throws RmException {
        if (driverId <= 0) {
            throw new BadRequestException();
        }
        logger.debug(String.format("remove driver with id %d", driverId));
        driverStore.removeById(driverId);
        return Response.ok().build();
    }
}
