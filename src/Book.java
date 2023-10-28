import javax.swing.*;
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

    public void displayInfo(JFrame x, JPanel jp, String[] columnNames) {
//        String [][]data = new String[1][6];
//        data[0][0] = String.valueOf(this.id);
//        data[0][1] = this.title;
//        data[0][2] = this.author;
//        data[0][3] = String.valueOf(this.year);
//        data[0][4] = String.valueOf(this.popularityCount);
//        data[0][5] = String.valueOf(this.cost);
//        JTable book = new JTable(data, columnNames);
//        JScrollPane sp = new JScrollPane(book);
//        x.add(sp);
//        jp.add(book);
//        x.add(jp);
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
