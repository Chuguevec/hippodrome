import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled //Manual ON
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void timeTest (){
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
