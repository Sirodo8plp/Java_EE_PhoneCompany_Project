package People;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

public class Seller extends Users{

    private int CustomersCount;
    private String ShopLocation;
    private String SellerCode , email , saltPass , salt;

    // Constructor.
    public Seller(String username , String name , String surname , String status , String SellerCode , String email , String password){
        super(username,name,surname,status);
        this.SellerCode = SellerCode;
        this.email = email;
        
        SecureRandom sr = new SecureRandom();
	 	byte[] bytes = new byte[20];
	 	sr.nextBytes(bytes);
	 	
        this.saltPass = Utilities.Encryption.getHashMD5(password, bytes.toString());
        this.salt = bytes.toString();
    }
    /**
     * Seller login constructor
     * @param username
     * @param stmt
     */
    public Seller(String username , Statement stmt) 
    {
    	super();
    	String sql = "Select * from sellers where username ='" + username + "'";
    	try 
    	{
    		ResultSet rs = stmt.executeQuery(sql);
    		while(rs.next()) 
    		{
    			setUsername(username);
    			saltPass = rs.getString("saltpass");
    			salt = rs.getString("salt");
    			CustomersCount = rs.getInt("customerscount");
    			ShopLocation = rs.getString("shoplocation");
    			SellerCode = rs.getString("sellercode");
    			email = rs.getString("email");
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
    	
    	Register_User(username , saltPass , salt , "Seller" , stmt);
    	
    	String insertStmt = "INSERT INTO sellers VALUES (";
		insertStmt += String.valueOf(id) + ",";
		insertStmt += "'" + username + "','" + saltPass + "','" + salt + "',";
		insertStmt += String.valueOf(ThreadLocalRandom.current().nextInt(1, 11)) + ",'Piraeus',";
		insertStmt +=  "'" + SellerCode + "',";
		insertStmt +=  "'" + email + "',";
		insertStmt +=  "'" + fname + "',";
		insertStmt +=  "'" + lname + "')";
		try 
		{
			stmt.executeUpdate(insertStmt);
		} 
		catch (SQLException e) 
		{
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
    public void Add_Customer(String name,String surname){

    }

    public void Create_Payment(Client client){}

    public void Change_Subscription(){}

    public int getCustomersCount() {
        return CustomersCount;
    }

    public void setCustomersCount(int customersCount) {
        CustomersCount = customersCount;
    }

    public String getShopLocation() {
        return ShopLocation;
    }

    public void setShopLocation(String shopLocation) {
        ShopLocation = shopLocation;
    }

    public String getSellerCode() {
        return SellerCode;
    }

    public void setSellerCode(String sellerCode) {
        SellerCode = sellerCode;
    }


}
