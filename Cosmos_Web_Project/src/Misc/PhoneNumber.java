package Misc;

public class PhoneNumber {

    private long phoneNum;
    private String PackageNumber;
    private String date;

    // Constructor.
    public PhoneNumber(long phoneNum , String PackageNumber){
        this.phoneNum = phoneNum;
        this.PackageNumber = PackageNumber;
    }

    // Different methods to be used later on...

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPackageNumber() {
        return PackageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        PackageNumber = packageNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
