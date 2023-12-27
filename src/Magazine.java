//package Assignment1;

import java.util.*;

public class Magazine extends Item {

    protected String publisherCompany;
    protected List<String> authors;

    //------------------------------------------------------------------------------------------------------------------------------------------
    public Magazine(String title, List<String> auths, String publisherCompany, int flag, int count, int cost) {
        if (flag == 0) {
            this.id = this.nextID++;
        } else {
            this.id = flag;
        }
        this.title = title;
        this.publisherCompany = publisherCompany;
        authors = new ArrayList<>();
        for (int i = 0; i < auths.size(); i++) {
            this.authors.add(auths.get(i));
        }
        this.type = 2;
        this.popularityCount = count;
        this.borrower = null;
        this.cost = cost;
        this.borrowersList = new ArrayList<Borrower>();
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void displayInfo() {
        System.out.print("ID: " + this.id + ", Title: '" + this.title + "' by ");
        for (int i = 0; i < this.authors.size(); i++) {
            System.out.print(this.authors.get(i));
            if (i != this.authors.size() - 1) {
                System.out.print(" , ");
            }
        }
        System.out.println("(" + this.publisherCompany + ") " + this.popularityCount);
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public double calculateCost() {
        return this.cost * this.popularityCount;
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
        for (int i = 0; i < this.authors.size(); i++) {
            output += (this.authors.get(i));
            if (i == this.authors.size() - 1) {
                output += "., ";
            } else {
                output += ", ";
            }
        }
        output += (this.publisherCompany + ", ");
        output += (this.popularityCount + ", ");
        output += (this.cost + "\n");
        return output;
    }
//------------------------------------------------------------------------------------------------------------------------------------------
}
