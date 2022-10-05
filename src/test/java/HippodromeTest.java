import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    public void nullListException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Hippodrome hippodrome = new Hippodrome(null);
        });
        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void emptyListException() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Hippodrome hippodrome = new Hippodrome(horses);
        });
        try {
            Hippodrome hippodrome = new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Лошадь №" + i, 5, 10));
        }
        Hippodrome hippodrome = new Hippodrome(List.copyOf(horses));
        List<Horse> getHorses = hippodrome.getHorses();
        for (int i = 0; i < horses.size(); i++) {
            assertEquals(horses.get(i), getHorses.get(i));
        }
    }

    @Test
    public void move() {
        int horseCount = 50;
        List<Horse> horses = new ArrayList<>();
        Horse horse = Mockito.mock(Horse.class);
        for (int i = 0; i < horseCount; i++) {
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse, Mockito.times(horseCount)).move();
    }

    @Test
    public void getWinner() {
        int horseCount = 20;
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <= horseCount; i++) {
            horses.add(new Horse("Horse №" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(horseCount), hippodrome.getWinner());
    }
}
