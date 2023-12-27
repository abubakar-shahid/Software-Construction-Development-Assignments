//package Assignment1;

import java.util.*;

public class Library {

    public List<Item> items;

    Scanner input = new Scanner(System.in);
//------------------------------------------------------------------------------------------------------------------------------------------

    Library() {
        this.items = new ArrayList<Item>();
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

        for (int i = 0; i < size; i++) {
            this.items.get(indexes[i]).displayInfo();
        }

    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void borrowItem() {
        System.out.println("Following Items currently are available:-");
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).borrower == null) {
                this.items.get(i).displayInfo();
            }
        }
        int index = 0, id;
        boolean flag = false;
        System.out.print("Enter Item ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            if (this.items.get(index).borrower == null) {
                input.nextLine();
                String name, number;
                System.out.print("Enter your Full Name: ");
                name = input.nextLine();
                System.out.print("Enter your Phone Number: ");
                number = input.nextLine();
                int type = this.items.get(index).type;
                double totalCost = this.items.get(index).calculateCost();
                Borrower b1 = new Borrower(name, number, totalCost);
                if (this.items.get(index).checkBorrower(b1)) {
                    System.out.println("You have already Borrowed this item previously !!! You cannot borrow it again !!!");
                } else {
                    this.items.get(index).borrower = b1;
                    this.items.get(index).borrowersList.add(b1);
                    System.out.println("Item Borrowed Successfully !!!");
                    System.out.println("Your Total Cost is: Rs." + totalCost + "/-");
                }
            } else {
                System.out.println("This item is already borrowed !!!");
            }

        } else {
            System.out.println("Item NOT Found !!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void addItem() {
        int choice;
        System.out.println("1. Add Book");
        System.out.println("2. Add Magazine");
        System.out.println("3. Add Newspaper");
        System.out.print("Enter Choice: ");
        choice = input.nextInt();
        if (choice == 1) {
            addBook();
        } else if (choice == 2) {
            addMagazine();
        } else if (choice == 3) {
            addNewspaper();
        } else {
            System.out.println("Incorrect Entry!!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void editItem() {
        int choice;
        System.out.println("1. Edit Book");
        System.out.println("2. Edit Magazine");
        System.out.println("3. Edit Newspaper");
        System.out.print("Enter Choice: ");
        choice = input.nextInt();
        if (choice == 1) {
            editBook();
        } else if (choice == 2) {
            //editMagazine();
        } else if (choice == 3) {
            //editNewspaper();
        } else {
            System.out.println("Incorrect Entry!!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void deleteItem() {
        int id;
        boolean flag = false;
        System.out.print("Enter the Item ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                this.items.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("Item Deleted Successfully");
        } else {
            System.out.println("Item NOT Found !!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewAllItems() {
        for (int i = 0; i < this.items.size(); i++) {
            this.items.get(i).displayInfo();
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewItemByID() {
        int index = 0, id;
        boolean flag = false;
        System.out.print("Enter Item ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            this.items.get(index).displayInfo();
        } else {
            System.out.println("Item NOT Found !!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewBorrowersList() {
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).borrower != null) {
                flag = true;
                System.out.print(this.items.get(i).title);
                this.items.get(i).borrower.displayInfo();
            }
        }
        if (!flag) {
            System.out.println("Borrowers List Empty !");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------ Some Helping Functions ------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------

    private void addBook() {
        input.nextLine();
        String title = "", author = "";
        int year, count, cost;

        System.out.print("Enter Title of the Book: ");
        title = input.nextLine();
        System.out.print("Enter Author of the Book: ");
        author = input.nextLine();
        System.out.print("Enter Year of Publication: ");
        year = input.nextInt();
        System.out.print("Enter Popularity Count: ");
        count = input.nextInt();
        System.out.print("Enter Cost of the Book: ");
        cost = input.nextInt();

        Book book = new Book(title, author, year, 0, count, cost);
        this.items.add(book);
        System.out.println("Book Added Successfully");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    private void addMagazine() {
        input.nextLine();
        String company, title, temp;
        int num, count, cost;
        List<String> auths = new ArrayList<>();

        System.out.print("Enter Title of the Magazine: ");
        temp = input.nextLine();
        title = temp;
        System.out.print("Enter Publisher Company of the Magazine: ");
        company = input.nextLine();
        System.out.print("Enter Numer of authors of the Magazine: ");
        num = input.nextInt();
        input.nextLine();
        System.out.println("Enter Names of authors:-");
        for (int i = 0; i < num; i++) {
            System.out.print("Author-" + (i + 1) + " : ");
            temp = input.nextLine();
            auths.add(temp);
        }
        System.out.print("Enter Popularity Count: ");
        count = input.nextInt();
        System.out.print("Enter Cost of the Magazine: ");
        cost = input.nextInt();

        Magazine magazine = new Magazine(title, auths, company, 0, count, cost);
        this.items.add(magazine);
        System.out.println("Magazine Added Successfully");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    private void addNewspaper() {
        input.nextLine();
        String company, title, date;
        int count;

        System.out.print("Enter Title of the Newspaper: ");
        title = input.nextLine();
        System.out.print("Enter Publisher Company of the Newspaper: ");
        company = input.nextLine();
        System.out.print("Enter Date of Publish: ");
        date = input.nextLine();
        System.out.print("Enter Popularity Count: ");
        count = input.nextInt();

        Newspaper np = new Newspaper(title, company, date, 0, count);
        this.items.add(np);
        System.out.println("Newspaper Added Successfully");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void editBook() {
        int index = 0, id;
        boolean flag = false;
        System.out.print("Enter Book ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                id = this.items.get(index).id;
                flag = true;
                break;
            }
        }
        if (flag && this.items.get(index).type == 1) {
            System.out.println("type: " + this.items.get(index).type);
            input.nextLine();
            System.out.println("Book Found !     Enter new Details:-");
            String title, author;
            int year, count, cost;
            System.out.print("Enter Title of the Book: ");
            title = input.nextLine();
            System.out.print("Enter Author of the Book: ");
            author = input.nextLine();
            System.out.print("Enter Year of Publication: ");
            year = input.nextInt();
            System.out.print("Enter Popularity Count: ");
            count = input.nextInt();
            System.out.print("Enter Cost of the Book: ");
            cost = input.nextInt();

            Book book = new Book(title, author, year, id, count, cost);
            this.items.remove(index);
            this.items.add(index, book);
            System.out.println("Book Edited Successfully");
        } else {
            System.out.println("Item NOT Found !!!");
        }

    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void editMagazine() {
        int index = 0, id;
        boolean flag = false;
        System.out.print("Enter Magazine ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                id = this.items.get(index).id;
                flag = true;
                break;
            }
        }
        if (flag && this.items.get(index).type == 2) {
            System.out.println("type: " + this.items.get(index).type);
            input.nextLine();
            System.out.println("Magazine Found !     Enter new Details:-");
            String company, title, temp;
            int num, count, cost;
            List<String> auths = new ArrayList<>();

            System.out.print("Enter Title of the Magazine: ");
            title = input.nextLine();
            System.out.print("Enter Publisher Company of the Magazine: ");
            company = input.nextLine();
            System.out.print("Enter Numer of authors of the Magazine: ");
            num = input.nextInt();
            System.out.print("Enter Names of authors: ");
            for (int i = 0; i < num; i++) {
                temp = input.nextLine();
                auths.add(temp);
            }
            System.out.print("Enter Popularity Count: ");
            count = input.nextInt();
            System.out.print("Enter Cost of the Magazine: ");
            cost = input.nextInt();

            Magazine magazine = new Magazine(title, auths, company, 0, count, cost);
            this.items.remove(index);
            this.items.add(index, magazine);
            System.out.println("Magazine Edited Successfully");
        } else {
            System.out.println("Item NOT Found !!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void editNewspaper() {
        int index = 0, id, count;
        boolean flag = false;
        System.out.print("Enter Newspaper ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                id = this.items.get(index).id;
                flag = true;
                break;
            }
        }
        if (flag && this.items.get(index).type == 3) {
            System.out.println("type: " + this.items.get(index).type);
            input.nextLine();
            System.out.println("Newspaper Found !     Enter new Details:-");
            String company, title, date;

            System.out.print("Enter Title of the Newspaper: ");
            title = input.nextLine();
            System.out.print("Enter Publisher Company of the Newspaper: ");
            company = input.nextLine();
            System.out.print("Enter Date of Publish: ");
            date = input.nextLine();
            System.out.print("Enter Popularity Count: ");
            count = input.nextInt();

            Newspaper np = new Newspaper(title, company, date, 0, count);
            this.items.remove(index);
            this.items.add(index, np);
            System.out.println("Newspaper Edited Successfully");
        } else {
            System.out.println("Item NOT Found !!!");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------
}
