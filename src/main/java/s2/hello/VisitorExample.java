package s2.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by russl on 11/24/2016.
 */
public class VisitorExample {

    public interface Product {
        void accept(ProductVisitor v);
    }

    public interface ProductVisitor {
        void visit(BookProduct p);

        void visit(GenericProduct p);
    }

    public class BookProduct implements Product {
        double retailCost = 10.0;

        public double getRetailCost() {
            return retailCost;
        }

        public void setRetailCost(double retailCost) {
            this.retailCost = retailCost;
        }

        @Override
        public void accept(ProductVisitor v) {
            v.visit(this);
        }
    }


    public class GenericProduct implements Product {
        public double getRetailCost() {
            return retailCost;
        }

        public void setRetailCost(double retailCost) {
            this.retailCost = retailCost;
        }

        double retailCost = 5.0;

        @Override
        public void accept(ProductVisitor v) {
            v.visit(this);
        }
    }


    public class PriceVisitor implements ProductVisitor {
        public double getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(double sumPrice) {
            this.sumPrice = sumPrice;
        }

        double sumPrice = 0;

        public void visit(BookProduct p) {
            sumPrice += p.getRetailCost() * taxRateMap.get(BookProduct.class);
        }

        public void visit(GenericProduct p) {
            sumPrice += p.getRetailCost() * taxRateMap.get(GenericProduct.class);
        }

        Map<Class, Double> taxRateMap = new HashMap<>();

        public PriceVisitor(Map<Class, Double> taxRateMap) {
            this.taxRateMap = taxRateMap;
        }
    }

    public void perform() {
        List<Product> productList = new ArrayList<>();
        productList.add(new BookProduct());
        productList.add(new GenericProduct());

        Map<Class, Double> countyTaxRateMap = new HashMap<>();
        countyTaxRateMap.put(BookProduct.class, 1.0);
        countyTaxRateMap.put(GenericProduct.class, 1.2);

        Map<Class, Double> cityTaxRateMap = new HashMap<>();
        cityTaxRateMap.put(BookProduct.class, 1.1);
        cityTaxRateMap.put(GenericProduct.class, 1.2);

        PriceVisitor cityPriceVisitor = new PriceVisitor(cityTaxRateMap);
        productList.forEach(p -> p.accept(cityPriceVisitor));

        System.out.println("total city price:  " + cityPriceVisitor.getSumPrice());

        PriceVisitor countyPriceVisitor = new PriceVisitor(countyTaxRateMap);
        productList.forEach(p -> p.accept(countyPriceVisitor));

        System.out.println("total county price:  " + countyPriceVisitor.getSumPrice());
    }

    public static void main(String[] args) {
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.perform();
    }
}