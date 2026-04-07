package util;

import model.Pizza;
import model.PizzaOrderClass;
import java.util.Comparator;

public class PizzaComparator implements Comparator<Pizza> {

    @Override
    public int compare(Pizza p1, Pizza p2){
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}
    class PriceComparator implements Comparator<Pizza>{
        @Override
        public int compare(Pizza p1, Pizza p2) {
            return Double.compare(p1.getPrice(), p2.getPrice());
        }
    }

    class PizzaIdComparator implements Comparator<Pizza> {
        @Override
        public int compare(Pizza p1, Pizza p2){
            return Integer.compare(p1.getId(), p2.getId());
        }
    }

    class DescriptionComparator implements Comparator<Pizza> {
        @Override
        public int compare(Pizza p1, Pizza p2) {
            return p1.getDescription().compareToIgnoreCase(p2.getDescription());
        }
    }

    class PriceOrderComparator implements Comparator<PizzaOrderClass>{
    @Override
    public int compare(PizzaOrderClass p1, PizzaOrderClass p2) {
        return Double.compare(p1.getTotalPrice(), p2.getTotalPrice());
    }
}
