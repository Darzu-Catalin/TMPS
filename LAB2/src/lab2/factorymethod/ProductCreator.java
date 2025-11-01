package lab2.factorymethod;

import lab2.abstractfactory.ComponentFactory;
import lab2.builder.PC;

public abstract class ProductCreator {
    protected final ComponentFactory components;

    public ProductCreator(ComponentFactory components) {
        this.components = components;
    }

    public Product orderProduct(String name) {
        // Common steps could be placed here: logging, QA, packaging, etc.
        PC spec = assembleBaseSpec(name);
        return create(name, spec);
    }

    protected PC assembleBaseSpec(String name) {
        return new PC.Builder()
                .name(name + " (" + components.getLabel() + ")")
                .cpu(components.createCPU())
                .gpu(components.createGPU())
                .ram(components.createRAM())
                .storage("1TB NVMe SSD")
                .powerSupply("750W Gold")
                .caseType("ATX Mid Tower")
                .wifi(true)
                .operatingSystem("Windows 11 Pro")
                .addExtra("Bluetooth 5.3")
                .build();
    }

    protected abstract Product create(String name, PC spec);
}
