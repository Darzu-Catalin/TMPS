package lab2.abstractfactory;

import lab2.prototype.CPU;
import lab2.prototype.GPU;
import lab2.prototype.RAM;

public class GamingFactory implements ComponentFactory {
    @Override
    public CPU createCPU() {
        return new CPU("Ryzen 9 7950X", 4.5, 16);
    }

    @Override
    public GPU createGPU() {
        return new GPU("RTX 4090", 24);
    }

    @Override
    public RAM createRAM() {
        return new RAM("DDR5", 32, 6000);
    }

    @Override
    public String getLabel() {
        return "Gaming";
    }
}
