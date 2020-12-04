package pl.mdziedzic.voucherstore.sales;

import org.junit.jupiter.api.Test;
import pl.mdziedzic.voucherstore.productcatalog.Product;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class BasketTest {

    public static final String PRODUCT_1 = "lego-2137";
    public static final String PRODUCT_2 = "lego-1488";

    @Test
    public void newlyCreatedBasketIsEmpty(){
        Basket basket = getEmptyBasket();
        assertThat(basket.isEmpty())
                .isTrue();

    }

    private Basket getEmptyBasket() {
        return  Basket.empty();
    }

    @Test
    public void basketWithProductIsNotEmpty(){
        //given
        Basket basket = getEmptyBasket();
        Product product = thereisProduct("lego-2137");
        //when
        basket.add(product);
        //then
        assertThat(basket.isEmpty())
                .isFalse();
    }
    @Test
    public void itShowsProcutsCount(){
        //given
        Basket basket = getEmptyBasket();
        Product product = thereisProduct("lego-2137");
        //when
        basket.add(product);
        //then
        assertThat(basket.isEmpty())
                .isFalse();
        assertThat(basket.getProductCount())
                .isEqualTo(2);
    }
    @Test
    public void itIncrementQuantityWhenAddedTwice(){
        //given
        Basket basket = getEmptyBasket();
        Product product1 = thereisProduct(PRODUCT_1);
        Product product2 = thereisProduct(PRODUCT_2);
        //when
        basket.add(product1);
        basket.add(product1);
        basket.add(product2);
        //then
        assertThat(basket.getProductCount())
                .isEqualTo(1);
        basketContainsProductWithQuantity(basket, product1);
    }

    private void basketContainsProductWithQuantity(Basket basket, Product product1) {
        assertThat(basket.getBasketItems())
                .filteredOn(basketLine -> basketLine.getProductId().equals(product1.getId()))
                .extracting(basketLine -> basketLine.getQuantity())
                .first()
                .isEqualTo(2);
    }

    private Product thereisProduct(String description) {
        Product product = new Product(UUID.randomUUID());
        product.setDescription(description);
        return product;
    }
}
