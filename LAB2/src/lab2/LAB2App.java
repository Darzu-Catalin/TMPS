package lab2;

import lab2.abstractfactory.ComponentFactory;
import lab2.abstractfactory.GamingFactory;
import lab2.abstractfactory.OfficeFactory;
import lab2.builder.PC;
import lab2.factorymethod.DesktopCreator;
import lab2.factorymethod.LaptopCreator;
import lab2.factorymethod.Product;
import lab2.factorymethod.ProductCreator;
import lab2.objectpool.PackagingBox;
import lab2.objectpool.PackagingBoxPool;
import lab2.prototype.CPU;
import lab2.prototype.GPU;
import lab2.prototype.RAM;
import lab2.singleton.Config;

public class LAB2App {
    public static void main(String[] args) {
        System.out.println("=== LAB2: Creational Design Patterns Demo (PC Assembly Workshop) ===\n");

        // 1) Singleton: Global configuration
        Config config = Config.getInstance();
        config.setCurrency("EUR");
        config.setEnvironment("prod");
        System.out.println("[Singleton] Config: " + config);

        // Factories for families of components (Abstract Factory)
        ComponentFactory gaming = new GamingFactory();
        ComponentFactory office = new OfficeFactory();

        // 2) Factory Method: Different product creators using the same order flow
        ProductCreator laptopMaker = new LaptopCreator(gaming);
        ProductCreator desktopMaker = new DesktopCreator(office);
        Product laptop = laptopMaker.orderProduct("Gaming Beast 15");
        Product desktop = desktopMaker.orderProduct("Office Buddy Pro");
        System.out.println("[Factory Method] Created: \n  - " + laptop + "\n  - " + desktop + "\n");

        // 3) Builder: Manual custom PC assembly with optional parts
        PC custom = new PC.Builder()
                .name("Creator's Dream")
                .cpu(gaming.createCPU())
                .gpu(gaming.createGPU())
                .ram(new RAM("DDR5", 64, 6400))
                .storage("4TB NVMe + 4TB HDD")
                .powerSupply("1000W Platinum")
                .caseType("E-ATX Workstation Tower")
                .wifi(true)
                .operatingSystem("Ubuntu 24.04 LTS")
                .addExtra("10Gb Ethernet Card")
                .addExtra("Quiet Fan Kit")
                .build();
        System.out.println("[Builder] Custom PC => " + custom + "\n");

        // 4) Prototype: Clone and customize base components
        CPU baseCPU = new CPU("Ryzen 7 Template", 3.8, 8);
        CPU clone1 = baseCPU.copy(); clone1.setModel("Ryzen 7 - Bin A");
        CPU clone2 = baseCPU.copy(); clone2.setModel("Ryzen 7 - Bin B");
        GPU baseGPU = new GPU("RTX 4070 Template", 12);
        GPU cloneGPU = baseGPU.copy(); cloneGPU.setModel("RTX 4070 OC");
        RAM baseRAM = new RAM("DDR5", 32, 5600);
        RAM cloneRAM = baseRAM.copy(); cloneRAM.setType("DDR5 (low-profile)");
        System.out.println("[Prototype] Cloned components: \n  - " + clone1 + "\n  - " + clone2 + "\n  - " + cloneGPU + "\n  - " + cloneRAM + "\n");

        // 5) Object Pool: Reuse packaging boxes to reduce allocations
        PackagingBoxPool boxPool = new PackagingBoxPool(2, "L");
        PackagingBox b1 = boxPool.acquire(); b1.setLabel(laptop.name());
        PackagingBox b2 = boxPool.acquire(); b2.setLabel(desktop.name());
        System.out.println("[Object Pool] Acquired boxes: " + b1 + ", " + b2 + "); available=" + boxPool.available());
        // release one and acquire again to demonstrate reuse
        boxPool.release(b1);
        PackagingBox b3 = boxPool.acquire(); b3.setLabel(custom.getName());
        System.out.println("[Object Pool] After reuse: " + b3 + "; available=" + boxPool.available());

        System.out.println("\n=== Demo complete ===");
    }
}
