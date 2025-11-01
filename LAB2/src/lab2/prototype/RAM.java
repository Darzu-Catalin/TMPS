package lab2.prototype;

public class RAM implements Component {
    private String type; // e.g., DDR5
    private int sizeGB;  // e.g., 32
    private int speedMHz; // e.g., 6000

    public RAM(String type, int sizeGB, int speedMHz) {
        this.type = type;
        this.sizeGB = sizeGB;
        this.speedMHz = speedMHz;
    }

    public String getType() { return type; }
    public int getSizeGB() { return sizeGB; }
    public int getSpeedMHz() { return speedMHz; }

    public void setType(String type) { this.type = type; }

    @Override
    public RAM copy() {
        return new RAM(type, sizeGB, speedMHz);
    }

    @Override
    public String toString() {
        return String.format("RAM{%s, %dGB, %dMHz}", type, sizeGB, speedMHz);
    }
}
