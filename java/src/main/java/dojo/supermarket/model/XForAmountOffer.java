package dojo.supermarket.model;

public class XForAmountOffer extends Offer {
    private Product product;
    private double price;
    private int numberInPack;

    public XForAmountOffer(Product product, double price, int numberInPack) {
        super(SpecialOfferType.TwoForAmount, product, price);
        this.product = product;
        this.price = price;
        this.numberInPack = numberInPack;
    }

    @Override
    public Discount calculate(double unitPrice, int quantity) {
        if (quantity >= numberInPack) {
            double total = price * quantity / numberInPack + quantity % numberInPack * unitPrice;
            double discountN = unitPrice * quantity - total;
            return new Discount(product, numberInPack + " for " + price, discountN);
        }
        return null;
    }
}
