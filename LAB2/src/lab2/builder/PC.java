package lab2.builder;

import lab2.prototype.CPU;
import lab2.prototype.GPU;
import lab2.prototype.RAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PC {
    private final String name;
    private final CPU cpu;
    private final GPU gpu;
    private final RAM ram;
    private final String storage;
    private final String powerSupply;
    private final String caseType;
    private final boolean wifi;
    private final String operatingSystem;
    private final List<String> extras;

    private PC(Builder b) {
        this.name = b.name;
        this.cpu = b.cpu;
        this.gpu = b.gpu;
        this.ram = b.ram;
        this.storage = b.storage;
        this.powerSupply = b.powerSupply;
        this.caseType = b.caseType;
        this.wifi = b.wifi;
        this.operatingSystem = b.operatingSystem;
        this.extras = Collections.unmodifiableList(new ArrayList<>(b.extras));
    }

    public String getName() { return name; }
    public CPU getCpu() { return cpu; }
    public GPU getGpu() { return gpu; }
    public RAM getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getPowerSupply() { return powerSupply; }
    public String getCaseType() { return caseType; }
    public boolean hasWifi() { return wifi; }
    public String getOperatingSystem() { return operatingSystem; }
    public List<String> getExtras() { return extras; }

    @Override
    public String toString() {
        return "PC{" +
                "name='" + name + '\'' +
                ", cpu=" + cpu +
                ", gpu=" + gpu +
                ", ram=" + ram +
                ", storage='" + storage + '\'' +
                ", powerSupply='" + powerSupply + '\'' +
                ", caseType='" + caseType + '\'' +
                ", wifi=" + wifi +
                ", OS='" + operatingSystem + '\'' +
                ", extras=" + extras +
                '}';
    }

    public static class Builder {
        private String name = "Custom PC";
        private CPU cpu;
        private GPU gpu;
        private RAM ram;
        private String storage = "512GB SSD";
        private String powerSupply = "650W";
        private String caseType = "ATX Mid Tower";
        private boolean wifi = true;
        private String operatingSystem = "Ubuntu 24.04";
        private List<String> extras = new ArrayList<>();

        public Builder name(String name) { this.name = name; return this; }
        public Builder cpu(CPU cpu) { this.cpu = cpu; return this; }
        public Builder gpu(GPU gpu) { this.gpu = gpu; return this; }
        public Builder ram(RAM ram) { this.ram = ram; return this; }
        public Builder storage(String storage) { this.storage = storage; return this; }
        public Builder powerSupply(String powerSupply) { this.powerSupply = powerSupply; return this; }
        public Builder caseType(String caseType) { this.caseType = caseType; return this; }
        public Builder wifi(boolean wifi) { this.wifi = wifi; return this; }
        public Builder operatingSystem(String os) { this.operatingSystem = os; return this; }
        public Builder addExtra(String extra) { this.extras.add(extra); return this; }

        public PC build() {
            if (cpu == null || ram == null) {
                throw new IllegalStateException("CPU and RAM are required to build a PC");
            }
            return new PC(this);
        }
    }
}
