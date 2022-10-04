import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class HippodromeTest {

    @Test
    public void constructorException (){
        assertThrows(IllegalArgumentException.class,()-> {
            Hippodrome hippodrome = new Hippodrome(null);
        });

        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }


    }
}
