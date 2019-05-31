package pl.mypocket.Logic;

import pl.mypocket.model.Product;

import java.util.List;

public class Calc {

    public static double sumAllKcal(List<Product> products){
        return products.stream().mapToDouble(Product::getCalories).sum();
    }
}
