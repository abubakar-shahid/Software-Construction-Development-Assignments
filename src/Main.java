import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Testing of Question#1
        System.out.println("-------------------- Testing Question # 1 --------------------");
        Q1_GenericStack<Integer> st1 = new Q1_GenericStack();
        try {
            System.out.println("Popped: " + st1.popStack());
        } catch (Q1_GenericStack.EmptyStackException exp) {
            System.out.println("Caught StackEmptyException: " + exp.getMessage());
        }
        System.out.println("----- After Pushing into Stack -----");
        st1.pushStack(13);
        st1.pushStack(9);
        st1.pushStack(2002);
        st1.printStack();
        System.out.println("Stack size: " + st1.stackSize());
        try {
            System.out.println("Popped: " + st1.popStack());
            System.out.println("Popped: " + st1.popStack());
        } catch (Q1_GenericStack.EmptyStackException exp) {
            System.out.println("Caught StackEmptyException: " + exp.getMessage());
        }
        st1.printStack();
        System.out.println("Stack size: " + st1.stackSize());
        if (st1.StackIsEmpty()) {
            System.out.println("Stack Empty :(");
        } else {
            System.out.println("Stack is not Empty :)");
        }
        //Testing of Question#2
        System.out.println("-------------------- Testing Question # 2 --------------------");
        Q2_FirstNonRepeatingStream obj = new Q2_FirstNonRepeatingStream();
        obj.addChar('a');
        obj.addChar('b');
        obj.addChar('f');
        //obj.addChar('d');
        obj.addChar('a');
        obj.addChar('b');
        //obj.addChar('e');
        //obj.addChar('c');
        obj.addChar('f');
        System.out.println("First Non-Repeating Character is : " + obj.getFirstNonRepeating());
        //Testing of Question#3
        System.out.println("-------------------- Testing Question # 3 --------------------");
        List<int[]> intervals = new ArrayList<>();
        int[] arr1 = {1, 3};
        intervals.add(arr1);
        int[] arr2 = {2, 6};
        intervals.add(arr2);
        int[] arr6 = {5, 7};
        intervals.add(arr6);
        int[] arr3 = {4, 10};
        intervals.add(arr3);
        int[] arr4 = {15, 18};
        intervals.add(arr4);
        int[] arr5 = {17, 20};
        intervals.add(arr5);
        Q3_MergeIntervals mObj = new Q3_MergeIntervals();
        System.out.println("Sample Input :-");
        mObj.print(intervals);
        mObj.mergeIntervals(intervals);
        System.out.println("Sample Output :-");
        mObj.print(intervals);
    }

}
