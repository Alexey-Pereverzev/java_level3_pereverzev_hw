package lesson06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class HomeWorkAppTest {

    private HomeWorkApp homeWorkApp;

    @BeforeEach
    void startUp() {
        System.out.println("Начало тестирования");
        homeWorkApp = new HomeWorkApp();
    }

    @DisplayName("Тест сбалансированного массива")
    @ParameterizedTest
    @MethodSource("data")
    void paramTest1(boolean expected, int[] array) {
        Assertions.assertEquals(expected, homeWorkApp.checkBalance(array));
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments(false, new int[]{2, 3, 4, 5, 13, 2}),
                Arguments.arguments(true, new int[]{2, 3, 4, 4, 3, 2}),
                Arguments.arguments(true, new int[]{2, 3, 4, 5, 14}),
                Arguments.arguments(true, new int[]{-1, 1, 1, 1}),
                Arguments.arguments(true, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 1, -2}),
                Arguments.arguments(true, new int[]{-3,4}),
                Arguments.arguments(true, new int[]{23, -46, -23})
        );
    }

    @DisplayName("Тест сдвига массива")
    @ParameterizedTest
    @MethodSource("data2")
    void paramTest2(int[] expected, int[] array, int n) {
        Assertions.assertArrayEquals(expected, homeWorkApp.arrayShift(array, n));
    }

    static Stream<Arguments> data2() {
        return Stream.of(
                Arguments.arguments(new int[]{2, 3, 4, 5, 13, 2}, new int[]{2, 3, 4, 5, 13, 2}, 6),
                Arguments.arguments(new int[]{2, 2, 3, 4, 5, 13}, new int[]{2, 3, 4, 5, 13, 2}, 1),
                Arguments.arguments(new int[]{13, 2, 2, 3, 4, 5}, new int[]{2, 3, 4, 5, 13, 2}, 2),
                Arguments.arguments(new int[]{2, 3, 4, 5, 13, 2}, new int[]{2, 3, 4, 5, 13, 2}, 0),
                Arguments.arguments(new int[]{5, 13, 2, 2, 3, 4}, new int[]{2, 3, 4, 5, 13, 2}, 3),
                Arguments.arguments(new int[]{13, 2, 2, 3, 4, 5}, new int[]{2, 3, 4, 5, 13, 2}, 2),
                Arguments.arguments(new int[]{3, 4, 5, 13, 2, 2}, new int[]{2, 3, 4, 5, 13, 2}, -1),
                Arguments.arguments(new int[]{2, 3, 4, 5, 13, 2}, new int[]{2, 3, 4, 5, 13, 2}, -6),
                Arguments.arguments(new int[]{4, 5, 13, 2, 2, 3}, new int[]{2, 3, 4, 5, 13, 2}, 10),
                Arguments.arguments(new int[]{13, 2, 2, 3, 4, 5}, new int[]{2, 3, 4, 5, 13, 2}, -10)
        );
    }

}