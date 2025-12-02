# LAB4 — Behavioral Design Patterns

**Domain:** PC Support & Sales System

This laboratory work demonstrates **3 Behavioral Design Patterns** in Java:

1. **Strategy Pattern**: Used for dynamic pricing calculation (e.g., different discount strategies).
2. **Observer Pattern**: Used for notifying customers about product availability (Back-in-Stock notifications).
3. **Chain of Responsibility Pattern**: Used for a technical support ticketing system (Level 1 -> Level 2 -> Hardware Specialist).

## Project Structure

```
LAB4/
├── src/
│   └── lab4/
│       ├── Main.java
│       ├── strategy/
│       │   ├── PricingStrategy.java
│       │   ├── RegularPricing.java
│       │   ├── MemberDiscountPricing.java
│       │   └── SeasonalDiscountPricing.java
│       ├── observer/
│       │   ├── Observer.java
│       │   ├── Subject.java
│       │   ├── Customer.java
│       │   └── Product.java
│       └── chain/
│           ├── SupportHandler.java
│           ├── Level1Support.java
│           ├── Level2Support.java
│           └── HardwareSpecialist.java
└── README.md
```

## How to Run

```bash
# Compile
mkdir -p bin
javac -d bin src/lab4/**/*.java src/lab4/Main.java

# Run
java -cp bin lab4.Main
```
