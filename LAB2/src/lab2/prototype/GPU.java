package lab2.prototype;

public class GPU implements Component {
    private String model;
    private int vramGB;

    public GPU(String model, int vramGB) {
        this.model = model;
        this.vramGB = vramGB;
    }

    public String getModel() { return model; }
    public int getVramGB() { return vramGB; }

    public void setModel(String model) { this.model = model; }

    @Override
    public GPU copy() {
        return new GPU(model, vramGB);
    }

    @Override
    public String toString() {
        return String.format("GPU{%s, %dGB}", model, vramGB);
    }
}
