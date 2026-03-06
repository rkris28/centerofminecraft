package com.centerofminecraft;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;

public final class ModBlocks {
	private static final List<Item> BUILDING_BLOCK_TAB_ITEMS = new ArrayList<>();

	private static final RegisteredBlock NESSIE = registerBlockWithItem(
			"nessie",
			BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(1.5F, 6.0F).requiresCorrectToolForDrops()
	);
	public static final Block NESSIE_BLOCK = NESSIE.block();
	public static final Item NESSIE_ITEM = NESSIE.item();

	private ModBlocks() {
	}

	public static void initialize() {
		// Intentionally empty. Static init handles registration.
	}

	public static void addToBuildingBlocksTab(FabricItemGroupEntries entries) {
		BUILDING_BLOCK_TAB_ITEMS.forEach(entries::accept);
	}

	private static RegisteredBlock registerBlockWithItem(String path, BlockBehaviour.Properties properties) {
		Identifier id = Identifier.fromNamespaceAndPath(Centerofminecraft.MOD_ID, path);
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

		Block block = Registry.register(
				BuiltInRegistries.BLOCK,
				id,
				new Block(properties.setId(blockKey))
		);

		Item item = Registry.register(
				BuiltInRegistries.ITEM,
				id,
				new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix())
		);
		BUILDING_BLOCK_TAB_ITEMS.add(item);
		return new RegisteredBlock(block, item);
	}

	private record RegisteredBlock(Block block, Item item) {
	}
}
