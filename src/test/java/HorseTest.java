import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void nameNullThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Horse horse = new Horse(null, 10);
        });
        try {
            Horse horse = new Horse(null, 10);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\t"})
    void enterEmptyNameThrowException(String value) {
        assertThrows(IllegalArgumentException.class, () -> {
            Horse horse = new Horse(value, 10);
        });
        try {
            Horse horse = new Horse(value, 10);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void speedNegativeThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Horse horse = new Horse("Пушка", -10);
        });
        try {
            Horse horse = new Horse("Гонка", -10);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void distanceNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Horse horse = new Horse("Пушка", 10, -10);
        });
        try {
            Horse horse = new Horse("Гонка", 10, -10);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getName() {
        String name = "Пушка";
        Horse horse = new Horse(name, 10);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        int speed = 42;
        Horse horse = new Horse("Пушка", speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        int distance = 53;
        Horse horse = new Horse("Пушка", 10, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistanceReturn0() {
        Horse horse = new Horse("Пушка", 10);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveUseGetRandom() {
        try (MockedStatic<Horse> mokHorse = Mockito.mockStatic(Horse.class)) {
            new Horse("Спирит", 5, 10).move();
            mokHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.9})
    void moveReturn(double random) {
        try (MockedStatic<Horse> mokHorse = Mockito.mockStatic(Horse.class)) {
            double distance = 10d;
            Horse horse = new Horse("SomeName", 10, distance);
            double resultDistance = distance + horse.getSpeed() * random;
            mokHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(resultDistance, horse.getDistance());
        }
    }
}
