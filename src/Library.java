import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Library implements ActionListener {

    public List<Book> items;
    String[] columnNames;
    JFrame x;
    JPanel jp;
    JLabel jl;
    JButton jb;
    JTextField jtf;
    JTable table;
    DefaultTableModel model;
    JScrollPane sp;

    Scanner input = new Scanner(System.in);
//------------------------------------------------------------------------------------------------------------------------------------------

    public Library() {
        this.items = new ArrayList<>();
        columnNames = new String[]{"ID", "Title", "Author", "Year of Publication", "Popularity Count", "Cost"};
        x = null;
        jp = null;
        jl = null;
        jb = null;
        jtf = null;
        table = null;
        model = null;
        sp = null;
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewHotPicks() {
        int size = this.items.size();
        int[] indexes = new int[size];
        int[] counts = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = this.items.get(i).id - 1;
            counts[i] = this.items.get(i).popularityCount;
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int temp1, temp2;
                if (counts[j] < counts[i]) {
                    temp1 = indexes[i];
                    temp2 = counts[i];
                    indexes[i] = indexes[j];
                    counts[i] = counts[j];
                    indexes[j] = temp1;
                    counts[j] = temp2;
                }
            }
        }
        x = new JFrame("Hot Picks!");
        x.setVisible(true);
        x.setSize(500, 400);
        x.setLayout(new GridLayout(2, 1));
        jp = new JPanel(new FlowLayout());
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        sp = new JScrollPane(table);
        jp.add(sp);
        x.add(jp);
        int rows = this.items.size();
        for (int i = 0, j = 0; i < rows; i++) {
            Object[] data = {
                    String.valueOf(this.items.get(indexes[i]).id),
                    this.items.get(indexes[i]).title,
                    this.items.get(indexes[i]).author,
                    String.valueOf(this.items.get(indexes[i]).year),
                    String.valueOf(this.items.get(indexes[i]).popularityCount),
                    String.valueOf(this.items.get(indexes[i]).cost)
            };
            model.addRow(data);
        }
        jb = new JButton("View Chart");
        jb.addActionListener(this::actionPerformed);
        JPanel jp2 = new JPanel(new FlowLayout());
        jp2.add(jb);
        x.add(jp2);
    }

    public void viewGraph() {
        JOptionPane.showMessageDialog(x, "Will be Printing Chart Soon :)");
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    JTextField text1 = new JTextField(5);
    JTextField text2 = new JTextField(20);
    JTextField text3 = new JTextField(15);

    public void borrowItem() {
        x = new JFrame();
        x.setVisible(true);
        x.setSize(500, 400);
        x.setLayout(new GridLayout(2, 1));
        jp = new JPanel(new FlowLayout());
        jl = new JLabel("          Following Books are currently available: ");
        jp.add(jl);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        sp = new JScrollPane(table);
        jp.add(sp);
        x.add(jp);
        int rows = this.items.size();
        for (int i = 0, j = 0; i < rows; i++) {
            if (this.items.get(i).borrower == null) {
                Object[] data = {
                        String.valueOf(this.items.get(i).id),
                        this.items.get(i).title,
                        this.items.get(i).author,
                        String.valueOf(this.items.get(i).year),
                        String.valueOf(this.items.get(i).popularityCount),
                        String.valueOf(this.items.get(i).cost)
                };
                model.addRow(data);
            }
        }
        JLabel l1 = new JLabel("Enter Book ID: ");
        JLabel l2 = new JLabel("Your Full Name: ");
        JLabel l3 = new JLabel("Phone Number: ");
        JButton enter = new JButton("Borrow");
        enter.addActionListener(this::actionPerformed);
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p4 = new JPanel(new FlowLayout());
        p1.add(l1);
        p1.add(text1);
        p2.add(l2);
        p2.add(text2);
        p3.add(l3);
        p3.add(text3);
        p4.add(enter);
        JPanel jp2 = new JPanel(new GridLayout(4, 1));
        jp2.add(p1);
        jp2.add(p2);
        jp2.add(p3);
        jp2.add(p4);
        x.add(jp2);
    }

    public void borrow() {
        boolean flag = false;
        int index = 0;
        int id = Integer.parseInt(text1.getText());
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            if (this.items.get(index).borrower == null) {
                String name = text2.getText();
                String number = text3.getText();
                double totalCost = this.items.get(index).calculateCost();
                Borrower b1 = new Borrower(name, number, totalCost);
                this.items.get(index).borrower = b1;
                JOptionPane.showMessageDialog(x, "Book Borrowed Successfully!\n" +
                        "Your Total Cost is: Rs. " + totalCost + "/-");
                text1.setText("");
                text2.setText("");
                text3.setText("");
                x.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(x, "This Book is Already Borrowed!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(x, "Book NOT Found!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    JTextField t1 = new JTextField(20);
    JTextField t2 = new JTextField(20);
    JTextField t3 = new JTextField(5);
    JTextField t4 = new JTextField(5);
    JTextField t5 = new JTextField(5);

    public void addItem() {
        x = new JFrame();
        x.setSize(400, 400);
        x.setVisible(true);
        jp = new JPanel();
        jp.setLayout(new GridLayout(6, 1));
        JLabel l1 = new JLabel("Title: ");
        JLabel l2 = new JLabel("Author: ");
        JLabel l3 = new JLabel("Year of Publication: ");
        JLabel l4 = new JLabel("Popularity Count: ");
        JLabel l5 = new JLabel("Cost: ");
        JTextField t1 = new JTextField(20);
        JTextField t2 = new JTextField(20);
        JTextField t3 = new JTextField(10);
        JTextField t4 = new JTextField(10);
        JTextField t5 = new JTextField(10);
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(l1);
        p1.add(t1);
        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(l2);
        p2.add(t2);
        JPanel p3 = new JPanel(new FlowLayout());
        p3.add(l3);
        p3.add(t3);
        JPanel p4 = new JPanel(new FlowLayout());
        p4.add(l4);
        p4.add(t4);
        JPanel p5 = new JPanel(new FlowLayout());
        p5.add(l5);
        p5.add(t5);
        JPanel p6 = new JPanel(new FlowLayout());
        JButton enter = new JButton("Add Item");
        enter.addActionListener(this::actionPerformed);
        p6.add(enter);
        jp.add(p1);
        jp.add(p2);
        jp.add(p3);
        jp.add(p4);
        jp.add(p5);
        jp.add(p6);
        x.add(jp);
    }

    public void add() {
        String title = t1.getText();
        String author = t2.getText();
        String st1 = t3.getText();
        String st2 = t4.getText();
        String st3 = t5.getText();
        System.out.println(st1 + "," + st2 + "," + st3);
//        int year = Integer.parseInt(st1);
//        int count = Integer.parseInt(st2);
//        int cost = Integer.parseInt(st3);
//        Book book = new Book(title, author, year, 0, count, cost);
//        this.items.add(book);
        JOptionPane.showMessageDialog(x, "Book Added Successfully!");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void editItem() {
//        int index = 0, id;
//        boolean flag = false;
//        System.out.print("Enter Book ID: ");
//        id = input.nextInt();
//        for (int i = 0; i < this.items.size(); i++) {
//            if (this.items.get(i).id == id) {
//                index = i;
//                id = this.items.get(index).id;
//                flag = true;
//                break;
//            }
//        }
//        if (flag && this.items.get(index).type == 1) {
//            System.out.println("type: " + this.items.get(index).type);
//            input.nextLine();
//            System.out.println("Book Found !     Enter new Details:-");
//            String title, author;
//            int year, count, cost;
//            System.out.print("Enter Title of the Book: ");
//            title = input.nextLine();
//            System.out.print("Enter Author of the Book: ");
//            author = input.nextLine();
//            System.out.print("Enter Year of Publication: ");
//            year = input.nextInt();
//            System.out.print("Enter Popularity Count: ");
//            count = input.nextInt();
//            System.out.print("Enter Cost of the Book: ");
//            cost = input.nextInt();
//
//            Book book = new Book(title, author, year, id, count, cost);
//            this.items.remove(index);
//            this.items.add(index, book);
//            System.out.println("Book Edited Successfully");
//        } else {
//            System.out.println("Item NOT Found !!!");
//        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void deleteItem() {
        x = new JFrame();
        x.setVisible(true);
        x.setSize(500, 400);
        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jl = new JLabel("Enter Book ID: ");
        jtf = new JTextField(10);
        jb = new JButton("Delete Item");
        jb.addActionListener(this);
        jp.add(jl);
        jp.add(jtf);
        jp.add(jb);
        x.add(jp);
    }

    public void delete() {
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == Integer.parseInt(jtf.getText())) {
                this.items.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(x, "Book Deleted Successfully!");
        } else {
            JOptionPane.showMessageDialog(x, "Book NOT Found!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewAllItems() {
        x = new JFrame();
        x.setVisible(true);
        x.setSize(400, 400);
        int rows = this.items.size(), cols = 6;
        String[][] data = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            data[i][0] = String.valueOf(this.items.get(i).id);
            data[i][1] = this.items.get(i).title;
            data[i][2] = this.items.get(i).author;
            data[i][3] = String.valueOf(this.items.get(i).year);
            data[i][4] = String.valueOf(this.items.get(i).popularityCount);
            data[i][5] = String.valueOf(this.items.get(i).cost);
        }
        JTable allBooks = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(allBooks);
        x.add(sp);
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewItemByID() {
        x = new JFrame();
        x.setVisible(true);
        x.setSize(500, 400);
        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jl = new JLabel("Enter Book ID: ");
        jtf = new JTextField(10);
        jb = new JButton("Show Item");
        jb.addActionListener(this);
        jp.add(jl);
        jp.add(jtf);
        jp.add(jb);
        x.add(jp);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        sp = new JScrollPane(table);
        x.setLayout(new FlowLayout());
        x.add(sp);
    }

    public void view() {
        if (table.getRowCount() != 0) {
            model.removeRow(0);
        }
        int index = 0, id = Integer.parseInt(jtf.getText());
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            String[] data = {
                    String.valueOf(this.items.get(index).id),
                    this.items.get(index).title,
                    this.items.get(index).author,
                    String.valueOf(this.items.get(index).year),
                    String.valueOf(this.items.get(index).popularityCount),
                    String.valueOf(this.items.get(index).cost)
            };
            model.addRow(data);
        } else {
            JOptionPane.showMessageDialog(x, "Book NOT Found!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    public void viewBorrowersList() {
        boolean flag = false;
        x = new JFrame();
        x.setVisible(true);
        x.setSize(400, 400);
        int rows = this.items.size(), cols = 4;
        String[][] data = new String[rows][cols];
        for (int i = 0, j = 0; i < this.items.size(); i++) {
            if (this.items.get(i).borrower != null) {
                data[j][0] = String.valueOf(this.items.get(i).id);
                data[j][1] = this.items.get(i).title;
                data[j][2] = this.items.get(i).borrower.fullName;
                data[j][3] = this.items.get(i).borrower.phoneNumber;
                j++;
                flag = true;
            }
        }
        String[] headings = {"Book ID", "Book Title", "Borrower Name", "Borrower Contact"};
        JTable allBorrowers = new JTable(data, headings);
        JScrollPane sp = new JScrollPane(allBorrowers);
        x.add(sp);
        if (!flag) {
            JOptionPane.showMessageDialog(x, "Borrower List Empty!");
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Show Item" -> view();
            case "Add Item" -> add();
            case "Delete Item" -> delete();
            case "Borrow" -> borrow();
            case "View Chart" -> viewGraph();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
}
