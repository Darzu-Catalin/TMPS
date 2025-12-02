package lab3.facade;

public class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        System.out.println("ComputerFacade: Starting computer...");
        cpu.freeze();
        String bootData = hardDrive.read(0, 1024);
        memory.load(0, bootData);
        cpu.jump(0);
        cpu.execute();
        System.out.println("ComputerFacade: Computer started successfully.");
    }
}
