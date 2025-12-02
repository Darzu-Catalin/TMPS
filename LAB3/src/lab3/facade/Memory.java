package lab3.facade;

public class Memory {
    public void load(long position, String data) {
        System.out.println("Memory: Loading '" + data + "' at position " + position);
    }
}
