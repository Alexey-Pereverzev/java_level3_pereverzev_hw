package lesson04.task1;


public class Main {

    public static void main(String[] args) {
        Prints prints = new Prints();

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                prints.printALetter();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5; j++) {
                prints.printBLetter();
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            for (int k = 0; k < 5; k++) {
                prints.printCLetter();
            }
        });
        t3.start();
    }
}
