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
import at.racemanager.api.entity.Track;
import at.racemanager.data.store.TrackStore;

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
 * Resource for tracks
 *
 * @author rolhai
 */
@Path("tracks")
@Consumes
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RequestScoped
@Interceptors(ResourceExceptionInterceptor.class)
public class TrackResource {

    private static final Logger logger = Logger.getLogger(TrackResource.class.getName());

    @Inject
    private TrackStore trackStore;

    @GET
    public Response findAll() {
        logger.info("find all tracks");
        return Response.ok(trackStore.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.info(String.format("find track with id %d", id));
        return Response.ok(trackStore.findById(id)).build();
    }

    @POST
    public Response create(Track track) throws RmException {
        if (track == null) {
            throw new BadRequestException(ResourceError.NO_TRACK);
        }
        logger.info(String.format("create track %s", track.toString()));
        Track createdTrack = trackStore.create(track);
        return Response.ok(createdTrack).build();
    }

    @DELETE
    @Path("{id")
    public Response removeById(@PathParam("id") long id) throws RmException {
        if (id <= 0) {
            throw new BadRequestException(ResourceError.NO_ID);
        }
        logger.info(String.format("remove track with id %d", id));
        trackStore.removeById(id);
        return Response.ok().build();
    }
}
