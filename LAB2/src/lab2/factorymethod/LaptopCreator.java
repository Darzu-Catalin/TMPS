package lab2.factorymethod;

import lab2.abstractfactory.ComponentFactory;
import lab2.builder.PC;

public class LaptopCreator extends ProductCreator {
    public LaptopCreator(ComponentFactory components) {
        super(components);
    }

    @Override
    protected Product create(String name, PC spec) {
        // tweak for laptop form-factor
        PC adjusted = new PC.Builder()
                .name(spec.getName() + " - 15" + '"')
                .cpu(spec.getCpu())
                .gpu(spec.getGpu())
                .ram(spec.getRam())
                .storage("1TB NVMe SSD (Laptop)")
                .powerSupply("240W Adapter")
                .caseType("Slim Laptop Chassis")
                .wifi(true)
                .operatingSystem(spec.getOperatingSystem())
                .build();
        return new Laptop(name, adjusted);
    }
}
