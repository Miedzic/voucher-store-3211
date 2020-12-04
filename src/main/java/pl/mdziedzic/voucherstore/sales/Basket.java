package pl.mdziedzic.voucherstore.sales;

import pl.mdziedzic.voucherstore.productcatalog.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    private final Map<String,Product> products;
    private final Map<String,Integer> productsQuantities;

    public Basket() {
        this.products = new HashMap<>();
        this.productsQuantities = new HashMap<>();
    }
    public static Basket empty(){
        return new Basket();
    }
    public boolean isEmpty(){
     return products.isEmpty();
    }

    public void add(Product product) {
        products.put(product.getId(),product);
        if(!isContains(product)){
            putIntoBasket(product);
        } else{
            inscreaseQuantity(product);
        }
        productsQuantities.put(product.getId(),1);
    }

    private void putIntoBasket(Product product) {
        products.put(product.getId(),product);
        productsQuantities.put(product.getId(),1);
    }

    private Integer inscreaseQuantity(Product product) {
        return productsQuantities.put(product.getId(), productsQuantities.get(product.getId())+1);
    }

    private boolean isContains(Product product) {
        return productsQuantities.containsKey(product.getId());
    }

    public int getProductCount() {
        return products.size();
    }

    public List<BasketLine> getBasketItems() {
        return productsQuantities
                .entrySet()
                .stream()
                .map(es -> new BasketLine(es.getKey(), es.getValue()))
                .collect(Collectors.toUnmodifiableList());
    }
}
