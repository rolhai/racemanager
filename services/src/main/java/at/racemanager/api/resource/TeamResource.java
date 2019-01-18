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

import at.racemanager.api.entity.RmException;
import at.racemanager.api.entity.Team;
import at.racemanager.data.store.DriverStore;
import at.racemanager.data.store.TeamStore;

import java.util.logging.Logger;

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

/**
 * Resource for teams
 *
 * @author rolhai
 */
@Path("teams")
@Consumes
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RequestScoped
@Interceptors(ResourceExceptionInterceptor.class)
public class TeamResource {

    private static final Logger logger = Logger.getLogger(TeamResource.class.getName());

    @Inject
    private TeamStore teamStore;

    @Inject
    private DriverStore driverStore;

    @GET
    public Response findAll() {
        logger.info("find all teams");
        return Response.ok(teamStore.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.info(String.format("find team with id %d", id));
        return Response.ok(teamStore.findById(id)).build();
    }

    @POST
    public Response create(Team team) throws RmException {
        if (team == null) {
            throw new BadRequestException(ResourceError.NO_TEAM);
        }
        logger.info(String.format("create team %s", team.toString()));
        Team createdTeam = teamStore.create(team);
        return Response.ok(createdTeam).build();
    }

    @POST
    @Path("{teamId}/drivers")
    public Response addDriver(@PathParam("teamId") long teamId, long driverId) throws RmException {
        if (teamId <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        if (driverId <= 0) {
            throw new BadRequestException(ResourceError.NO_DRIVER);
        }
        teamStore.addDriver(teamId, driverId);
        return Response.ok().build();
    }

    @DELETE
    @Path("{teamId}/drivers")
    public Response removeDriver(@PathParam("teamId") long teamId, long driverId)
            throws RmException {
        if (teamId <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        if (driverId <= 0) {
            throw new BadRequestException(ResourceError.NO_DRIVER);
        }
        teamStore.removeDriver(teamId, driverId);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id")
    public Response removeById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.info(String.format("remove team with id %d", id));
        teamStore.removeById(id);
        return Response.ok().build();
    }
}
