package lesson06;

import java.util.Arrays;

public class HomeWorkApp {

    public static void main(String[] args) {
                                                            // (7) - "сбалансированный массив"
        int[] array5 = {23, -46, -23};
        System.out.println(checkBalance(array5));

        System.out.println();                               // (8) - сдвиг массива на произвольное число позиций
        int[] array6 = {2, 3, 4, 5, 13, 2};
        int n = 2;
        printArray(arrayShift(array6, n));
    }


    public static void printArray(int[] array) {    // вывод массива на экран, вспомогательный метод
        String arrayString = Arrays.toString(array);
        System.out.println(arrayString);
    }

    public static boolean checkBalance(int[] array) {      // (7) - "сбалансированный массив"
        if (array.length <= 1) {
            return false;
        } else {
            int leftPos = 0;
            int rightPos = array.length - 1;  // задаем 2 индекса, которые проходят массив с левого и правого края к середине
            int leftSum = 0;   // начинаем суммирование с левого и правого края
            int rightSum = 0;
            return checkBalance(array, leftPos, rightPos, leftSum, rightSum);
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

    public static int[] arrayShift(int[] array, int n) {   // (8) - сдвиг массива на произвольное число позиций
        int[] result = Arrays.copyOf(array, array.length);
        int shiftNumber;
        if (n >= 0) {                                       // для положительного числа сдвигаем на остаток от деления
            shiftNumber = n % (array.length);               // на длину массива
        } else {
            shiftNumber = array.length - ((-n) % (array.length));   // вычисляем размер сдвига для отрицательного числа
        }
        for (int i = 0; i < shiftNumber; i++) {
            result = oneStepShift(result);
        }
        return result;
    }


}
