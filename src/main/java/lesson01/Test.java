package lesson01;

import java.util.ArrayList;

public class Test {

    private static ArrayOperations<String> aos;         // Задания 1-2 делаем на примере массивов String[] и Integer[]
    private static ArrayOperations<Integer> aoi;

    public static void main(String[] args) {
        String[] array = new String[]{"A", "B", "C", "D", "E"};     //  ЗАДАНИЕ 1
        Integer[] array1 = new Integer[]{1, 2, 2, 3, 54, 7, 8, 9, 0};
        aos = new ArrayOperations<>();
        aoi = new ArrayOperations<>();
        aos.swapElements(array, 1, 4);
        aoi.swapElements(array1, 8, 1);

        System.out.println("Задание 1 — результат перестановки элементов массива:");
        System.out.println(aos.toString(array));
        System.out.println(aoi.toString(array1));
        System.out.println();

        System.out.println("-----");                                //  ЗАДАНИЕ 2
        System.out.println();
        System.out.println("Задание 2 — печать ArrayList, полученного из массива:");

        ArrayList<String> alString = aos.toArrayList(array);
        ArrayList<Integer> alInteger = aoi.toArrayList(array1);
        System.out.println(alString.toString());
        System.out.println(alInteger.toString());
        System.out.println();

        System.out.println("-----");                                //  ЗАДАНИЕ 3
        System.out.println();

        Box<Apple> b1 = new Box<>("Apple");                // создаем коробки
        Box<Orange> b2 = new Box<>("Orange");
        Box<Apple> b3 = new Box<>("Apple");
        Box<Orange> b4 = new Box<>("Orange");
        Box<Orange> b5 = new Box<>("Orange");

        for (int i = 0; i < 12; i++) {
            b1.add(new Apple());
        }   //наполняем коробки фруктами
        for (int i = 0; i < 8; i++) {
            b2.add(new Orange());
        }
        for (int i = 0; i < 8; i++) {
            b3.add(new Apple());
        }
        for (int i = 0; i < 10; i++) {
            b4.add(new Orange());
        }
        for (int i = 0; i < 6; i++) {
            b5.add(new Orange());
        }


        System.out.println(b1.toString());          // взвешиваем коробки, метод getWeight используется методом toString
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        System.out.println(b4.toString());
        System.out.println(b5.toString());

        System.out.println();

        System.out.println("Равен ли вес 1 и 2 коробки: " + b1.compareTo(b2));      // сравнение веса коробок
        System.out.println("Равен ли вес 2 и 3 коробки: " + b2.compareTo(b3));
        System.out.println("Равен ли вес 3 и 4 коробки: " + b3.compareTo(b4));

        System.out.println();

        System.out.println("Пересыпаем содержимое 2й коробки в 4ю:");           // пересыпание фруктов
        b2.intersperse(b4);

        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        System.out.println(b4.toString());
        System.out.println(b5.toString());

    }
}
