package lesson01;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayOperations<E> {


    public boolean swapElements(E[] array, int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= array.length || index2 >= array.length) {
            return false;
        } else {
            E temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
            return true;
        }
    }

    public String toString(E[] array) {
        String s = "";
        if (array.length > 0) {
            for (E e : array) {
                s = s.concat(e.toString()).concat(", ");
            }
            s = "[".concat(s.substring(0, s.length() - 2)).concat("]");
        } else {
            s = "[]";
        }
        return s;
    }

    public ArrayList<E> toArrayList(E[] array) {
        ArrayList<E> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }


}
