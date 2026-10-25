package dev.duchars.nolavapockets;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * Removes the hidden 1x1 lava pockets that are generated in the nether. Does not touch any other nether lava generation.
 */
public class NoLavaPockets implements ModInitializer {
	private static ResourceKey<PlacedFeature> vanilla(String path) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.withDefaultNamespace(path));
	}

	@Override
	public void onInitialize() {
		ResourceKey<PlacedFeature> closed = vanilla("spring_closed");
		ResourceKey<PlacedFeature> closedDouble = vanilla("spring_closed_double");
		BiomeModifications.create(Identifier.fromNamespaceAndPath("no_lava_pockets", "remove_closed_springs"))
			.add(ModificationPhase.REMOVALS, BiomeSelectors.foundInTheNether(), context -> {
				context.getGenerationSettings().removeFeature(closed);
				context.getGenerationSettings().removeFeature(closedDouble);
			});
	}
}
