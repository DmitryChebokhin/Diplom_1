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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private Bun Bun;

    @Mock
    private Ingredient Ingredient;

    @Mock
    private Ingredient Ingredient2;

    @Test
    public void testSetBuns(){
        Burger burger = new Burger();
        burger.setBuns(Bun);
        assertEquals(Bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(Ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(Ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(Ingredient);
        burger.addIngredient(Ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(Ingredient2, burger.ingredients.get(0));
        assertEquals(Ingredient, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        when(Bun.getPrice()).thenReturn(3.0f);
        when(Ingredient.getPrice()).thenReturn(1.0f);
        when(Ingredient2.getPrice()).thenReturn(2.0f);
        burger.setBuns(Bun);
        burger.addIngredient(Ingredient);
        burger.addIngredient(Ingredient2);

        float expectedPrice = 3.0f * 2 + 1.0f + 2.0f; // Ожидаемая цена бургера (цена булочки * 2 + цена первого ингредиента + цена второго ингредиента)
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();
        when(Bun.getName()).thenReturn("BunName");
        when(Ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(Ingredient.getName()).thenReturn("SauceName");
        when(Ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(Ingredient2.getName()).thenReturn("FillingName");

        burger.setBuns(Bun);
        burger.addIngredient(Ingredient);
        burger.addIngredient(Ingredient2);


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
