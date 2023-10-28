import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class GUI extends JFrame implements ActionListener {
    Library library = new Library();
    JLabel title;
    JButton op1;
    JButton op2;
    JButton op3;
    JButton op4;
    JButton op5;
    JButton op6;
    JButton op7;
    JButton op8;
    JButton op9;

    public void createGUI() {
        //adding header
        title = new JLabel();
        title.setText("*********** Welcome to The Library Management System ***********");

        //adding body
        JPanel body = new JPanel();
        body.setLayout(new GridLayout(10, 1));
        body.add(title);
        op1 = new JButton("Hot Picks!");
        op1.addActionListener(this);
        op2 = new JButton("Borrow Book");
        op2.addActionListener(this);
        op3 = new JButton("View Book by ID");
        op3.addActionListener(this);
        op4 = new JButton("View All Books");
        op4.addActionListener(this);
        op5 = new JButton("Add New Book");
        op5.addActionListener(this);
        op6 = new JButton("Edit Book");
        op6.addActionListener(this);
        op7 = new JButton("Delete Book");
        op7.addActionListener(this);
        op8 = new JButton("View Borrower's List");
        op8.addActionListener(this);
        op9 = new JButton("Exit");
        op9.addActionListener(this);
        body.add(op1);
        body.add(op2);
        body.add(op3);
        body.add(op4);
        body.add(op5);
        body.add(op6);
        body.add(op7);
        body.add(op8);
        body.add(op9);
        add(body);

        //finishing
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //data handling
        readFile(library);
        //writeFile(library);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        switch (option) {
            case "Hot Picks!" -> this.library.viewHotPicks();
            case "Borrow Book" -> this.library.borrowItem();
            case "View Book by ID" -> this.library.viewItemByID();
            case "View All Books" -> this.library.viewAllItems();
            case "Add New Book" -> this.library.addItem();
            case "Edit Book" -> this.library.editItem();
            case "Delete Book" -> this.library.deleteItem();
            case "View Borrower's List" -> this.library.viewBorrowersList();
            default -> this.writeFile(library);
        }
    }

    //---------------------------------------------------------------------------------------------------------
    public static void passBookData(String data, Library library) {
        data = data + ".";
        String st = "";
        char ch;
        ArrayList<String> arrayList = new ArrayList<>();
        //System.out.println(data);
        for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);
            if (ch == ',' || ch == '.' && !"".equals(st)) {
                arrayList.add(st);
                st = "";
            } else {
                if (ch != ' ') {
                    st += ch;
                }
            }
        }

        int year = Integer.parseInt(arrayList.get(3)), count = Integer.parseInt(arrayList.get(4));
        int cost = Integer.parseInt(arrayList.get(5));
        Book book = new Book(arrayList.get(1), arrayList.get(2), year, 0, count, cost);
        library.items.add(book);
    }

    public void readFile(Library library) {
        String filePath = "D:\\FAST-NUCES l215845\\5th Semester\\Software Construction & Development\\Assignments\\Assignment-3\\data.txt";

        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    //System.out.println(line);
                    passBookData(line, library);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Loaded Books from file data.txt");
    }

    public void writeFile(Library library) {

        try {
            try (FileWriter fWriter = new FileWriter("D:\\FAST-NUCES l215845\\5th Semester\\Software Construction & Development\\Assignments\\Assignment-3\\data.txt")) {
                for (int i = 0; i < library.items.size(); i++) {
                    fWriter.write(library.items.get(i).getData());
                }
            }
            System.out.println("File is created successfully with the content.");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        exit(0);
    }
}