package game.service;

import model.Dragon;
import model.Knight;

public class KnightDragonFactory {

    public static Dragon droughtDragon() {
        return balancedDragon();
    }

    public static Dragon balancedDragon() {
        return new Dragon(5, 5, 5, 5);
    }

    public static Dragon rainDragon() {
        return new Dragon(5, 10, 5, 0);
    }

    public static Knight balancedKnight() {
        return new Knight(5, 5, 5, 5);
    }

    public static Dragon balancedKnightsDragon() {
        return new Dragon(7, 5, 4, 4);
    }

}
