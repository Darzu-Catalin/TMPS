package lab2.singleton;

public final class Config {
    private String currency = "USD";
    private String environment = "dev";

    private Config() {}

    // Initialization-on-demand holder idiom
    private static class Holder {
        private static final Config INSTANCE = new Config();
    }

    public static Config getInstance() {
        return Holder.INSTANCE;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "Config{" +
                "currency='" + currency + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }
}
