//package Assignment1;

import java.util.*;

public class Item implements Configuration {

    protected static int nextID = 1;
    protected int id;
    protected String title;
    protected int type;
    protected Borrower borrower;
    protected int popularityCount;
    protected int cost;
    protected List<Borrower> borrowersList;

    @Override
    public void displayInfo() {
    }

    @Override
    public double calculateCost() {
        return 1;
    }

    @Override
    public boolean checkBorrower(Borrower b) {
        return true;
    }

    @Override
    public String getData() {
        return " ";
    }

}
