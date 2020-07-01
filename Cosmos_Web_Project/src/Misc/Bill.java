package Misc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Bill {

    private long phonenumber;
    public List<Call> CustomerCalls = new ArrayList<>();
    private double charge;//subscription's price
    private double previous_debt , current_bill; //current_bill = charge + previous_debt
    private String bill_date = "";

    /**
     * Bill constructor:
     * Take a phonenumber and produce for it five random calls.
     * Summarise total client debt : debt + program charge
     * @param pn
     */
    public Bill(long pn , double debt){
        this.phonenumber = pn;
        
        for(int i = 0;i <5; i++) {
        	Call c = new Call(phonenumber);
            CustomerCalls.add(c);
        }
        
        previous_debt = debt;
        
        bill_date = "2020-8-1";
    }
    
    public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public Bill(Statement stmt , long pn) 
    {
    	phonenumber = pn;
    	bill_date = "";
    	current_bill = 0;
    	charge=0;
    	previous_debt = 0;
    	
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from bill where phonenumber=" + String.valueOf(phonenumber));
    		
    		while(rs.next()) {
    			bill_date = rs.getString("billdate");
    			charge = rs.getDouble("price");
    		}
    		
    		rs = stmt.executeQuery("Select * from call where phonecaller1=" + String.valueOf(phonenumber));
    		while(rs.next()) {
    			Call c = new Call( rs.getLong("phonecaller1") , rs.getLong("phonereceiver") , rs.getString("calldate"));
    			CustomerCalls.add(c);
    		}
    	}
    	catch( Exception e) {e.printStackTrace(); }
    }
    
    public void set_bill_sv(HttpServletRequest request , String past) 
    {
    	request.getSession().setAttribute("currentBill", current_bill);
    	request.getSession().setAttribute("debtt", previous_debt);
    	request.getSession().setAttribute("subcharge", current_bill +" €");
    	if (past.equals("pay_charge") && current_bill==0.0 && previous_debt==0.0 ) request.getSession().setAttribute("infooo", "Έχετε πληρώσει όλες τις οφειλές σας.");
	}
    
    public void append(Statement stmt) 
    {
    	try {
    		stmt.executeUpdate("Insert into bill values(" + String.valueOf(phonenumber) + ",'" + 
    							bill_date +"'," + String.valueOf(charge) +")");
    		return;
    	}
    	catch(Exception e) { e.printStackTrace();}
    	return;
    }
    
    public void append_calls(Statement stmt) 
    {
    	try {
    		
    			for(int i = 0; i <5; i++) {
    				stmt.executeUpdate("Insert into call values(" + String.valueOf(phonenumber) + "," + 
							String.valueOf(CustomerCalls.get(i).getReceiver()) +",'" + 
    						CustomerCalls.get(i).getCall_date() +"')");
    			}
    		return;
    	}
    	catch(Exception e) { e.printStackTrace();}
    	return;
    }
    
    public void pay_bill(Statement stmt , HttpServletRequest request) {
    	
    	try {
    		stmt.executeUpdate("delete from bill where phonenumber=" + String.valueOf(phonenumber));
    		stmt.executeUpdate("update client set debt=0 , bill=0 where phonenumber="+ String.valueOf(phonenumber));
    		request.getSession().setAttribute("infooo", "Επιτυχής πληρωμή λογαριασμού.");
    		//set next bill
    		stmt.executeUpdate("Insert into bill values("+String.valueOf(phonenumber)+",'2020-8-1'," + String.valueOf(charge)+")");
    		previous_debt = 0;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		request.getSession().setAttribute("infooo", "Σφάλμα κατά την πληρωμή.");
    	}
    }
    
    public List<Call> getCustomerCalls() {
		return CustomerCalls;
	}

	public void setCustomerCalls(List<Call> customerCalls) {
		CustomerCalls = customerCalls;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getPrevious_debt() {
		return previous_debt;
	}

	public void setPrevious_debt(double previous_debt) {
		this.previous_debt = previous_debt;
	}

	public double getCurrent_bill() {
		return current_bill;
	}

	public void setCurrent_bill() {
		this.current_bill = previous_debt + charge;
	}
	
	public void setCurrent_bill(double b) {
		this.current_bill = b;
	}

	// Different methods to be used later on...
    public void Create_Bill(){}

    public void Bill_History(){}
}
