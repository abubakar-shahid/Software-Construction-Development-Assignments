public class Main extends Thread{
    public void run(){
        System.out.println("Thread " + this.threadId() + " started!");
    }
    public static void main(String[] args) {
        Thread t1 = new Main();
        t1.start();
    }
}