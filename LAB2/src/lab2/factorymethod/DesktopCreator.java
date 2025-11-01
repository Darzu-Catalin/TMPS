package lab2.factorymethod;

import lab2.abstractfactory.ComponentFactory;
import lab2.builder.PC;

public class DesktopCreator extends ProductCreator {
    public DesktopCreator(ComponentFactory components) {
        super(components);
    }

    @Override
    protected Product create(String name, PC spec) {
        // tweak for desktop form-factor
        PC adjusted = new PC.Builder()
                .name(spec.getName() + " - Tower")
                .cpu(spec.getCpu())
                .gpu(spec.getGpu())
                .ram(spec.getRam())
                .storage("2TB NVMe SSD")
                .powerSupply("850W Platinum")
                .caseType("ATX Full Tower")
                .wifi(spec.hasWifi())
                .operatingSystem(spec.getOperatingSystem())
                .addExtra("Extra Case Fans")
                .build();
        return new Desktop(name, adjusted);
    }
}
