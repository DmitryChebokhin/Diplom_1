import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

public class IngredientTests {
    Random random = new Random();
    public IngredientType type = IngredientType.SAUCE;
    public String name = RandomStringUtils.randomAlphabetic(5,15);
    public float price = random.nextFloat() + 10;

    @Test
    public void testType(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
    }

    @Test
    public void testName(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(name, ingredient.getName());
    }

    @Test
    public void testPrice(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(price, ingredient.getPrice(), 0.001);
    }
}
