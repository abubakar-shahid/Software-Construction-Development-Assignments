//package Assignment1;

import java.util.*;

public class Newspaper extends Item {

    protected String publisherCompany;
    protected String date;

    //------------------------------------------------------------------------------------------------------------------------------------------
    public Newspaper(String title, String company, String date, int flag, int count) {
        if (flag == 0) {
            this.id = this.nextID++;
        } else {
            this.id = flag;
        }
        this.title = title;
        this.publisherCompany = company;
        this.date = date;
        this.type = 3;
        this.popularityCount = count;
        this.borrower = null;
        this.cost = 0;
        this.borrowersList = new ArrayList<Borrower>();
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void displayInfo() {
        System.out.println("ID: " + this.id + ", Title: '" + this.title + "' by " + this.publisherCompany + "(" + this.date + ")" + this.popularityCount);
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public double calculateCost() {
        return 15;
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean checkBorrower(Borrower b) {
        boolean flag = true;
        for (int i = 0; i < this.borrowersList.size(); i++) {
            if (this.borrowersList.get(id).checkBorrower(b)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        return false;
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public String getData() {
        String output = "";
        output += (this.type + ", ");
        output += (this.title + ", ");
        output += (this.publisherCompany + ", ");
        output += (this.popularityCount + ", ");
        output += (this.date + "\n");
        return output;
    }
//------------------------------------------------------------------------------------------------------------------------------------------
}
