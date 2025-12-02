package lab4.chain;

public class HardwareSpecialist extends SupportHandler {
    @Override
    public void handleRequest(String issue, int severity) {
        if (severity == 3) {
            System.out.println("Hardware Specialist: Handling complex hardware failure - " + issue);
        } else {
            System.out.println("Hardware Specialist: Issue too critical or unknown. Consult manager.");
        }
    }
}
