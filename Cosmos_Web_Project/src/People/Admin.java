package People;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;

public class Admin extends Users {

    private int SellersCount;
    private String AdminCode , email , saltPass , salt; //for register.

    // Constructor.
    public Admin(String username , String name , String surname , String status , String AdminCode , String email , String password){
        super(username , name , surname , "Admin");
        this.AdminCode = AdminCode;
        this.email = email;
        
        SecureRandom sr = new SecureRandom();
	 	byte[] bytes = new byte[20];
	 	sr.nextBytes(bytes);
	 	
        this.saltPass = Utilities.Encryption.getHashMD5(password, bytes.toString());
        this.salt = bytes.toString();
    }
    
    public Admin(String username , Statement stmt) 
    {
    	super();
    	String sql = "Select * from admins where username ='" + username + "'";
    	try 
    	{
    		ResultSet rs = stmt.executeQuery(sql);
    		while(rs.next()) 
    		{
    			setUsername(rs.getString("username"));
    			saltPass = rs.getString("saltpass");
    			salt = rs.getString("salt");
    			email = rs.getString("email");
    			AdminCode = rs.getString("admincode");
    			setName(rs.getString("fname"));
    			setSurname(rs.getString("lname"));
    		}
    		System.out.println(getUsername() + email);
    	}
    	catch(Exception e) 
    	{
    		System.out.println(e.toString());
    	}
    }

    public void Register(Statement stmt , HttpServletRequest request)
    {
    	setId(stmt);
    	String username = getUsername() , fname = getName() , lname = getSurname();
    	int id = getId();
    	String saltPass = getSaltPass() , salt = getSalt() , email = getEmail() , code = getAdminCode();
    	
    	Register_User(username , saltPass , salt , "Admin" , stmt);
    	
    	String insertStmt = "INSERT INTO admins VALUES (";
		insertStmt += String.valueOf(id) + ",";
		insertStmt += "'" + username + "','" + saltPass + "','" + salt + "'";
		insertStmt +=  ",'" + email + "',";
		insertStmt +=  "'" + code + "',";
		insertStmt +=  "'" + fname + "',";
		insertStmt +=  "'" + lname + "')";
		
		try {
			stmt.executeUpdate(insertStmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return;
    }
    
    public void set_profile_values(HttpServletRequest request) 
    {
    	request.getSession().setAttribute("username", getUsername());
    	request.getSession().setAttribute("email", email);
    	request.getSession().setAttribute("firstname", getName());
    	request.getSession().setAttribute("lastname" , getSurname());
    	request.getSession().setAttribute("infooo", "");
    }

    
    public void Update_Account(String username , String email , String fname , String lname , Statement stmt , HttpServletRequest request) 
    {
    	if (username.equals("") && email.equals("") && fname.equals("") && lname.equals("")) {
    		
    		request.getSession().setAttribute("infooo", "Δεν έγινε καμία αλλαγή στον λογαριασμό σας.");
    		return;
    	}
    	
    	String info = ""; //for any errors
    	String sql = "Update admins set ";
    	try {
    		
    		if (!username.equals("")) {
    			
    			ResultSet rs = stmt.executeQuery("Select * from users where username='" + username + "'");
        		if (rs.next()) info += "Το όνομα σύνδεσης χρήστη υπάρχει ήδη.";
        		else {
        			setUsername(username);
    				request.getSession().setAttribute("username", username);
    				sql += "username='" + username + "'";
        		}
    		}
    		
    		if(!email.equals("")) {
    			
    			ResultSet rs = stmt.executeQuery("Select * from admins where email='" + username + "'");
        		if(rs.next()) info += "Το email χρησιμοποιείται ήδη.";
        		else {
        			this.email = email;	
        			request.getSession().setAttribute("email", email);
        			sql +=  sql.equals("Update admins set ") ? "email='" + email + "'" : ",email='" + email + "'";
        		}
    		}
    		
    		if(info.equals("")) {
    			
    			if (!username.equals("")) stmt.executeUpdate( "Update users set username='" + username + "' where id=" + getId() + "" );
    			
    			if (!fname.equals("")) {
    				setName(fname);
    				request.getSession().setAttribute("firstname", fname);
    				sql += sql.equals("Update admins set ") ? "fname='" + fname + "'" : ",fname='" + fname + "'";
    			}
    			
    			if (!lname.equals("")) {
    				setSurname("");
    				request.getSession().setAttribute("lastname", lname);
    				sql += sql.equals("Update admins set ") ? "lname='" + lname + "'" : ",lname='" + lname + "'" ;
    			}
    			
    			sql += " where id=" + getId();
    			
    			stmt.executeUpdate(sql);
    			request.getSession().setAttribute("infooo", "Επιτυχής ενημέρωση λογαριασμού.");
    			return;
    		}
    		else {
    			request.getSession().setAttribute("infooo", info );
    			return;
    		}
    	}
    	catch(Exception e) {e.printStackTrace();
    	System.out.println(sql);}
    }
    
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSaltPass() {
		return saltPass;
	}

	public void setSaltPass(String saltPass) {
		this.saltPass = saltPass;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	// Different methods to be used later on...
    public void Create_Seller(){
        //Seller sel = new Seller();
        //SellersCount ++;
    }

    public void Delete_Seller(Statement stmt , HttpServletRequest request, String username, String acode){
    	
    	
    	String deleteStmt = "DELETE FROM sellers WHERE sellers.username = '"+ username +"'";
    	String deleteStmt1 = "DELETE FROM users WHERE users.username = '"+ username +"'";
    	//String checkCode =  "SELECT admincode FROM admins WHERE admins.admincode = '" + acode + "'";
    	//System.out.println(checkCode);
    	
    	try {
    		
    	//	stmt.executeUpdate(checkCode);
    		//System.out.println(checkCode);
    		//if(acode == username) {
    		
    			stmt.executeUpdate(deleteStmt);
    			stmt.executeUpdate(deleteStmt1);
    			request.getSession().setAttribute("infooo", "Επιτυχής διαγραφή πωλητή.");
    		//}
    		
    	}catch (Exception e) 
		{
			e.printStackTrace();
		}
	    return;
    	
    	
    }

    public void Create_Customer(){

    }

    public void Delete_Customer(Statement stmt , HttpServletRequest request, String clientid){
    	
    	
    	String deleteStmt = "DELETE FROM client_subs WHERE client_subs.client_id = '" + clientid + "'";
		String deleteStmt1 = "DELETE FROM client WHERE client.id = '" + clientid + "'";
		String deleteStmt2 = "DELETE FROM users WHERE users.id = '" + clientid + "'";
    	
    	try {
    	
    		stmt.executeUpdate(deleteStmt);
			stmt.executeUpdate(deleteStmt1);
			stmt.executeUpdate(deleteStmt2);
			request.getSession().setAttribute("infooo", "Επιτυχής διαγραφή πελάτη.");
    		
    		
    	}catch(Exception e) {e.printStackTrace();}
    	
    }

    public void Create_New_Subscription(){

    }

    public int getSellersCount() {
        return SellersCount;
    }

    public void setSellersCount(int sellersCount) {
        SellersCount = sellersCount;
    }

    public String getAdminCode() {
        return AdminCode;
    }

    public void setAdminCode(String adminCode) {
        AdminCode = adminCode;
    }
}
