//Parallel Array Sum:
//Write a Java program that calculates the sum of elements in an array using multiple threads.
//Each thread should be responsible for computing the sum of a portion of the array.
//Combine the partial sums to obtain the total sum.

public class Q1 {
    public static final int ARRAY_SIZE = 8;
    public static final int NUM_THREADS = 4;
    public static int[] array = new int[ARRAY_SIZE];

    public static void main(String[] args) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        Thread[] threads = new Thread[NUM_THREADS];

        int chunkSize = ARRAY_SIZE / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == NUM_THREADS - 1) ? ARRAY_SIZE : (i + 1) * chunkSize;

            threads[i] = new Thread(new SumTask(startIndex, endIndex));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalSum = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            totalSum += SumTask.getPartialSum(i);
        }

        System.out.print("Array: ");
        for (int i = 0; i < Q1.ARRAY_SIZE; i++) {
            System.out.print(Q1.array[i] + " ");
        }
        System.out.println();
        System.out.println("Total Sum: " + totalSum);
    }

}

class SumTask implements Runnable {
    private static int[] array;
    private static int[] partialSums;
    private int startIndex;
    private int endIndex;

    public SumTask(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        int partialSum = 0;
        for (int i = startIndex; i < endIndex; i++) {
            partialSum += array[i];
        }
        partialSums[(int) (Thread.currentThread().getId() % Q1.NUM_THREADS)] = partialSum;
    }

    public static int getPartialSum(int threadId) {
        return partialSums[threadId];
    }

    static {
        array = Q1.array;
        partialSums = new int[Q1.NUM_THREADS];
    }
}
