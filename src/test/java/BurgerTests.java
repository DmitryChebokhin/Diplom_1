import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTests {

    private final double DELTA = 0.001;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient ingredient2;

    @Test
    public void testSetBuns(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        when(bun.getPrice()).thenReturn(3.0f);
        when(ingredient.getPrice()).thenReturn(1.0f);
        when(ingredient2.getPrice()).thenReturn(2.0f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        float expectedPrice = 3.0f * 2 + 1.0f + 2.0f; // Ожидаемая цена бургера (цена булочки * 2 + цена первого ингредиента + цена второго ингредиента)
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();
        when(bun.getName()).thenReturn("BunName");
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("SauceName");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("FillingName");

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);


        String expectedReceipt = "(==== BunName ====)\n" +
                "= sauce SauceName =\n" +
                "= filling FillingName =\n" +
                "(==== BunName ====)\n" +
                "\nPrice: 0,000000\n";

        String actualReceipt = burger.getReceipt();
        expectedReceipt = expectedReceipt.replace("\n", System.lineSeparator()); // Заменяю символы перевода строки для корректного сравнения
        assertEquals(expectedReceipt, actualReceipt);
    }
}
