/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import DatabaseBroker.BrokerBazePodataka;
import DatabaseBroker.BrokerBazePodataka1;
import domain.GeneralDObject;

/**
 *
 * @author Sinisa
 */
public abstract class OpsteIzvrsenjeSO {

    static public BrokerBazePodataka bbp = new BrokerBazePodataka1();
    GeneralDObject gdo;

    synchronized public boolean opsteIzvrsenjeSO() {
        bbp.makeConnection();
        boolean signal = izvrsiSO();
        if (signal == true) {
            bbp.commitTransation();
        } else {
            bbp.rollbackTransation();
        }
        bbp.closeConnection();
        return signal;
    }

    abstract public boolean izvrsiSO();
}
