package lab2.abstractfactory;

import lab2.prototype.CPU;
import lab2.prototype.GPU;
import lab2.prototype.RAM;

public interface ComponentFactory {
    CPU createCPU();
    GPU createGPU();
    RAM createRAM();
    String getLabel();
}
