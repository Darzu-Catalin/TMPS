package lab2.factorymethod;

import lab2.builder.PC;

public class Desktop implements Product {
    private final String name;
    private final PC pc;

    public Desktop(String name, PC pc) {
        this.name = name;
        this.pc = pc;
    }

    @Override
    public String name() { return name; }

    @Override
    public String type() { return "Desktop"; }

    @Override
    public PC specification() { return pc; }

    @Override
    public String toString() {
        return type() + "[" + name + "] => " + pc;
    }
}
