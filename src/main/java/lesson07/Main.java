package lesson07;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        HomeWorkApp homeWorkApp = new HomeWorkApp();

        homeWorkApp.setArray7(new int[]{23, -46, -23});         // массив для проверки "сбалансированного массива"
        homeWorkApp.setArray8(new int[]{2, 3, 4, 5, 13, 2});    // массив для проверки сдвига
        homeWorkApp.setShift(2);                                    // величина сдвига

      /*  homeWorkApp.checkBalance();
        System.out.println();

        homeWorkApp.arrayShift();*/

        Class<?> aClass = Class.forName("lesson07.HomeWorkApp");
        RunTest runTest = new RunTest();
        runTest.start(aClass, homeWorkApp);

    }
}
