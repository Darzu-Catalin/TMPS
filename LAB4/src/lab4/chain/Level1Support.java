package lab4.chain;

public class Level1Support extends SupportHandler {
    @Override
    public void handleRequest(String issue, int severity) {
        if (severity == 1) {
            System.out.println("Level 1 Support: Handling basic issue - " + issue);
        } else if (nextHandler != null) {
            System.out.println("Level 1 Support: Cannot handle. Escalating...");
            nextHandler.handleRequest(issue, severity);
        } else {
            System.out.println("Level 1 Support: Cannot handle and no further support available.");
        }
    }
}
