//package Assignment1;

import java.util.*;

public class Book extends Item {

    protected String author;
    protected int year;

    //------------------------------------------------------------------------------------------------------------------------------------------
    public Book(String title, String auth, int year, int flag, int count, int cost) {
        if (flag == 0) {
            this.id = this.nextID++;
        } else {
            this.id = flag;
        }
        this.title = title;
        this.author = auth;
        this.year = year;
        this.type = 1;
        this.popularityCount = count;
        this.borrower = null;
        this.cost = cost;
        this.borrowersList = new ArrayList<Borrower>();
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void displayInfo() {
        System.out.println("ID: " + this.id + ", Title: '" + this.title + "' by " + this.author + "(" + this.year + ") " + this.popularityCount);
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public double calculateCost() {
        return (this.cost + (0.2 * this.cost) + 200);
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
        output += (this.author + ", ");
        output += (this.year + ", ");
        output += (this.popularityCount + ", ");
        output += (this.cost + "\n");
        return output;
    }
//------------------------------------------------------------------------------------------------------------------------------------------
}
