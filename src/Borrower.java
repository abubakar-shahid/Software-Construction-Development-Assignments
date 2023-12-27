//package Assignment1;

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

    public void displayInfo() {
        System.out.print(", Borrowed by: " + fullName);
        System.out.println(" (" + phoneNumber + ")");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public boolean checkBorrower(Borrower b) {
        if (b.fullName == this.fullName && b.phoneNumber == this.phoneNumber && b.totalBill == this.totalBill) {
            return true;
        }
        return false;
    }
//------------------------------------------------------------------------------------------------------------------------------------------
}
