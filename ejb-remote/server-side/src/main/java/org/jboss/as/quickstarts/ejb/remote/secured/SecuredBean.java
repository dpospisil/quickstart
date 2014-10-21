/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.as.quickstarts.ejb.remote.secured;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author dpospisi
 */

@Stateless
@RolesAllowed({"guest"})
@SecurityDomain("other")
@Remote(RemoteEcho.class)
public class SecuredBean implements RemoteEcho {

    @Override
    public String secureEcho(String word) {
        return word + " " + word;
    }
    
}
