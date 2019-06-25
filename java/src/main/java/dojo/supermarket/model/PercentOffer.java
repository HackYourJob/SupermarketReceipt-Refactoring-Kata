package dojo.supermarket.model;

public class PercentOffer extends Offer {
    private Product product;
    private double percent;

    public PercentOffer(Product product, double percent) {
        super(SpecialOfferType.TenPercentDiscount, product, percent);
        this.product = product;
        this.percent = percent;
    }

    @Override
    public Discount calculate(double unitPrice, int quantity) {
        return new Discount(product, percent + "% off", quantity * unitPrice * percent / 100.0);
    }
}
