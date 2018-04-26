/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.api.resource;

import at.racemanager.api.entity.RmParameterException;
import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.json.Json;
import javax.validation.ConstraintViolationException;
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
        } catch (RmParameterException ex) {
            String msg = Json.createObjectBuilder().add("parameter validation: ", ex.getMessage()).build().toString();
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        } catch (EJBException ex) {
            String msg = handleEJBException(ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        } catch (Exception ex) {
            String msg = Json.createObjectBuilder().add("error: ", ex.getMessage()).build().toString();
            return Response.serverError().entity(msg).build();
        }
        return proceedResponse;
    }

    private String handleEJBException(EJBException ex) {
        if (ex == null) {
            return "";
        }
        Throwable cause = ex.getCause();
        while (cause != null && cause.getCause() != null) { //&& !(cause instanceof ConstraintViolationException)) {
            cause = cause.getCause();
        }
        String prefix = "";
        if (cause instanceof ConstraintViolationException) {
            prefix = "bean-validation error";
        } else {
            prefix = "ejb error";
        }
        String msg = cause == null ? ex.getMessage() : cause.getMessage();
        return Json.createObjectBuilder().add(prefix + ": ", msg).build().toString();
    }
}
