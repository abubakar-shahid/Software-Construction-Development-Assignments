import java.util.*;

public class Library {

    public List<Book> items;

    Scanner input = new Scanner(System.in);
//------------------------------------------------------------------------------------------------------------------------------------------

    public Library() {
        this.items = new ArrayList<>();
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
        System.out.println("Following Books are currently available:-");
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).borrower == null) {
                this.items.get(i).displayInfo();
            }
        }
        int index = 0, id;
        boolean flag = false;
        System.out.print("Enter Book ID: ");
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
                double totalCost = this.items.get(index).calculateCost();
                Borrower b1 = new Borrower(name, number, totalCost);
                if (this.items.get(index).checkBorrower(b1)) {
                    System.out.println("You have already Borrowed this item previously !!! You cannot borrow it again !!!");
                } else {
                    this.items.get(index).borrower = b1;
                    this.items.get(index).borrowersList.add(b1);
                    System.out.println("Book Borrowed Successfully !!!");
                    System.out.println("Your Total Cost is: Rs." + totalCost + "/-");
                }
            } else {
                System.out.println("This Book is already borrowed !!!");
            }

        } else {
            System.out.println("Item NOT Found !!!");
        }
        input.nextLine();
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void addItem() {
        String title = "";
        String author = "";
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

    public void editItem() {
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

    public void deleteItem() {
        int id;
        boolean flag = false;
        System.out.print("Enter Book ID: ");
        id = input.nextInt();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                this.items.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("Book Deleted Successfully");
        } else {
            System.out.println("Item NOT Found !!!");
        }
        input.nextLine();
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
        System.out.print("Enter Book ID: ");
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
        input.nextLine();
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
}
