import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        //JOptionPane.showMessageDialog(x, "Will be Printing Chart Soon :)");
        PieChart chart = new PieChart(this.items);
        JFrame y = new JFrame();
        y.setVisible(true);
        y.setSize(400, 400);
        y.add(chart);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    JTextField text1 = new JTextField(5);
    JTextField text2 = new JTextField(20);
    JTextField text3 = new JTextField(15);

    public void borrowItem() {
        x = new JFrame("Borrow Book");
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
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    JTextField t5;

    public void addItem() {
        x = new JFrame("Add Book");
        x.setSize(400, 400);
        x.setVisible(true);
        jp = new JPanel();
        jp.setLayout(new GridLayout(6, 1));
        JLabel l1 = new JLabel("Title: ");
        JLabel l2 = new JLabel("Author: ");
        JLabel l3 = new JLabel("Year of Publication: ");
        JLabel l4 = new JLabel("Popularity Count: ");
        JLabel l5 = new JLabel("Cost: ");
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(5);
        t4 = new JTextField(5);
        t5 = new JTextField(5);
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
        int year = Integer.parseInt(t3.getText());
        int count = Integer.parseInt(t4.getText());
        int cost = Integer.parseInt(t5.getText());
        Book book = new Book(title, author, year, 0, count, cost);
        this.items.add(book);
        JOptionPane.showMessageDialog(x, "Book Added Successfully!");
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
    }
//-----------------------------------------------------------------------------------------------------------------------------------------

    JTextField t6;
    int index;

    public void editItem() {
        x = new JFrame("Edit Book");
        x.setSize(400, 400);
        x.setVisible(true);
        jp = new JPanel();
        jp.setLayout(new GridLayout(7, 1));
        JLabel l1 = new JLabel("Title: ");
        JLabel l2 = new JLabel("Author: ");
        JLabel l3 = new JLabel("Year of Publication: ");
        JLabel l4 = new JLabel("Popularity Count: ");
        JLabel l5 = new JLabel("Cost: ");
        JLabel l6 = new JLabel("Book Id: ");
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);
        t6 = new JTextField(5);
        JPanel p7 = new JPanel(new FlowLayout());
        p7.add(l6);
        p7.add(t6);
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
        JButton enter = new JButton("Enter");
        JButton confirm = new JButton("Confirm");
        enter.addActionListener(this::actionPerformed);
        confirm.addActionListener(this::actionPerformed);
        p7.add(enter);
        p6.add(confirm);
        jp.add(p7);
        jp.add(p1);
        jp.add(p2);
        jp.add(p3);
        jp.add(p4);
        jp.add(p5);
        jp.add(p6);
        x.add(jp);
    }

    public void enter() {
        int id = Integer.parseInt(t6.getText());
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == id) {
                index = i;
                id = this.items.get(index).id;
                flag = true;
                break;
            }
        }
        if (flag) {
            t1.setText(this.items.get(index).title);
            t2.setText(this.items.get(index).author);
            t3.setText(String.valueOf(this.items.get(index).year));
            t4.setText(String.valueOf(this.items.get(index).popularityCount));
            t5.setText(String.valueOf(this.items.get(index).cost));
        } else {
            JOptionPane.showMessageDialog(x, "Book NOT Found!", "Error", JOptionPane.WARNING_MESSAGE);
            t6.setText("");
        }
    }

    public void save() {
        this.items.get(index).title = t1.getText();
        this.items.get(index).author = t2.getText();
        this.items.get(index).year = Integer.parseInt(t3.getText());
        this.items.get(index).popularityCount = Integer.parseInt(t4.getText());
        this.items.get(index).cost = Integer.parseInt(t5.getText());
        JOptionPane.showMessageDialog(x, "Book Edited Successfully!");
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void deleteItem() {
        x = new JFrame("Delete Book");
        x.setVisible(true);
        x.setSize(500, 400);
        x.setLayout(new GridLayout(2, 1));
        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        sp = new JScrollPane(table);
        jp.add(sp);
        jl = new JLabel("Enter Book ID: ");
        jtf = new JTextField(10);
        jb = new JButton("Delete Item");
        jb.addActionListener(this);
        JPanel jp2 = new JPanel(new FlowLayout());
        jp2.add(jl);
        jp2.add(jtf);
        jp2.add(jb);
        x.add(jp);
        x.add(jp2);
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
    }

    public void delete() {
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).id == Integer.parseInt(jtf.getText())) {
                index = i;
                this.items.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(x, "Book Deleted Successfully!");
            model.removeRow(index);
        } else {
            JOptionPane.showMessageDialog(x, "Book NOT Found!", "Error", JOptionPane.WARNING_MESSAGE);
        }
        jtf.setText("");
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewAllItems() {
        x = new JFrame("All Books");
        x.setVisible(true);
        x.setSize(500, 400);
        x.setLayout(new GridLayout(2, 1));
        model = new DefaultTableModel();
        String[] columns = {"ID", "Title", "Author", "Year of Publication", "Popularity Count", "Cost", "Read Item"};
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new ButtonEditor());
        table.addMouseListener(new MouseAdapter() {
            private static int hoveredRow = -1;

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1;
                table.repaint();
            }
        });

        table.addMouseMotionListener(new MouseMotionListener() {
            private static int hoveredRow = -1;

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);

                if (row != hoveredRow) {
                    hoveredRow = row;
                    table.repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Not used in this example
            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private static int hoveredRow = -1;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                if (row == hoveredRow) {
                    component.setBackground(Color.gray);
                } else {
                    component.setBackground(table.getBackground());
                }
                return component;
            }
        });
        sp = new JScrollPane(table);
        x.add(sp);
        int rows = this.items.size();
        for (int i = 0; i < rows; i++) {
            //JButton b = new JButton("read");
            Object[] data = {
                    String.valueOf(this.items.get(i).id),
                    this.items.get(i).title,
                    this.items.get(i).author,
                    String.valueOf(this.items.get(i).year),
                    String.valueOf(this.items.get(i).popularityCount),
                    String.valueOf(this.items.get(i).cost),
                    //createButton()
            };
            model.addRow(data);
            model.setValueAt(createButton(), i, 6);
        }
    }

    private static JButton createButton() {
        JButton button = new JButton("Read");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Code to to with Button
            }
        });
        return button;
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public void viewItemByID() {
        x = new JFrame("View Book by ID");
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
            jtf.setText("");
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    public void viewBorrowersList() {
        boolean flag = false;
        x = new JFrame("Borrowers List");
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
            case "Enter" -> enter();
            case "Confirm" -> save();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private String originalText;

        public ButtonEditor() {
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    JOptionPane.showMessageDialog(null, "Button Clicked!");
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof JButton) {
                button = (JButton) value;
                originalText = button.getText();
            }
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            // Restore the original text when editing is stopped
            button.setText(originalText);
            return button;
        }
    }
}