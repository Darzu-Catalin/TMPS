package lab3.facade;

public class HardDrive {
    public String read(long lba, int size) {
        System.out.println("HardDrive: Reading " + size + " bytes from sector " + lba);
        return "BOOT_SECTOR_DATA";
    }
}
