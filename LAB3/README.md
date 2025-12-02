# LAB3 — Structural Design Patterns

**Domain:** Computer Systems & Peripherals

This laboratory work demonstrates **3 Structural Design Patterns** in Java:

1.  **Adapter Pattern**: Allows an old VGA Monitor to work with a modern HDMI Computer.
2.  **Decorator Pattern**: Dynamically adds features (like Extra RAM, RGB Lights) to a Computer object, calculating the total cost.
3.  **Facade Pattern**: Provides a simple interface to start a complex Computer system (handling CPU, Memory, and Hard Drive interactions).

## Project Structure

```
LAB3/
├── src/
│   └── lab3/
│       ├── Main.java
│       ├── adapter/
│       │   ├── HDMIInterface.java
│       │   ├── VGAMonitor.java
│       │   └── VGAToHDMIAdapter.java
│       ├── decorator/
│       │   ├── Computer.java
│       │   ├── BasicComputer.java
│       │   ├── ComputerDecorator.java
│       │   ├── ExtraRAMDecorator.java
│       │   └── RGBLightsDecorator.java
│       └── facade/
│           ├── CPU.java
│           ├── Memory.java
│           ├── HardDrive.java
│           └── ComputerFacade.java
└── README.md
```

## How to Run

```bash
# Compile
mkdir -p bin
javac -d bin src/lab3/**/*.java src/lab3/Main.java

# Run
java -cp bin lab3.Main
```
