/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.as.quickstarts.ejb.remote.stateless;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.security.auth.login.LoginContext;
import org.jboss.as.quickstarts.ejb.remote.secured.RemoteEcho;
import org.jboss.security.auth.callback.UsernamePasswordHandler;
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

    private static final String USER = "guest";
    private static final String PASSWD = "kilo_maso1";

    @Override
    public String secureEcho(String word) {
        return type2(word);
    }

    private String type1(String word) {
        try {

            SecurityClient client = SecurityClientFactory.getSecurityClient();
            client.setSimple(USER, PASSWD);
            client.login();

            String response = secured.secureEcho(word);

            client.logout();

            return response;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String type2(String word) {
        try {
        UsernamePasswordHandler handler = new UsernamePasswordHandler(USER, PASSWD);
        LoginContext lc = new LoginContext("client-login", handler);
        lc.login();
            String response = secured.secureEcho(word);
        lc.logout();
        return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
