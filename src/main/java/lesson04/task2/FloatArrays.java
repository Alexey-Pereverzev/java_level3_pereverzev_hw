package lesson04.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FloatArrays {
    static final int size = 10000000;
    static final int h = size / 2;
    public float[] arr;

    public FloatArrays() {
        this.arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }
    }

    public void method1() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("Первый метод: " + (b - a));
    }

    public void method2() {
        long a = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(() -> {

            for (int i = 0; i < h; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }

        });
        pool.execute(() -> {

            for (int i = h; i < size; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }

        });
        pool.shutdown();
        try {
            pool.awaitTermination(2000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long b = System.currentTimeMillis();
        System.out.println("Второй метод: " + (b - a));
    }
}
