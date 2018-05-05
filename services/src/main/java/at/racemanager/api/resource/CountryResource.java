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

import at.racemanager.api.entity.Country;
import at.racemanager.api.entity.RmException;
import at.racemanager.data.store.CountryStore;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Resource for countries
 *
 * @author rolhai
 */
@Path("countries")
@Consumes
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RequestScoped
@Interceptors(ResourceExceptionInterceptor.class)
public class CountryResource {

    private static final Logger logger = LogManager.getLogger(CountryResource.class);

    @Inject
    private CountryStore countryStore;

    @GET
    public Response findAll() {
        logger.debug("find all countries");
        return Response.ok(countryStore.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.debug(String.format("find country with id %d", id));
        return Response.ok(countryStore.findById(id)).build();
    }

    @POST
    public Response create(Country country) throws RmException {
        if (country == null) {
            throw new BadRequestException(ResourceError.NO_COUNTRY);
        }
        logger.debug(String.format("create country %s", country.toString()));
        Country createdCountry = countryStore.create(country);
        return Response.ok(createdCountry).build();
    }

    @DELETE
    @Path("{id")
    public Response removeById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.debug(String.format("remove country with id %d", id));
        countryStore.removeById(id);
        return Response.ok().build();
    }
}
