/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.api.resource;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.json.Json;
import javax.ws.rs.core.Response;

/**
 *
 * @author rolhai
 */
@Interceptor
public class ResourceExceptionInterceptor {

    @AroundInvoke
    public Object handleException(InvocationContext context) {
        Object proceedResponse;
        try {
            proceedResponse = context.proceed();
        } catch (Exception ex) {
            String msg = Json.createObjectBuilder().add("error", ex.getMessage()).build().toString();
            return Response.serverError().entity(msg).build();
        }
        return proceedResponse;
    }
}
