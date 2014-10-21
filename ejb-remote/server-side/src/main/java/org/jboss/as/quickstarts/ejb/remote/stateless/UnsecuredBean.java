/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.as.quickstarts.ejb.remote.stateless;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.jboss.as.quickstarts.ejb.remote.secured.RemoteEcho;
import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;

/**
 *
 * @author dpospisi
 */
@Stateless
@Remote(RemoteEcho.class)
public class UnsecuredBean implements RemoteEcho {

    @EJB(beanName = "SecuredBean")
    RemoteEcho secured;

    @Override
    public String secureEcho(String word) {

        try {
            SecurityClient client = SecurityClientFactory.getSecurityClient();
            client.setSimple("user", "mypass");
            client.login();

            return secured.secureEcho(word);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
