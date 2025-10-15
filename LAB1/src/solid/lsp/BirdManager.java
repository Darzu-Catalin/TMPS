package solid.lsp;

import java.util.List;

/**
 * BirdManager class - Manages bird operations
 * Liskov Substitution Principle: Works with any Bird subclass without knowing the specific type
 */
public class BirdManager {

    public void feedAllBirds(List<Bird> birds) {
        System.out.println("Feeding all birds:");
        for (Bird bird : birds) {
            bird.eat(); // Works with any Bird subclass
        }
        System.out.println();
    }

    public void makeBirdsSleep(List<Bird> birds) {
        System.out.println("Making all birds sleep:");
        for (Bird bird : birds) {
            bird.sleep(); // Works with any Bird subclass
        }
        System.out.println();
    }

    public void makeBirdsSounds(List<Bird> birds) {
        System.out.println("Birds making sounds:");
        for (Bird bird : birds) {
            bird.makeSound(); // Works with any Bird subclass
        }
        System.out.println();
    }

    public void makeFlyingBirdsFly(List<Bird> birds) {
        System.out.println("Flying birds taking flight:");
        for (Bird bird : birds) {
            if (bird instanceof FlyingBird) {
                FlyingBird flyingBird = (FlyingBird) bird;
                flyingBird.fly();
            } else {
                System.out.println(bird.getName() + " cannot fly");
            }
        }
        System.out.println();
    }

    public void printBirdInfo(List<Bird> birds) {
        System.out.println("Bird Information:");
        for (Bird bird : birds) {
            System.out.println(bird.toString());
            if (bird instanceof FlyingBird) {
                FlyingBird flyingBird = (FlyingBird) bird;
                System.out.println("  - Max flight altitude: " + flyingBird.getMaxFlightAltitude() + " meters");
            } else {
                System.out.println("  - This bird cannot fly");
            }
        }
        System.out.println();
    }
}