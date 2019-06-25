package dojo.supermarket.model;

import static dojo.supermarket.model.SpecialOfferType.ThreeForTwo;

public class ThreeForTwoOffer extends Offer {
    private Product product;

    public ThreeForTwoOffer(Product product) {
        super(ThreeForTwo, product, 0);
        this.product = product;
    }

    public Discount calculate(double unitPrice, int quantity) {
        double discountAmount = quantity * unitPrice - ((quantity/3 * 2 * unitPrice) + quantity % 3 * unitPrice);
        return new Discount(product, "3 for 2", discountAmount);
    }
}
