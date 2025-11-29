package net.profinoob.gamblingPlugin.utils;

import org.bukkit.Material;

public class ItemExchange {

    private Material material;
    private int value;

    // Constructors
    public ItemExchange(Material material,int value) {
        if (material == null) throw new IllegalArgumentException("Material cannot be null!");
        if (value < 0) throw new IllegalArgumentException("Value must be greater than or equal to 0!");
        this.material = material;
        this.value = value;
    }

    // Getters
    public Material getMaterial() {
        return material;
    }

    public int getValue() {
        return value;
    }

    // Overrides
    @Override
    public String toString() {
        return "ItemExchange{" + "material=" + material + ", value=" + value + '}';
    }
}
