package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupermarketTest {
    private final Product toothbrush = new Product("toothbrush", ProductUnit.Each);
    private final Product apples = new Product("apples", ProductUnit.Kilo);

    @Test
    public void productsWithoutOffers() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        cart.addItemQuantity(toothbrush, 2);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(6.955, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void threeForTwoOffer() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(
                new ThreeForTwoOffer(toothbrush));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(1.98, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void threeForTwoOfferWithAdditionalProduct() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new ThreeForTwoOffer(toothbrush));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 4);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(2.97, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void twoForAmountOfferWithAdditionalProduct() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new XForAmountOffer(toothbrush, 1.5, 2));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(3.24, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void twoForAmountOffer() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new XForAmountOffer(toothbrush, 1.5, 2));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(1.5, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void fiveForAmountOffer() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new XForAmountOffer(toothbrush, 1.5, 5));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 5);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(1.5, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void tenPercentOffer() {
        // Actors
        SupermarketCatalog catalog = getSupermarketCatalog();
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new PercentOffer(toothbrush, 10));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 5);

        // Action
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // Asserts
        assertEquals(4.455, receipt.getTotalPrice(), 0.01);
    }

    private SupermarketCatalog getSupermarketCatalog() {
        SupermarketCatalog catalog = new FakeCatalog();
        catalog.addProduct(toothbrush, 0.99);
        catalog.addProduct(apples, 1.99);
        return catalog;
    }
}
