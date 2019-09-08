import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void test() {
        Todo t = new Todo("Watch tv");
        String output = t.toString();
        assertEquals("[T][✗] Watch tv", output);
    }
}
