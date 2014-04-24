/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Kelly
 */
public class QueryManager {
    public QueryManager(){
        this.db = new DbManager();
    }
    ResultSet rs;
    Statement st;
    DbManager db;
    
    //Login query
    public void login(String email, String password){
        String login = "SELECT email, Wachtwoord FROM account where email = '" +
                email + "' AND wachtwoord = '" + password + "'";
        String loggedIn = "";
        try{
            rs = db.doQuery(login);
            if(rs.next()){
                loggedIn = "Logged in succesful";
            }
            else if(rs.getString("email") != email){
                loggedIn =  "Incorrect e-mail adres";
            }
            else if(rs.getString("wachtwoord") != password){
                loggedIn = "Incorrect wachtwoord";
            }
        }
        catch(SQLException E){
            JOptionPane.showMessageDialog(null, E.getMessage());
        }
    }
    
    
}
