package lab2.objectpool;

public class PackagingBoxPool {
    private final ObjectPool<PackagingBox> pool;
    private final String defaultSize;

    public PackagingBoxPool(int maxSize, String defaultSize) {
        this.defaultSize = defaultSize;
        this.pool = new ObjectPool<>(maxSize, () -> new PackagingBox(defaultSize));
    }

    public PackagingBox acquire() {
        PackagingBox box = pool.acquire();
        box.reset();
        box.setSize(defaultSize);
        return box;
    }

    public void release(PackagingBox box) {
        box.reset();
        pool.release(box);
    }

    public int available() { return pool.available(); }
}
