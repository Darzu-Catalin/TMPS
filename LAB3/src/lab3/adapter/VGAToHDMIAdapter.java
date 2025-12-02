package lab3.adapter;

// Adapter
public class VGAToHDMIAdapter implements HDMIInterface {
    private VGAMonitor vgaMonitor;

    public VGAToHDMIAdapter(VGAMonitor vgaMonitor) {
        this.vgaMonitor = vgaMonitor;
    }

    @Override
    public void display(String digitalSignal) {
        System.out.println("Adapter: Converting HDMI digital signal to VGA analog...");
        // Simulate conversion logic
        String analogSignal = "[Analog Version of " + digitalSignal + "]";
        vgaMonitor.showVGASignal(analogSignal);
    }
}
