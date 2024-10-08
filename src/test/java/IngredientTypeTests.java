import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTests {
    private final IngredientType type;

    public IngredientTypeTests(IngredientType type) {
        this.type = type;
    }

    @Parameterized.Parameters
    public static Object[][] dataForTests() {
        return new Object[][] {
                {IngredientType.SAUCE},
                {IngredientType.FILLING}
        };
    }

    @Test
    public void testIngredientType() {
        assertEquals(type.toString(), type.toString());
    }
}
