package Misc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Bill {

    private long phonenumber;
    private List<Call> CustomerCalls = new ArrayList<>();
    private double charge;
    private double previous_debt , current_bill;
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
        
        bill_date = "2020-7-1";
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
    	//put 0 to everything in case client has payed the bill
    	bill_date = "";
    	current_bill = 0;
    	charge=0;
    	previous_debt = 0;
    	
    	try {
    		ResultSet rs = stmt.executeQuery("Select * from bill where phonenumber=" + String.valueOf(phonenumber));
    		
    		while(rs.next()) {
    			bill_date = rs.getString("billdate");
    			current_bill = rs.getDouble("price");
    		}
    		
    		rs = stmt.executeQuery("Select * from call where phonecaller1=" + String.valueOf(phonenumber));
    		while(rs.next()) {
    			Call c = new Call( rs.getLong("phonecaller1") , rs.getLong("phonereceiver") , rs.getString("calldate"));
    			CustomerCalls.add(c);
    		}
    	}
    	catch( Exception e) {e.printStackTrace(); }
    }
    
    public void set_bill_sv(HttpServletRequest request) 
    {
    	request.getSession().setAttribute("currentBill", current_bill);
    	request.getSession().setAttribute("debtt", previous_debt);
    	request.getSession().setAttribute("subcharge", current_bill +" €");
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
    		System.out.println(phonenumber);
    		stmt.executeUpdate("delete from bill where phonenumber=" + String.valueOf(phonenumber));
    		stmt.executeUpdate("update client set debt=0 , bill=0 where phonenumber="+ String.valueOf(phonenumber));
    		request.getSession().setAttribute("infooo", "Επιτυχής πληρωμή λογαριασμού.");
    		previous_debt = 0;
    		charge = 0;
    		current_bill = 0;
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

	public void setCurrent_bill(double current_bill) {
		this.current_bill = current_bill;
	}

	// Different methods to be used later on...
    public void Create_Bill(){}

    public void Bill_History(){}
}
