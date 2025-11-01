package lab2.abstractfactory;

import lab2.prototype.CPU;
import lab2.prototype.GPU;
import lab2.prototype.RAM;

public class OfficeFactory implements ComponentFactory {
    @Override
    public CPU createCPU() {
        return new CPU("Intel i5-14400", 2.5, 10);
    }

    @Override
    public GPU createGPU() {
        return new GPU("Integrated UHD", 1);
    }

    @Override
    public RAM createRAM() {
        return new RAM("DDR4", 16, 3200);
    }

    @Override
    public String getLabel() {
        return "Office";
    }
}
