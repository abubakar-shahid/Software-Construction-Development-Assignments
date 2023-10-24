import java.util.*;

public class Book {

    protected static int nextID = 1;
    protected int id;
    protected String title;
    protected int type;
    protected Borrower borrower;
    protected int popularityCount;
    protected int cost;
    protected List<Borrower> borrowersList;
    protected String author;
    protected int year;

    //------------------------------------------------------------------------------------------------------------------------------------------
    public Book(String title, String auth, int year, int flag, int count, int cost) {
        if (flag == 0) {
            this.id = Book.nextID++;
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
        this.borrowersList = new ArrayList<>();
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void displayInfo() {
        System.out.println("ID: " + this.id + ", Title: '" + this.title + "' by " + this.author + "(" + this.year + ") " + this.popularityCount);
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public double calculateCost() {
        return (this.cost + (0.2 * this.cost) + 200);
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public boolean checkBorrower(Borrower b) {
        boolean flag = true;
        for (Iterator<Borrower> it = this.borrowersList.iterator(); it.hasNext();) {
            if (this.borrowersList.get(id).checkBorrower(b)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
//------------------------------------------------------------------------------------------------------------------------------------------

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
