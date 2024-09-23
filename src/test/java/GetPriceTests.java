import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GetPriceTests {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private final float price;

    public GetPriceTests(float price) {
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] BunPrice() {
        return new Object[][]{
                {0},
                {15},
                {150},
                {2513},
                {-10},
        };
    }

    @Test
    public void GetPriceForBurgerWithoutIngredientsTest() {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(price);
        Burger burger = new Burger();
        burger.setBuns(new Bun("bigmac", price));
        float expected = price * 2;
        float actual = burger.getPrice();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void GetPriceForBurgerWithOneIngredientTest() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(price);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        Burger burger = new Burger();
        burger.setBuns(new Bun("cheeseburger", price));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "mustard", price));
        float expected = price * 3;
        float actual = burger.getPrice();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void GetPriceForBurgerWithTwoIngredientsTest() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(price);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        Burger burger = new Burger();
        burger.setBuns(new Bun("hamburger", price));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "mustard", price));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", price + 100));
        float expected = price * 4 + 100;
        float actual = burger.getPrice();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void GetPriceForBurgerWithFourIngredientsTest() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(price);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        Burger burger = new Burger();
        burger.setBuns(new Bun("fishburger", price));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "mustard", price));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "omelette", price + 100));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "mayonnaise", price + 200));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "fish", price + 300));
        float expected = price * 6 + 600;
        float actual = burger.getPrice();
        assertEquals(expected, actual, 0);
    }
}