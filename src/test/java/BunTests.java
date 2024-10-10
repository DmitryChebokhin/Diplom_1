import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import java.util.Random;

public class BunTests {

    private final double DELTA = 0.001;

    private String bunName;
    private float bunPrice;
    private Random random;

    @Before
    public void setUp() {
        random = new Random();
        bunName = RandomStringUtils.randomAlphabetic(5, 15);
        bunPrice = random.nextFloat() + 10;
    }

    @Test
    public void testGetName() {
        Bun bun = createBun();
        String actualName = bun.getName();
        Assert.assertEquals(bunName, actualName);
    }

    @Test
    public void testGetPrice() {
        Bun bun = createBun();
        float actualPrice = bun.getPrice();
        Assert.assertEquals(bunPrice, actualPrice, DELTA);
    }

    private Bun createBun() {
        return new Bun(bunName, bunPrice);
    }
}
