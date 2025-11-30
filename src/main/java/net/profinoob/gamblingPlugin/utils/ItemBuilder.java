package net.profinoob.gamblingPlugin.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemMeta itemMeta;
    private ItemStack itemStack;

    // Constructors
    public ItemBuilder(Material mat) {
        itemStack = new ItemStack(mat);
        itemMeta = itemStack.getItemMeta();
    }

    // Methods
    public ItemBuilder setDisplayName(String s) {
        itemMeta.setDisplayName(s);
        return this;
    }

    public ItemBuilder setLocalizedName(String s) {
        itemMeta.setLocalizedName(s);
        return this;
    }

    public ItemBuilder setLore(String... l) {
        itemMeta.setLore(Arrays.asList(l));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean b) {
        itemMeta.setUnbreakable(b);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... f) {
        itemMeta.addItemFlags(f);
        return this;
    }

    // builds ItemBuilder object into ItemStack
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    // Overrides
    @Override
    public String toString() {
        return "ItemBuilder{" +
                "itemMeta=" + itemMeta +
                "itemStack=" + itemStack +
                "}";
    }
}
