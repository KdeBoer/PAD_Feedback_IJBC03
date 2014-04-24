/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 *
 * @author Alex
 */
public class Servlet extends HttpServlet {

    private static final String VELOCITY_TEMPLATES_PREFIX = "/vsl";
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	
        Properties _Properties = new Properties();

        _Properties.setProperty("resource.loader", "webapp");
        _Properties.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
        _Properties.setProperty("webapp.resource.loader.path", VELOCITY_TEMPLATES_PREFIX);

        ServletContext _ServletContext = getServletContext();
        Velocity.setApplicationAttribute("javax.servlet.ServletContext", _ServletContext);
        try {
            Velocity.init(_Properties);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        VelocityContext vv1_Context = new VelocityContext();
        
        
        
        
        
         Template template = null;
         vv1_Context.put("errorCode", 1234);
         vv1_Context.put("errorCode", "Dit wordt meegegeven aan velocity");
         
         final String s_Template = "login.html";
        try {

            template = Velocity.getTemplate("login.html");
        } catch (ResourceNotFoundException rnfe) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, rnfe);
            // couldn't find the template
        } catch (ParseErrorException pee) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, pee);
            // syntax error: problem parsing the template
        } catch (MethodInvocationException mie) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, mie);
            // something invoked in the template
            // threw an exception
        } catch (Exception e) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        PrintWriter out = response.getWriter();
        
        
        if(template != null){
            template.merge(vv1_Context, out);
        }
        out.close();
    }
    
    
   
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
