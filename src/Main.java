//package Assignment1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    //------------------------------------------------------------------------------------------------------------------------------------------
    public static void passBookNewspaperData(String data, Library library) {
        data = data + ".";
        String st = "";
        char ch;
        ArrayList<String> arrayList = new ArrayList<>();
        //System.out.println(data);
        for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);
            if (ch == ',' || ch == '.' && st != "") {
                arrayList.add(st);
                st = "";
            } else {
                if (ch != ' ') {
                    st += ch;
                }
            }
        }
        if (arrayList.get(0).charAt(0) == '1') {
            int year = Integer.parseInt(arrayList.get(3)), count = Integer.parseInt(arrayList.get(4));
            int cost = Integer.parseInt(arrayList.get(5));
            Book book = new Book(arrayList.get(1), arrayList.get(2), year, 0, count, cost);
            library.items.add(book);
            //book.displayInfo();
        } else {
            int count = Integer.parseInt(arrayList.get(3));
            Newspaper np = new Newspaper(arrayList.get(1), arrayList.get(2), arrayList.get(4), 0, count);
            library.items.add(np);
            //np.displayInfo();
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void passMagazineData(String data, Library library) {
        data = data + ".";
        String st = "";
        char ch;
        int comma = 0, fstop = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> auths = new ArrayList<>();
        //System.out.println(data);
        for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);
            if (ch == ',' || ch == '.' && st != "") {
                if (ch == ',') {
                    comma++;
                } else if (ch == '.') {
                    fstop++;
                }
                if (comma > 2 && fstop <= 1) {
                    auths.add(st);
                    st = "";
                    if (fstop == 1) {
                        fstop++;
                    }
                } else {
                    arrayList.add(st);
                    st = "";
                }
            } else {
                if (ch != ' ') {
                    st += ch;
                }
            }
        }
        int count = Integer.parseInt(arrayList.get(4)), cost = Integer.parseInt(arrayList.get(5));
        Magazine mg = new Magazine(arrayList.get(1), auths, arrayList.get(3), 0, count, cost);
        library.items.add(mg);
        //mg.displayInfo();
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void readFile(Library library) {
        String filePath = "src\\data.txt";

        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                if (line.charAt(0) == '2') {
                    passMagazineData(line, library);
                } else {
                    passBookNewspaperData(line, library);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Loaded Books from file data.txt");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void writeFile(Library library) {

        try {
            FileWriter fWriter = new FileWriter("src\\data" + ".txt");
            for (int i = 0; i < library.items.size(); i++) {
                int type = library.items.get(i).type;
                fWriter.write(library.items.get(i).getData());
            }
            fWriter.close();
            System.out.println("File is created successfully with the content.");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Library library = new Library();
        readFile(library);
        Scanner input = new Scanner(System.in);
        int choice = 0;

        System.out.println("Library Management System Menu:-");
        System.out.println("1. Hot Picks!");
        System.out.println("2. Borrow an Item");
        System.out.println("3. Add an Item");
        System.out.println("4. Edit an Item");
        System.out.println("5. Delete an Item");
        System.out.println("6. View All Items");
        System.out.println("7. View Items by ID");
        System.out.println("8. View Borrowers List");
        System.out.println("9. Exit");

        while (true) {
            System.out.println();
            System.out.print("Enter your Choice: ");
            choice = input.nextInt();
            System.out.println();
            if (choice == 1) {
                library.viewHotPicks();
            } else if (choice == 2) {
                library.borrowItem();
            } else if (choice == 3) {
                library.addItem();
            } else if (choice == 4) {
                library.editItem();
            } else if (choice == 5) {
                library.deleteItem();
            } else if (choice == 6) {
                library.viewAllItems();
            } else if (choice == 7) {
                library.viewItemByID();
            } else if (choice == 8) {
                library.viewBorrowersList();
            } else if (choice == 9) {
                writeFile(library);
                System.out.println("\n---------- Good Bye !!! ----------");
                break;
            } else {
                System.out.println("Incorrect Entry!!!");
            }
        }
    }
}
//------------------------------------------------------------------------------------------------------------------------------------------
