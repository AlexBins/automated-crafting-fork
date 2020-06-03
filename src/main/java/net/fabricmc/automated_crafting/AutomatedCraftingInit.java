package net.fabricmc.automated_crafting;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.BlockContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AutomatedCraftingInit implements ModInitializer {
	// an instance of our new item
	public static final AutoCrafterBlock AUTO_CRAFTER = new AutoCrafterBlock(FabricBlockSettings.of(Material.ANVIL).build());
	public static BlockEntityType<AutoCrafterBlockEntity> AUTO_CRAFTER_ENTITY;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.BLOCK, AutoCrafterBlock.ID, AUTO_CRAFTER);
		Registry.register(Registry.ITEM, new Identifier("automated_crafting", "auto_crafter"), new BlockItem(AUTO_CRAFTER, new Item.Settings().group(ItemGroup.MISC)));
		AUTO_CRAFTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "automated_crafting:auto_crafter_entity", BlockEntityType.Builder.create(AutoCrafterBlockEntity::new, AUTO_CRAFTER).build(null));

		ContainerProviderRegistry.INSTANCE.registerFactory(AutoCrafterBlock.ID, (syncId, id, player, buf) -> new AutoCrafterController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

	}
}