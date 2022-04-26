package lesson05.races;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        CountDownLatch cdl_prepare = new CountDownLatch(CARS_COUNT);
        CountDownLatch cdl_race_finish = new CountDownLatch(CARS_COUNT);
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
        Semaphore sem = new Semaphore(CARS_COUNT / 2);
        ReentrantLock rl = new ReentrantLock();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(sem), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdl_prepare, cb, cdl_race_finish, rl);
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            cdl_prepare.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            cdl_race_finish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

