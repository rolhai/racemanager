/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.api.entity;

/**
 *
 * @author rolhai
 */
public class RmParameterException extends RmException {

    public RmParameterException(String parameter, String message) {
        super(parameter + " " + message);
    }
}
