package lab2.factorymethod;

import lab2.builder.PC;

public class Laptop implements Product {
    private final String name;
    private final PC pc;

    public Laptop(String name, PC pc) {
        this.name = name;
        this.pc = pc;
    }

    @Override
    public String name() { return name; }

    @Override
    public String type() { return "Laptop"; }

    @Override
    public PC specification() { return pc; }

    @Override
    public String toString() {
        return type() + "[" + name + "] => " + pc;
    }
}
