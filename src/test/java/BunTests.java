import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTests {
    private final String name;
    private final float price;

    public BunTests(String name, float price ){
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] Buns() {
        return new Object[][]{
                {"",0},
                {"big",12},
                {"bigmag",1234},
                {"bigmagbigmagbigmagbigmag",12345678},
                {"^#(&@#*)#*@(*#", -123},
        };
    }

    @Test
    public void SetBunReturnOurBunTests(){
        Burger burger = new Burger();
        burger.setBuns(new Bun(name, price));
        List<String> expected = new ArrayList<>();
        expected.add(name);
        expected.add(String.valueOf(price));
        List<String> actual = new ArrayList<>();
        actual.add(burger.bun.getName());
        actual.add(String.valueOf(burger.bun.getPrice()));
        assertEquals(expected ,actual);
    }
}
