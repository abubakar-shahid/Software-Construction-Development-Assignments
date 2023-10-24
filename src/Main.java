import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
//------------------------------------------------------------------------------------------------------------------------------------------

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
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void readFile(Library library) {
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
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void writeFile(Library library) {

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
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Library library = new Library();
        readFile(library);
        Scanner input = new Scanner(System.in);
        int choice = 0;

        System.out.println("Library Management System Menu:-");
        System.out.println("1. Hot Picks!");
        System.out.println("2. Borrow Book");
        System.out.println("3. Add New Book");
        System.out.println("4. Edit Book");
        System.out.println("5. Delete Book");
        System.out.println("6. View All Books");
        System.out.println("7. View Book by ID");
        System.out.println("8. View Borrowers List");
        System.out.println("9. Exit");

        OUTER:
        while (true) {
            System.out.println();
            System.out.print("Enter your Choice: ");
            choice = input.nextInt();
            System.out.println();
            switch (choice) {
                case 1 ->
                    library.viewHotPicks();
                case 2 ->
                    library.borrowItem();
                case 3 ->
                    library.addItem();
                case 4 ->
                    library.editItem();
                case 5 ->
                    library.deleteItem();
                case 6 ->
                    library.viewAllItems();
                case 7 ->
                    library.viewItemByID();
                case 8 ->
                    library.viewBorrowersList();
                case 9 -> {
                    writeFile(library);
                    System.out.println("\n---------- Good Bye !!! ----------");
                    break OUTER;
                }
                default ->
                    System.out.println("Incorrect Entry!!!");
            }
        }
    }
}
//------------------------------------------------------------------------------------------------------------------------------------------
