package lab3.adapter;

// Adaptee
public class VGAMonitor {
    public void showVGASignal(String analogSignal) {
        System.out.println("VGA Monitor: Displaying analog signal -> " + analogSignal);
    }
}
