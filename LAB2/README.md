# LAB2 — Creational Design Patterns (Java)

Domain: PC Assembly Workshop

Patterns implemented (no frameworks/libs):
- Singleton — global configuration (`lab2.singleton.Config`)
- Builder — assemble immutable PC specs (`lab2.builder.PC`)
- Prototype — clone base components (`lab2.prototype.*`)
- Factory Method — create product types (`lab2.factorymethod.*`)
- Abstract Factory — component families (`lab2.abstractfactory.*`)
- Object Pool — reusable packaging boxes (`lab2.objectpool.*`)

## How to run

From the repo root (macOS, zsh):

```sh
# Compile
mkdir -p LAB2/bin
find LAB2/src -name "*.java" -print0 | xargs -0 javac -d LAB2/bin

# Run
java -cp LAB2/bin lab2.LAB2App
```

## What you’ll see
- Singleton prints environment/currency.
- Factory Method builds a Gaming laptop and an Office desktop using Abstract Factory components.
- Builder assembles a custom workstation PC.
- Prototype clones CPU/GPU/RAM templates and customizes models/types.
- Object Pool acquires/releases packaging boxes and shows reuse.

## Notes
- Pure Java 17+ code (no external dependencies).
- Each pattern is isolated in its own package and stitched together in `LAB2App`.
