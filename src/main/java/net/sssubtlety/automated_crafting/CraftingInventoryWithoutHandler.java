package net.sssubtlety.automated_crafting;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.util.collection.DefaultedList;
import net.sssubtlety.automated_crafting.mixin.CraftingInventoryAccessor;

public class CraftingInventoryWithoutHandler extends CraftingInventory {
    private static final CraftingScreenHandler dummyHandler = new CraftingScreenHandler(0, new PlayerInventory(null));

    public CraftingInventoryWithoutHandler(int width, int height) {
        super(dummyHandler, width, height);
    }

    public CraftingInventoryWithoutHandler(int width, int height, DefaultedList<ItemStack> contents) throws IllegalArgumentException {
        this(width, height);
        int size = this.size();
        if (contents.size() == size)
            ((CraftingInventoryAccessor)this).setStacks(contents);
//        else if (contents.size() > size) {
//            DefaultedList<ItemStack> truncatedContents = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
//
//            for (int i = 0; i < size; i++)
//                truncatedContents.set(i, contents.get(i));
//
//            ((CraftingInventoryAccessor) this).setStacks(truncatedContents);
//        }
        else
            throw new IllegalArgumentException("Trying to create CraftingInventoryWithoutHandler from list with size != width * height. ");
    }

    public DefaultedList<ItemStack> getInventorySubList(int start, int length) {
        if (start < 0) throw new IllegalArgumentException("Received negative start. ");
        if (length < 0) throw new IllegalArgumentException("Received negative length. ");
        if (start + length > this.size()) throw new IllegalArgumentException("start + length > inventory size. ");

        DefaultedList<ItemStack> subInventory = DefaultedList.ofSize(length, ItemStack.EMPTY);
        for (int i = 0; i < length; i++)
            subInventory.set(i, this.getInventory().get(i + start));

        return subInventory;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ((CraftingInventoryAccessor)this).getStacks().set(slot, stack);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(((CraftingInventoryAccessor)this).getStacks(), slot, amount);
    }


    public DefaultedList<ItemStack> getInventory() {
        return ((CraftingInventoryAccessor)this).getStacks();
    }

    public void setInventory(DefaultedList<ItemStack> newInventory) {
        ((CraftingInventoryAccessor)this).setStacks(newInventory);
    }
}
