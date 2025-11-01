package lab2.objectpool;

import java.util.UUID;

public class PackagingBox {
    private final String id = UUID.randomUUID().toString();
    private String size; // S, M, L
    private String label;

    public PackagingBox(String size) {
        this.size = size;
    }

    public String getId() { return id; }
    public String getSize() { return size; }
    public String getLabel() { return label; }

    public void setSize(String size) { this.size = size; }
    public void setLabel(String label) { this.label = label; }

    public void reset() {
        this.label = null;
    }

    @Override
    public String toString() {
        return "Box{" + size + ", id=" + id.substring(0, 8) + ", label='" + label + "'}";
    }
}
