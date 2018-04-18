/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.business;

import javax.ejb.Stateless;

/**
 *
 * @author rolhai
 */
@Stateless
public class MasterDataBusiness {

    public String getMasterData() {
        return "MasterData";
    }
}
