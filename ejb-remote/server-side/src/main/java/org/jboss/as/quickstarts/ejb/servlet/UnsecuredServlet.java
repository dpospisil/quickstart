/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.as.quickstarts.ejb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.as.quickstarts.ejb.remote.secured.RemoteEcho;

/**
 *
 * @author dpospisi
 */
@WebServlet("/unsecured")
public class UnsecuredServlet extends HttpServlet {
    
    @EJB(beanName = "UnsecuredBean")
    RemoteEcho unsecured;

    @EJB(beanName = "SecuredBean")
    RemoteEcho secured;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter print = response.getWriter();
        print.println("<html>");
        print.println("<body>");        
        try {
            print.println("Calling unsecured.");
            unsecured.secureEcho("Bambulik");
            print.println("Call succedded.");
            //print.println("Calling secured.");
            //secured.secureEcho("Bambulik");
            //print.println("Call succedded.");
        } catch (Exception e) {
            print.println("Got exception:");
            e.printStackTrace(print);
        } finally {
            print.println("</body>");
            print.println("</html>");                    
        }
    }
}
