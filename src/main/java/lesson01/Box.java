package lesson01;

import java.util.ArrayList;

public class Box<E extends Fruit> {
    private final ArrayList<E> fruits;
    private final String className;

    public Box(String className) {
        fruits = new ArrayList<>();
        this.className = className;
    }

    public double getWeight() {
        double sum = 0f;
        for (E fruit : fruits) {
            sum = sum + fruit.weight;
        }
        return sum;
    }

    public String toString() {
        String s = "В коробке ";
        s = s.concat(fruits.size() + "");
        s = s.concat(" фруктов ");
        s = s.concat(className);
        s = s.concat(" весом ");
        s = s.concat(getWeight() + "");
        return s;
    }

    public boolean compareTo(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001f;
    }

    public void add(E fruit) {
        fruits.add(fruit);
    }

    public void intersperse(Box<E> anotherBox) {
        int i = fruits.size();
        while (i > 0) {
            E fruit = fruits.get(i - 1);
            anotherBox.add(fruit);
            fruits.remove(fruit);
            i--;
        }
    }

}
