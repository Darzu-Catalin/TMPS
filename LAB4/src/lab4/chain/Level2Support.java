package lab4.chain;

public class Level2Support extends SupportHandler {
    @Override
    public void handleRequest(String issue, int severity) {
        if (severity == 2) {
            System.out.println("Level 2 Support: Handling advanced software issue - " + issue);
        } else if (nextHandler != null) {
            System.out.println("Level 2 Support: Cannot handle. Escalating...");
            nextHandler.handleRequest(issue, severity);
        } else {
            System.out.println("Level 2 Support: Cannot handle and no further support available.");
        }
    }
}
