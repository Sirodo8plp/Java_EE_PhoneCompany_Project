package People;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    			salt = rs.getString(salt);
    			email = rs.getString("email");
    			AdminCode = rs.getString("admincode");
    			setName(rs.getString("fname"));
    			setSurname(rs.getString("lname"));
    		}
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
    	
    	Register_User(username , saltPass , salt , "Client" , stmt);
    	
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

    public void Delete_Seller(String SellerName){}

    public void Create_Customer(){

    }

    public void Delete_Customer(String CustomerName){}

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
