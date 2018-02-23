package at.racemanager.resource;

import at.racemanager.data.service.DriverDataService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("drivers")
@Consumes
@Produces(MediaType.APPLICATION_JSON)
public class DriversResource {

    @Inject
    private DriverDataService driverDataService;

    @GET
    public Response getDriver(String namefilter) {
        return Response.ok(driverDataService.getDrivers()).build();
    }

    @GET
    @Path("/{driverId}")
    public Response findDriverById(@PathParam("driverId") long driverId) {
        return Response.ok(driverDataService.findDriver(driverId)).build();
    }
}
