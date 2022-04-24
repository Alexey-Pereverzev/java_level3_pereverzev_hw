package lesson04.task1;

public class Prints {
    private char printed;

    public Prints() {
        this.printed = '0';
    }

    public synchronized void printALetter() {
        while (printed != 'C' && printed != '0') {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printed = 'A';
        System.out.print("A");
        notifyAll();
    }

    public synchronized void printBLetter() {
        while (printed != 'A') {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printed = 'B';
        System.out.print("B");
        notifyAll();
    }

    public synchronized void printCLetter() {
        while (printed != 'B') {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printed = 'C';
        System.out.print("C");
        notifyAll();
    }
}
