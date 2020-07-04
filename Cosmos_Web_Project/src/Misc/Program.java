package Misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

public class Program {

    private String SubscriptionName;
    private int minutes,mb,sms;
    private float CostMin,CostMB,CostSms;
    private double charge;
    private int client_id;
    public static List<List<String>> All_Subs = new ArrayList<List<String>>();
    
    public Program() {
    	//null constructor for using All_Subs in home page.
    }
    
   	/**
   	 * Program constructor :
   	 * 	1) Firstly,instantiate client's id.
   	 * 	2)Then,instantiate program attributes with:
   	 * 		2.a)minutes,mb,sms with set_Program
   	 * 		2.b)Costmin,CostMB,CostSms,charge with set_Program_sv
   	 * @param client_id
   	 */
    public Program(int client_id){

        this.client_id = client_id;
    }
    /**
     * Instantiate client's program values.
     * @param stmt
     */
    public void set_Program(Statement stmt) 
    {
    	try {
			ResultSet rs = stmt.executeQuery("Select * from client_subs where client_id="+client_id+"");
			while(rs.next()) 
			{
				SubscriptionName = rs.getString("subname");
				minutes = Integer.parseInt(rs.getString("minutes"));
				mb = Integer.parseInt(rs.getString("mb"));
				sms = Integer.parseInt(rs.getString("sms"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Set program's session variables in
     * clients page(top-left location).
     * @param stmt
     * @param request
     */
    public void set_Program_sv(Statement stmt , HttpServletRequest request) 
    {
    	try {
			ResultSet rs = stmt.executeQuery("Select * from subs where subname='" + SubscriptionName + "'");
			while(rs.next()) 
			{
				request.getSession().setAttribute("subname", rs.getString("subname"));
				
				if(Integer.parseInt(rs.getString("minutes"))==-1)
					request.getSession().setAttribute("submins", "Απεριόριστα Λεπτά");
				else
					request.getSession().setAttribute("submins", rs.getString("minutes") + " Λεπτά");
				
				request.getSession().setAttribute("submb", rs.getString("mb")+" MB");
				request.getSession().setAttribute("subsms", rs.getString("sms"));
				charge = Float.parseFloat(rs.getString("charge"));
				CostMin = Float.parseFloat(rs.getString("costmin"));
				CostSms = Float.parseFloat(rs.getString("costsms"));
				CostMB = Float.parseFloat(rs.getString("costmb"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    /**
     * Set the client's remaining program's attributes.
     * (middle-center row - 3 columns position)
     * @param request
     */
    public void set_Remaining_Program_SV(HttpServletRequest request) 
    {
    	if(minutes ==-1)
			request.getSession().setAttribute("minleft", "Απεριόριστα Λεπτά");
		else
			request.getSession().setAttribute("minleft", minutes);
    	
		request.getSession().setAttribute("mbleft", mb);
		request.getSession().setAttribute("smsleft", sms);
    }

    public void set_program_registration(Statement stmt , int id , int randomNum , PhoneNumber pn) 
    {
    	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM subs where sub_num=" + String.valueOf(randomNum) + "");
			while(rs.next()) 
			{
				SubscriptionName = rs.getString("subname");
				minutes = rs.getInt("minutes");
				mb = rs.getInt("mb");
				sms = rs.getInt("sms");
				
				CostMin = rs.getFloat("costmin");
				CostSms = rs.getFloat("costsms");
				CostMB = rs.getFloat("costmb");
			}
			pn.setPackageNumber(SubscriptionName);
			minutes = minutes ==-1 ? -1 : ThreadLocalRandom.current().nextInt(0, minutes);
			sms = ThreadLocalRandom.current().nextInt(0, sms);
			mb = ThreadLocalRandom.current().nextInt(0, mb);
			
			setCharge(minutes, sms, mb, CostMin, CostSms, CostMB);
			
			String s = "Insert into client_subs values("+String.valueOf(id)+",'" + SubscriptionName + "'," +
					minutes +"," + mb + "," + sms + ")";
			
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void change_subscription(Statement stmt , HttpServletRequest request , String new_sub , long phonenumber) 
    {
    	try {
    		
    		ResultSet rs = stmt.executeQuery("Select * from subs where subname='"+new_sub+"'");
    		while(rs.next()) {
    			SubscriptionName = rs.getString("subname");
    			minutes = rs.getInt("minutes");
    			sms = rs.getInt("sms");
    			mb = rs.getInt("mb");
    			CostMin = rs.getFloat("costmin");
    			CostSms = rs.getFloat("costsms");
    			CostMB = rs.getFloat("costmb");
    			charge = rs.getFloat("charge") - 0.45 * rs.getFloat("charge");
    		}
    		String mins = minutes==-1? "Απεριόριστα" : String.valueOf(minutes);
    		stmt.executeUpdate("update client_subs set subname='"+new_sub+"',minutes="+mins
    		+",mb="+String.valueOf(mb) + ",sms="+String.valueOf(sms) + " where client_id="+String.valueOf(client_id));
    		
    		stmt.executeUpdate("update client set program='"+new_sub+"',debt=0,bill="+String.valueOf(charge)+" where id="+
    				String.valueOf(client_id));
    		
    		stmt.executeUpdate("insert into bill values("+String.valueOf(phonenumber) +",'2020-7-29',"+
    							String.valueOf(charge)+")");
    		
    		request.getSession().setAttribute("infooo", "Επιτυχής αλλαγή προγράμματος.");
    		request.getSession().setAttribute("minleft", mins);
    		request.getSession().setAttribute("smsleft", sms);
    		request.getSession().setAttribute("mbleft", mb);
    		request.getSession().setAttribute("subname", SubscriptionName);
    		request.getSession().setAttribute("submins", mins);
    		request.getSession().setAttribute("subsms", sms);
    		request.getSession().setAttribute("submb", mb);
    		request.getSession().setAttribute("subcharge", charge);
    		request.getSession().setAttribute("currentBill", charge);
    		request.getSession().setAttribute("debtt", 0);
    	}
    	catch( Exception e) 
    	{
    		request.getSession().setAttribute("infooo", "Σφάλμα κατά την αλλαγή προγράμματος.");
    	}
    }
    
    public void refresh_balance(Statement stmt , HttpServletRequest request) 
    {
    	boolean flag = true;
    	int min = 0 , s = 0 , m=0;
    	try 
    	{
    		ResultSet rs = stmt.executeQuery("Select * from subs where subname='" + SubscriptionName + "'");
    		while(rs.next()) {
    			if(minutes==rs.getInt("minutes") && sms == rs.getInt("sms") && mb == rs.getInt("mb")) {
    				request.getSession().setAttribute("infooo", "Δεν υπάρχει λόγος ανανέωσης υπολοίπου.");
    				flag = false;
    			}
    			else {
    				min = rs.getInt("minutes");
    				s = rs.getInt("sms");
    				m = rs.getInt("mb");
    				minutes = min;
    				sms = s;
    				mb = m;
    			}
    		}
    		if (flag) {
    			stmt.executeUpdate("update client_subs set minutes="+String.valueOf(min)+",sms=" + String.valueOf(s)
				+",mb=" + String.valueOf(m)+" where client_id="+String.valueOf(client_id));
    			request.getSession().setAttribute("infooo", "Επιτυχής ανανέωση υπολοίπου.");
    		}
    	}
    	catch(Exception e) {e.printStackTrace();}
    	request.getSession().setAttribute("quick_access", "");
    	return;
    }

    public static void Gather_Subs(Statement stmt) {
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from subs");
    		List<String> l = new ArrayList<>();
    		while(rs.next()) {
    			l.add(rs.getString("subname"));
    			if(rs.getInt("minutes") == -1) l.add("Απεριόριστα");
    			else l.add(String.valueOf(rs.getInt("minutes")));
    			l.add(String.valueOf(rs.getInt("sms")));
    			l.add(String.valueOf(rs.getInt("mb")));
    			l.add(String.valueOf(rs.getDouble("charge")));
    			All_Subs.add(l);
    			l = new ArrayList<>();
    		}
    	}
    	catch(Exception e) {e.printStackTrace();}
    }
    
    public void setCharge(int min , int sms , int mb , float cmin , float csms , float cmb) {
    	if(min==-1) min = 0;
    	this.charge = cmin*min + csms*sms + cmb*mb;
    }
    
    public String getSubscriptionName() {
        return SubscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.SubscriptionName = subscriptionName;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMb() {
        return mb;
    }

    public void setMb(int mb) {
        this.mb = mb;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public float getCostMin() {
        return CostMin;
    }

    public void setCostMin(float costMin) {
        CostMin = costMin;
    }

    public float getCostMB() {
        return CostMB;
    }

    public void setCostMB(float costMB) {
        CostMB = costMB;
    }

    public float getCostSms() {
        return CostSms;
    }

    public void setCostSms(float costSms) {
        CostSms = costSms;
    }

    public double getCharge() {
        return charge;
    }

}
