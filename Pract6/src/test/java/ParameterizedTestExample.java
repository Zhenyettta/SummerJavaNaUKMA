import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(strings = {"hello", "world", "JUnit"})
    void testStringNotEmpty(String word) {
        assertNotNull(word);
        assertFalse(word.isEmpty());
    }
}
