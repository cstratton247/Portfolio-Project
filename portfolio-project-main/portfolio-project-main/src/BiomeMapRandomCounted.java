import components.biomemap.BiomeMap;
import components.biomemap.Biome;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates randomizing a biome map and counting a specific biome.
 */
public class BiomeMapRandomCounted {

    public static void main(String[] args) {
        // Create a 6x6 BiomeMap
        BiomeMap map = new BiomeMap1L(6, 6);

        // Randomize all biomes
        map.randomizeBiomes();

        // Count how many PLAINS are in the map
        int plainsCount = map.countBiome(Biome.PLAINS);

        // Print results
        try (SimpleWriter out = new SimpleWriter1L(System.out)) {
            out.println("Randomized Biome Map:");
            map.printMap(out);
            out.println("Number of PLAINS cells: " + plainsCount);
        }
    }
}
