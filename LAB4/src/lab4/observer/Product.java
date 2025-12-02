package lab4.observer;

import java.util.ArrayList;
import java.util.List;

public class Product implements Subject {
    private String productName;
    private boolean inStock;
    private List<Observer> observers;

    public Product(String productName) {
        this.productName = productName;
        this.inStock = false;
        this.observers = new ArrayList<>();
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
        if (inStock) {
            notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Product '" + productName + "' is back in stock!");
        }
    }
}
