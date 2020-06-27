package Misc;

import java.util.concurrent.ThreadLocalRandom;

public class Call {

	private long caller , receiver;
	private String call_date;
	
    /**
     * 
     * @param clientName
     */
    public Call(long phonenumber)
    {
        caller = phonenumber;
        
        //create random receiver
        String tmp = "69";
        for(int i = 0; i <8; i++) {
        	tmp += ThreadLocalRandom.current().nextInt(0, 10);
        }
        receiver = Long.parseLong(tmp);
        
        //create random call date
        call_date = "2020-";
        call_date += ThreadLocalRandom.current().nextInt(1, 13) + "-";
        call_date += ThreadLocalRandom.current().nextInt(1, 25);
    }
    
    /**
     * constructor for login - take all data from db
     * @return
     */
    public Call(long pc , long pr , String date) {
    	caller = pc;
    	receiver = pr;
    	call_date = date;
    }

    public long getCaller() {
		return caller;
	}

	public void setCaller(long caller) {
		this.caller = caller;
	}

	public long getReceiver() {
		return receiver;
	}

	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}

	public String getCall_date() {
		return call_date;
	}

	public void setCall_date(String call_date) {
		this.call_date = call_date;
	}

	public void add_Call(long phonenumber){
        
    }
}
