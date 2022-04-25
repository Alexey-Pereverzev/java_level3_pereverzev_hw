package lesson07;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class RunTest {

    private static boolean isBeforeNotDoubled;
    private static boolean isAfterNotDoubled;
    private static ArrayList<Method> testMethods;
    private static ArrayList<Integer> priorities;
    private static Method beforeSuite;
    private static Method afterSuite;
    private static boolean isBeforePresent;
    private static boolean isAfterPresent;

    private static void getAnnotationMethods(Class<?> aClass) {
        Method[] methods = aClass.getDeclaredMethods();

        isBeforePresent = false;
        isBeforeNotDoubled = true;
        isAfterPresent = false;
        isAfterNotDoubled = true;
        testMethods = new ArrayList<>();
        priorities = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (!isBeforePresent) {
                    isBeforePresent = true;
                    beforeSuite = method;
                } else {
                    isBeforeNotDoubled = false;
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (!isAfterPresent) {
                    isAfterPresent = true;
                    afterSuite = method;
                } else {
                    isAfterNotDoubled = false;
                }
            }
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
                priorities.add(method.getAnnotation(Test.class).priority());
            }
        }
        sortTestMethods();
        
    }

    private static void sortTestMethods() {
        int i = 0;
        Method m;
        int temp;
        while (i<testMethods.size()-1) {
            if (priorities.get(i) > priorities.get(i+1)) {
                m = testMethods.get(i);
                testMethods.set(i,testMethods.get(i+1));
                testMethods.set(i+1,m);
                temp = priorities.get(i);
                priorities.set(i,priorities.get(i+1));
                priorities.set(i+1,temp);
                if (i>0) i--;
            } else {
                i++;
            }
        }
    }

    public static void start(Class<?> aClass, Object o) {
        getAnnotationMethods(aClass);
        try {
            if (!isBeforeNotDoubled) {
                throw new RuntimeException ("Более 1 метода @BeforeSuite");
            }
            if (!isAfterNotDoubled) {
                throw new RuntimeException ("Более 1 метода @AfterSuite");
            }

            if (isBeforePresent) {
                beforeSuite.invoke(o);
            }

            for (Method testMethod : testMethods) {
                testMethod.invoke(o);
            }

            if (isAfterPresent) {
                afterSuite.invoke(o);
            }


        } catch (RuntimeException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
