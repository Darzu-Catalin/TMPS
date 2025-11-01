package lab2.factorymethod;

import lab2.builder.PC;

public interface Product {
    String name();
    String type();
    PC specification();
}
