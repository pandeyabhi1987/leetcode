import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class DailyTemperaturesTest {
    private final DailyTemperatures solution = new DailyTemperatures();

    @Test
    public void testDailyTemperatures() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
        int[] result = solution.dailyTemperatures(temperatures);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testDailyTemperaturesEmptyArray() {
        int[] temperatures = {};
        int[] expected = {};
        int[] result = solution.dailyTemperatures(temperatures);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testDailyTemperaturesSingleElement() {
        int[] temperatures = {85};
        int[] expected = {0};
        int[] result = solution.dailyTemperatures(temperatures);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testDailyTemperaturesIncreasingTemperatures() {
        int[] temperatures = {60, 65, 70, 75, 80};
        int[] expected = {1, 1, 1, 1, 0};
        int[] result = solution.dailyTemperatures(temperatures);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testDailyTemperaturesDecreasingTemperatures() {
        int[] temperatures = {80, 75, 70, 65, 60};
        int[] expected = {0, 0, 0, 0, 0};
        int[] result = solution.dailyTemperatures(temperatures);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testDailyTemperaturesEqualTemperatures() {
        int[] temperatures = {60, 60, 60, 60, 60};
        int[] expected = {0, 0, 0, 0, 0};
        int[] result = solution.dailyTemperatures(temperatures);
        Assertions.assertArrayEquals(expected, result);
    }
}