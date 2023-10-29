public class Borrower {

    protected String fullName;
    protected String phoneNumber;
    protected double totalBill;

    //------------------------------------------------------------------------------------------------------------------------------------------
    public Borrower(String name, String number, double bill) {
        this.fullName = name;
        this.phoneNumber = number;
        this.totalBill = bill;
    }
//------------------------------------------------------------------------------------------------------------------------------------------
}