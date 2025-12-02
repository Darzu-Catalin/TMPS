package lab3;

import lab3.adapter.HDMIInterface;
import lab3.adapter.VGAMonitor;
import lab3.adapter.VGAToHDMIAdapter;
import lab3.decorator.BasicComputer;
import lab3.decorator.Computer;
import lab3.decorator.ExtraRAMDecorator;
import lab3.decorator.RGBLightsDecorator;
import lab3.facade.ComputerFacade;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LAB3: Structural Design Patterns Demo ===\n");

        // --- 1. Adapter Pattern Demo ---
        System.out.println("--- 1. Adapter Pattern: VGA to HDMI ---");
        VGAMonitor oldMonitor = new VGAMonitor();
        HDMIInterface hdmiAdapter = new VGAToHDMIAdapter(oldMonitor);
        
        // The computer sends a digital HDMI signal, but the adapter makes it work with the VGA monitor
        hdmiAdapter.display("1080p Digital Video Signal");
        System.out.println();

        // --- 2. Decorator Pattern Demo ---
        System.out.println("--- 2. Decorator Pattern: Computer Configuration ---");
        Computer myPC = new BasicComputer();
        System.out.println("Base: " + myPC.getDescription() + " | Cost: $" + myPC.getCost());

        // Add Extra RAM
        myPC = new ExtraRAMDecorator(myPC);
        System.out.println("Upgrade 1: " + myPC.getDescription() + " | Cost: $" + myPC.getCost());

        // Add RGB Lights
        myPC = new RGBLightsDecorator(myPC);
        System.out.println("Upgrade 2: " + myPC.getDescription() + " | Cost: $" + myPC.getCost());
        System.out.println();

        // --- 3. Facade Pattern Demo ---
        System.out.println("--- 3. Facade Pattern: Computer Startup ---");
        ComputerFacade computer = new ComputerFacade();
        // Simple interface hides the complex interactions of CPU, Memory, and HDD
        computer.start();
    }
}
