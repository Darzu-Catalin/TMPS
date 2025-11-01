package lab2.prototype;

public class CPU implements Component {
    private String model;
    private double GHz;
    private int cores;

    public CPU(String model, double GHz, int cores) {
        this.model = model;
        this.GHz = GHz;
        this.cores = cores;
    }

    public String getModel() { return model; }
    public double getGHz() { return GHz; }
    public int getCores() { return cores; }

    public void setModel(String model) { this.model = model; }

    @Override
    public CPU copy() {
        return new CPU(model, GHz, cores);
    }

    @Override
    public String toString() {
        return String.format("CPU{%s, %.1fGHz, %d cores}", model, GHz, cores);
    }
}
