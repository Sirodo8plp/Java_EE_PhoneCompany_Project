package People;
import Misc.*;
import javax.servlet.http.HttpServletRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;
public class Client extends Users {

    private String afm;
    private String phoneNumber , email , saltPass , salt;
    private double debt;
    public PhoneNumber phonenumber;
    public Program program;
    public Bill bill;

    /**
     * Register constructor
     * @param afm
     * @param username
     * @param name
     * @param surname
     * @param status
     * @param phonenumber1
     * @param email
     * @param password
     */
    public Client(String afm , String username , String name , String surname , 
    		String status , long phonenumber1 , 
    		String email , String password){
        super(username , name , surname , status);
        this.afm = afm;
        phonenumber = new PhoneNumber(phonenumber1,"");
        program = new Program(0);
        this.email = email;
        
        SecureRandom sr = new SecureRandom();
	 	byte[] bytes = new byte[20];
	 	sr.nextBytes(bytes);
	 	
        this.saltPass = Utilities.Encryption.getHashMD5(password, bytes.toString());
        this.salt = bytes.toString();
    }
    
    /**
     * Login Constructor
     * @param username in order to determine table row
     * @param stmt in order to connect
     */
    public Client(String username , Statement stmt)
    {
    	super();
    	String sql = "Select * from client where username ='" + username + "'";
    	double tmp = 0;
    	try 
    	{
    		ResultSet rs = stmt.executeQuery(sql);
    		while(rs.next()) 
    		{
    			setId(rs.getInt("id"));
    			setUsername(rs.getString("username"));
    			saltPass = rs.getString("saltpass");
    			salt = rs.getString("salt");
    			afm = rs.getString("afm");
    			email = rs.getString("email");
    			debt = rs.getDouble("debt");
    			phonenumber = new PhoneNumber(rs.getLong("phonenumber") , rs.getString("program"));
    			program = new Program(rs.getInt("id"));
    			setName(rs.getString("fname"));
    			setSurname(rs.getString("lname"));
    			tmp = rs.getDouble("bill");
    		}
    		bill = new Bill(stmt , phonenumber.getPhoneNum());
    		program.set_Program(stmt);
    		
    		if(tmp!=0) bill.setCurrent_bill(tmp);
    		else bill.setCurrent_bill(debt + program.getCharge());
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    public void Update_Account(String username , String email , String fname , String lname , Statement stmt , HttpServletRequest request) 
    {
    	if (username.equals("") && email.equals("") && fname.equals("") && lname.equals("")) {
    		
    		request.getSession().setAttribute("infooo", "Δεν έγινε καμία αλλαγή στον λογαριασμό σας.");
    		return;
    	}
    	
    	String info = ""; //for any errors
    	String sql = "Update client set ";
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
    			
    			ResultSet rs = stmt.executeQuery("Select * from client where email='" + username + "'");
        		if(rs.next()) info += "Το email χρησιμοποιείται ήδη.";
        		else {
        			this.email = email;
        			request.getSession().setAttribute("email", email);
        			sql +=  sql.equals("Update client set ") ? "email='" + email + "'" : ",email='" + email + "'";
        		}
    		}
    		
    		if(info.equals("")) {
    			
    			if (!username.equals("")) stmt.executeUpdate( "Update users set username='" + username + "' where id=" + getId() + "" );
    			
    			if (!fname.equals("")) {
    				setName(fname);
    				request.getSession().setAttribute("firstname", fname);
    				sql += sql.equals("Update client set ") ? "fname='" + fname + "'" : ",fname='" + fname + "'";
    			}
    			
    			if (!lname.equals("")) {
    				setSurname("");
    				request.getSession().setAttribute("lastname", lname);
    				sql += sql.equals("Update client set ") ? "lname='" + lname + "'" : ",lname='" + lname + "'" ;
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

    public void Register(Statement stmt , HttpServletRequest request)
    {
    	String[] sub_name = new String[]{"Cosmos Mobile L","Cosmos Mobile XL","Cosmos Mobile S","Cosmos Mobile M",
    			"Cosmos Green Max","Cosmos Green Start" , "Cosmos Green Plus" , "Cosmos Green Final"};
    	
    	double[] sub_charge = new double[]{30.9,42.9,31.99,40.59,47.48,27.95,35.75,49.64};
    	
    	int n = ThreadLocalRandom.current().nextInt(1, 9);
    	phonenumber.setPackageNumber(sub_name[n]);
    	setId(stmt);
    	int id = getId();
    	
    	setId(stmt);
    	String username = getUsername() , fname = getName() , lname = getSurname();
    	debt = ThreadLocalRandom.current().nextInt(0, 21);
    	bill = new Bill(phonenumber.getPhoneNum() , debt);
    	bill.setCharge(sub_charge[n]);
    	bill.setCurrent_bill(debt + sub_charge[n]);
    	bill.append(stmt);
    	bill.append_calls(stmt);
    	
    	Register_User(username , saltPass , salt , "Client" , stmt);
    	
    	String insertStmt = "INSERT INTO client Values(";
    	insertStmt += String.valueOf(id) + ",";
    	insertStmt += "'" + username + "','" + saltPass + "','" + salt + "'";
    	insertStmt +=  "," + afm + ",";
    	insertStmt +=  "'" + email + "',";
    	insertStmt +=  "" + String.valueOf(debt) + ",";
    	insertStmt +=  "" + String.valueOf(phonenumber.getPhoneNum()) + ",";
    	insertStmt += "'" +  sub_name[n] + "'," + String.valueOf(bill.getCharge()) + ",";
    	insertStmt +=  "'" + fname + "',";
    	insertStmt +=  "'" + lname + "')";
    	try {
    		stmt.executeUpdate(insertStmt);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	program.set_program_registration(stmt, id, n, phonenumber);
    	return;
    }
    
    public void set_profile_sv(HttpServletRequest request) 
    {
    	request.getSession().setAttribute("username", getUsername());
    	request.getSession().setAttribute("email", email);
    	request.getSession().setAttribute("firstname", getName());
    	request.getSession().setAttribute("lastname", getSurname());
    }
    
    public void Add_Request(String req , Statement stmt , HttpServletRequest request) 
    {
    	if(req.equals(null) || req.equals("")) {
    		request.getSession().setAttribute("infooo", "Το αίτημα σας ήταν κενό.");
    		return;
    	}
    	int req_id = 0;
    	try 
    	{
    		ResultSet rs = stmt.executeQuery("Select max(id) from requests");
    		while(rs.next()) req_id = rs.getInt("max") + 1;
    		
    		stmt.executeUpdate("Insert into requests values("+req_id+",'" + getUsername() + "','" + req + "')");
    		request.getSession().setAttribute("infooo", "Το αίτημα σας καταχωρήθηκε.<br>Μέσος χρόνος απάντησης: 1-2 μέρες.");
    		return;
    	}
    	catch(Exception e) {e.printStackTrace();}
    }
    
    public void setAfm(String afm) {
		this.afm = afm;
	}

	// Different methods to be used later on...
    public double getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public void Show_Account(){
        //show current object's info-account
    }

    public void Show_Call_History(){

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void Pay_Account(){

    }

    public void Show_Debt(){}

   
    
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

	public PhoneNumber getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(PhoneNumber phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getAfm() {
		return afm;
	}

}
