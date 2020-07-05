package People;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utilities.Encryption;

public abstract class Users {

    private String username , name , surname , status;
    private int id;
    private static int usersCounter;
    
    // Constructor
    
    public Users(String username , String name , String surname , String status){
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.status = status;
        
    }
    
    public Users() {}

    // Method that checks if all user/seller fields have valid input.
    
    public int getId() {
		return id;
	}

	public void setId(Statement stmt) 
	{
		try {
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM users");
			while(rs.next()) this.id =  rs.getInt("max") + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exc at user setIDd");
		}
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}

	public static String Register(String afm ,String username, String status, String email, String phonenumber , String scode , Statement stmt){
        
		String error_message = "";
    	error_message += Check_Username_Existence_Reg(stmt , username);
        error_message += Check_Email_Existence_Reg(stmt,email,status);
        
        if(status.equals("admins") || status.equals("sellers")) error_message += Check_code_Existence_Reg(stmt , scode , status);
        else {
        	error_message += Check_Phonenumber_Existence_Reg(stmt , phonenumber);
        	error_message += Check_afm_Existence_Reg(stmt,afm);
        }
        
        return error_message;
    }
	/**
	 * Registers user before inserting the data into proper user table.
	 * @param username
	 * @param saltp
	 * @param salt
	 * @param type
	 * @param stmt
	 */
	public void Register_User(String username , String saltp , String salt , String type , Statement stmt) {
		String insert = "Insert into users values(";
		insert += String.valueOf(getId()) + ",'" + username + "','" + saltp + "',";
		insert += "'" + type + "','" + salt +"')";
		
		try {
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exc at user/reg method");
		}
		return;
	}
   

	/**
	 * Check if user exists || if password is correct.
	 * @param username
	 * @param password
	 * @param stmt
	 * @param type if user exists,determine his type(db table).
	 * @return Message indicating whether to proceed or not.
	 */
    public static String login(String username , String password , Statement stmt , StringBuilder type)
    {	
    	boolean user_exists = false;
    	try 
    	{
    		String saltPass = "" , salt = "";
    		ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='"+username+"'");
    		while(rs.next()) 
    		{
    			user_exists = true;
    			saltPass = rs.getString("saltedpass");
    			salt = rs.getString("salt");
    			
    			if("Client".equals(rs.getString("user_type"))) type.append("client");
    			else if("Seller".equals(rs.getString("user_type"))) type.append("sellers");
    			else type.append("admins");
    		}
    		if(!user_exists) return "Δεν υπάρχει τέτοιος χρήστης στο σύστημα.";
    		if(!saltPass.equals(Encryption.getHashMD5(password,salt))) return "Λανθασμένος κωδικός/όνομα σύνδεσης χρήστη";
    	}
    	catch(Exception e) {return e.toString();}
    	return "";
    }

    public void logout(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int getUsersCounter() {
        return usersCounter;
    }

    public static void setUsersCounter(int usersCounter) {
        Users.usersCounter = usersCounter;
    }
    
    
   // public String getUsername() {
     //   return username;
   // }
    
    
    
    
    
    
    
    public static String Check_Username_Existence_Reg(Statement stmt , String username ) {
    	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM users where username='"+username+"'");
			while(rs.next()) {
				return "Το όνομα σύνδεσης χρήστη είναι δεσμευένο.<br>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
    public static String Check_Email_Existence_Reg(Statement stmt,String email , String table){
    	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+table+" where email='"+email+"'");
			while(rs.next()) {
				return "Το email χρησιμοποιείται.<br>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
    public static String Check_Phonenumber_Existence_Reg(Statement stmt , String pn ) {
    	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM client where phonenumber="+pn+"");
			while(rs.next()) {
				return "Ο αριθμός τηλεφώνου είναι δεσμευένος.<br>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
    public static String Check_code_Existence_Reg(Statement stmt,String code , String table){
    	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+table+" where sellercode='"+code+"'");
			while(rs.next()) {
				return "Ο ειδικός κωδικός δεν υπάρχει.<br>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
    public static String Check_afm_Existence_Reg(Statement stmt,String afm){
    	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM client where afm='"+String.valueOf(afm) + "'");
			while(rs.next()) {
				return "Το Α.Φ.Μ. δεν υπάρχει.<br>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
}
