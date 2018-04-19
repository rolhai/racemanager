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
public abstract class RmException extends Exception {

    public RmException(String message) {
        super(message);
    }

    public RmException(String message, Throwable cause) {
        super(message, cause);
    }

}
