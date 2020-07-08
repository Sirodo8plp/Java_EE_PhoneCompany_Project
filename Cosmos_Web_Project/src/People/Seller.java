package People;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

public class Seller extends Users{

    private int CustomersCount;
    private String ShopLocation;
    private String SellerCode , email , saltPass , salt;
    public List<List<String>> requests = new ArrayList<List<String>>();  
    
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
    			setId(rs.getInt("id"));
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
    	request.getSession().setAttribute("infooo", "");
    }
    
    public void Update_Account(String username , String email , String fname , String lname , Statement stmt , HttpServletRequest request) 
    {
if (username.equals("") && email.equals("") && fname.equals("") && lname.equals("")) {
    		
    		request.getSession().setAttribute("infooo", "Δεν έγινε καμία αλλαγή στον λογαριασμό σας.");
    		return;
    	}
    	
    	String info = ""; //for any errors
    	String sql = "Update sellers set ";
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
    			
    			ResultSet rs = stmt.executeQuery("Select * from sellers where email='" + username + "'");
        		if(rs.next()) info += "Το email χρησιμοποιείται ήδη.";
        		else {
        			this.email = email;
        			request.getSession().setAttribute("email", email);
        			sql +=  sql.equals("Update sellers set ") ? "email='" + email + "'" : ",email='" + email + "'";
        		}
    		}
    		
    		if(info.equals("")) {
    			
    			if (!username.equals("")) stmt.executeUpdate( "Update users set username='" + username + "' where id=" + getId() + "" );
    			
    			if (!fname.equals("")) {
    				setName(fname);
    				request.getSession().setAttribute("firstname", fname);
    				sql += sql.equals("Update sellers set ") ? "fname='" + fname + "'" : ",fname='" + fname + "'";
    			}
    			
    			if (!lname.equals("")) {
    				setSurname("");
    				request.getSession().setAttribute("lastname", lname);
    				sql += sql.equals("Update sellers set ") ? "lname='" + lname + "'" : ",lname='" + lname + "'" ;
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

    public void Create_Payment(String pn , String date , String price , Statement stmt , HttpServletRequest request)
    {
    	//check if phone exists
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from client where phonenumber="+pn);
    		if (!rs.next()) {
    			request.getSession().setAttribute("infooo", "Δε βρέθηκε πελάτης με τέτοιον αριθμό.");
    			return;
    		}
    		rs = stmt.executeQuery("Select * from bill where phonenumber=" + pn);
    		if (rs.next()) {
    			request.getSession().setAttribute("infooo", "Έχει ήδη εκδοθεί λογαριασμός για<br>τον αριθμό "+pn);
    			return;
    		}
    		
    		stmt.executeUpdate("Insert into bill values("+pn+",'"+date+"',"+price+")");
    		request.getSession().setAttribute("infooo", "Επιτυχής δημιουργία λογαριασμού.");
    	}
    	catch(Exception e) {e.printStackTrace();
    	request.getSession().setAttribute("infooo", e);}
    	return;
    }
    
    public void Collect_Requests(Statement stmt) {
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from requests");
    		while(rs.next()) {
    			if(rs.getString("answer").equals("f")) {
    				List<String> l = new ArrayList<>();
        			l.add(String.valueOf(rs.getInt("id")));
        			l.add(rs.getString("request_giver"));
        			l.add(rs.getString("add_info"));
        			requests.add(l);
    			}
    		}
    	}
    	catch(Exception e) {e.printStackTrace();}
    }
    
    public void Answer_Request(String req_id , String response , String giver , Statement stmt , HttpServletRequest request) 
    {
    	try 
    	{
    		if(response.equals("")) {
    			request.getSession().setAttribute("infooo", "Η απάντηση δεν είναι έγκυρη.");
    		}
    		else {
    			//get request id
    			int i =0;
    			ResultSet rs = stmt.executeQuery("Select max(id) from responses");
    			if(rs.next()) i = rs.getInt("max") + 1;
    			
    			//first change requests table
        		stmt.executeUpdate("update requests set answer=true where id=" + req_id);
        		
        		//insert to responses
        		stmt.executeUpdate("Insert into responses values("+String.valueOf(i)+",'"+getUsername()+"','"+response+"'"
        				+ ",'"+giver+"')");
        		request.getSession().setAttribute("infooo", "Η απάντηση καταχωρήθηκε.");
    		}
    	}
    	catch(Exception e) {e.printStackTrace();}
    }
    
    public void Add_Program(Statement stmt , HttpServletRequest request , String sub_name , String minutes , String sms ,
    						String mb , String charge , String cmin , String csms , String cmb) 
    {
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from subs where subname='"+sub_name+"'");
    		if(rs.next()) {
    			request.getSession().setAttribute("infooo", "Το όνομα προγράμματος υπάρχει ήδη.");
        		return;
    		}
    		else {
        		String info = "";
        		if (Double.parseDouble(charge)<20) info += "Alert: Το πάγιο είναι μικρότερο του μέσου όρου.";
        		rs = stmt.executeQuery("Select max(sub_num) from subs");
        		int sub_num = 0;
        		while(rs.next()) sub_num = rs.getInt("max") + 1;
        			
        		String sql = "Insert into subs values('"+sub_name+"',"+minutes+","+mb+","+sms+",";
        		sql += cmin +"," + cmb + "," + csms+ ","+ charge + "," + String.valueOf(sub_num)+")";
        		System.out.println(sql);
        		stmt.executeUpdate(sql);
        		info = "<br>Επιτυχής προσθήκη προγράμματος";
        		request.getSession().setAttribute("infooo", info);
        		return;
        	}
    	}
    	catch(Exception e) {e.printStackTrace();}
    }
    
    public void Delete_Program(Statement stmt , HttpServletRequest request , String sub_name) {
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from client where program='"+sub_name+"'");
    		if(rs.next()) {
    			request.getSession().setAttribute("infooo","Το πρόγραμμα χρησιμοποιείται από πελάτες.<br>"
    					+ "Η διαγραφή προγράμματος απέτυχε.");
    			return;
    		}
    		else {
    			stmt.executeUpdate("delete from subs where subname='"+sub_name+"'");
    			request.getSession().setAttribute("infooo", "Επιτυχής διαγραφή προγράμματος.");
    			return;
    		}
    	}
    	catch(Exception e) {e.printStackTrace();}
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
