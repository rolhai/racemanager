package at.racemanager.resource;

import at.racemanager.api.service.DriversApiService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("drivers")
@Consumes
@Produces(MediaType.APPLICATION_JSON)
public class DriversResource {

    @Inject
    private DriversApiService driversApiService;

    @GET
    public Response getDriver(String namefilter) {
        return Response.ok(driversApiService.getDrivers()).build();
    }
}
