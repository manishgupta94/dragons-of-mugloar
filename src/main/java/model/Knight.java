package model;

import org.javatuples.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knight {

    private String name;
    private int attack;
    private int armor;
    private int agility;
    private int endurance;

    public Knight() {}

    public Knight(int attack, int armor, int agility, int endurance) {
        this.attack = attack;
        this.armor = armor;
        this.agility = agility;
        this.endurance = endurance;
        if (sumOfAbilities() != 20) {
            throw new IllegalArgumentException("Sum is " + sumOfAbilities() + "Not 20");
        }
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getEndurance() {
        return this.endurance;
    }

    public Map<String, Integer> getAbilityIndexMapDragon() {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("scaleThickness", this.attack);
        map.put("clawSharpness", this.armor);
        map.put("wingStrength", this.agility);
        map.put("fireBreath", this.endurance);
        return map;
    }

    public List<Pair<String, Integer>> getAbilityIndexPairsDragon() {
        return Arrays.asList(
            Pair.with("scaleThickness", this.attack),
            Pair.with("clawSharpness", this.armor),
            Pair.with("wingStrength", this.agility),
            Pair.with("fireBreath", this.endurance)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Knight knight = (Knight) o;

        if (attack != knight.attack)
            return false;
        if (armor != knight.armor)
            return false;
        if (agility != knight.agility)
            return false;
        return endurance == knight.endurance;
    }

    @Override
    public int hashCode() {
        int result = attack;
        result = 31 * result + armor;
        result = 31 * result + agility;
        result = 31 * result + endurance;
        return result;
    }

    @Override
    public String toString() {
        return "Knight{" +
            "name='" + name + '\'' +
            ", attack=" + attack +
            ", armor=" + armor +
            ", agility=" + agility +
            ", endurance=" + endurance +
            '}';
    }

    private int sumOfAbilities() {
        return attack + armor + agility + endurance;
    }

}
