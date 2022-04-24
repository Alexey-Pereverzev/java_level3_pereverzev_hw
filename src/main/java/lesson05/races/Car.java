package lesson05.races;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private CountDownLatch cdl_prepare;
    private CyclicBarrier cb;
    private CountDownLatch cdl_race_finish;
    private ReentrantLock rl;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch cdl_prepare, CyclicBarrier cb, CountDownLatch cdl_race_finish, ReentrantLock rl) {
        this.race = race;
        this.speed = speed;
        this.cdl_prepare = cdl_prepare;
        this.cb = cb;
        this.cdl_race_finish = cdl_race_finish;
        this.rl = rl;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl_prepare.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        cdl_race_finish.countDown();

        if (rl.tryLock()) {
            System.out.println(name + " - WIN");
        }

    }
}
