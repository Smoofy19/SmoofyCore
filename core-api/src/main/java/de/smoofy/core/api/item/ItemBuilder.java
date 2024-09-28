package de.smoofy.core.api.item;

/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 27.09.24, 22:55
 */

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    private Consumer<InventoryClickEvent> consumer;

    private ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
        this.itemMeta = this.itemStack.getItemMeta();
    }

    private ItemBuilder(ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        this(itemStack);
        this.consumer = consumer;
    }

    private ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    private ItemBuilder(Material material, Consumer<InventoryClickEvent> consumer) {
        this(material);
        this.consumer = consumer;
    }

    public static ItemBuilder of(@NotNull ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public static ItemBuilder of(@NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer) {
        return new ItemBuilder(itemStack, consumer);
    }

    public static ItemBuilder of(@NotNull Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder of(@NotNull Material material, @NotNull Consumer<InventoryClickEvent> consumer) {
        return new ItemBuilder(material, consumer);
    }

    public ItemBuilder name(@NotNull Component name) {
        this.itemMeta.displayName(name);
        return this;
    }

    public ItemBuilder noName() {
        this.itemMeta.displayName(Component.text(" "));
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder durability(int damage) {
        if (this.itemMeta instanceof Damageable damageable) {
            damageable.setDamage(damage);
            this.itemStack.setItemMeta(damageable);
        }
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder unbreakable() {
        this.itemMeta.setUnbreakable(true);
        return this;
    }

    public ItemBuilder enchant(int level, @NotNull Enchantment... enchantments) {
        Arrays.stream(enchantments).forEach(enchantment -> this.itemStack.addEnchantment(enchantment, level));
        return this;
    }

    public ItemBuilder enchantUnsafe(int level, @NotNull Enchantment... enchantments) {
        Arrays.stream(enchantments).forEach(enchantment -> this.itemStack.addUnsafeEnchantment(enchantment, level));
        return this;
    }

    public ItemBuilder lore(@NotNull Component... lore) {
        this.itemMeta.lore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder lore(List<Component> lore) {
        this.itemMeta.lore(lore);
        return this;
    }

    public ItemBuilder addLoreLine(@NotNull Component line, int pos) {
        if (this.itemMeta.lore() == null || !this.itemMeta.hasLore()) {
            return this;
        }
        var lore = Lists.newArrayList(Objects.requireNonNull(this.itemMeta.lore()));
        lore.set(pos, line);
        this.itemMeta.lore(lore);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

}