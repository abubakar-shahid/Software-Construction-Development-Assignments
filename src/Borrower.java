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

//    public void displayInfo() {
//        System.out.print(", Borrowed by: " + fullName);
//        System.out.println(" (" + phoneNumber + ")");
//    }
//------------------------------------------------------------------------------------------------------------------------------------------

//    public boolean checkBorrower(Borrower b) {
//        return (b.fullName == null ? this.fullName == null : b.fullName.equals(this.fullName))
//                && (b.phoneNumber == null ? this.phoneNumber == null : b.phoneNumber.equals(this.phoneNumber))
//                && b.totalBill == this.totalBill;
//    }
//------------------------------------------------------------------------------------------------------------------------------------------
}
