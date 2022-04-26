package lesson07;

import java.util.Arrays;

public class HomeWorkApp {

    private static int[] array7;
    private static int[] array8;
    private static int n;

    public void setArray7(int[] array7) {
        HomeWorkApp.array7 = array7;
    }

    public void setArray8(int[] array8) {
        HomeWorkApp.array8 = array8;
    }

    public void setShift(int n) {
        HomeWorkApp.n = n;
    }

    public static void printArray(int[] array) {    // вывод массива на экран, вспомогательный метод
        String arrayString = Arrays.toString(array);
        System.out.println(arrayString);
    }

    @BeforeSuite
    public void beforeExecution() {
        System.out.println("Test is running");
        System.out.println();
    }

    @AfterSuite
    public void afterExecution() {
        System.out.println();
        System.out.println("Test is finished");
    }

    @Test (priority = 2)
    public static void checkBalance() {      // (7) - "сбалансированный массив"
        if (array7.length <= 1) {
            System.out.println("false");
        } else {
            int leftPos = 0;
            int rightPos = array7.length - 1;  // задаем 2 индекса, которые проходят массив с левого и правого края к середине
            int leftSum = 0;   // начинаем суммирование с левого и правого края
            int rightSum = 0;
            System.out.println(checkBalance(array7, leftPos, rightPos, leftSum, rightSum));
        }
    }

    public static boolean checkBalance(int[] array, int leftpos, int rightpos, int leftsum, int rightsum) {
        if (leftpos >= rightpos) return false;
        if (leftpos == rightpos-1) {
            return ((leftsum+array[leftpos]) == (rightsum+array[rightpos]));
        } else {
            return (checkBalance(array, (leftpos+1), rightpos, (leftsum+array[leftpos]),rightsum) ||
                    checkBalance(array, leftpos, (rightpos-1), leftsum, (rightsum+array[rightpos])));
                        // рекурсивный вызов со сдвигом либо левого либо правого курсора
        }
    }

    public static int[] oneStepShift(int[] array) {    // сдвиг массива на 1 позицию - вспомогательный метод
        int[] result = Arrays.copyOf(array, array.length);
        int buffer = result[result.length - 1];
        System.arraycopy(result, 0, result, 1, result.length - 1);
        result[0] = buffer;
        return result;
    }

    @Test
    public static void arrayShift() {                  // (8) - сдвиг массива на произвольное число позиций
        int[] result = Arrays.copyOf(array8, array8.length);
        int shiftNumber;
        if (n >= 0) {                                       // для положительного числа сдвигаем на остаток от деления
            shiftNumber = n % (array8.length);               // на длину массива
        } else {
            shiftNumber = array8.length - ((-n) % (array8.length));   // вычисляем размер сдвига для отрицательного числа
        }
        for (int i = 0; i < shiftNumber; i++) {
            result = oneStepShift(result);
        }
        printArray(result);
    }


}
