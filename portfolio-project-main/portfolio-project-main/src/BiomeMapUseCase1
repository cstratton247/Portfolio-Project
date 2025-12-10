import components.biomemap.BiomeMap;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates generating a biome map from a height map and printing it.
 */
public class BiomeMapUseCase1 {

    public static void main(String[] args) {
        // Create a BiomeMap instance
        BiomeMap map = new BiomeMap1L(5, 5);

        // Create a simple height map using an anonymous class
        BiomeMap.HeightMap heightMap = new BiomeMap.HeightMap() {
            @Override
            public int getHeight(int x, int y) {
                return (x + y) % 3; // simple height pattern
            }

            @Override
            public int getWidth() {
                return 5;
            }

            @Override
            public int getHeightCount() {
                return 5;
            }
        };

        // Generate biomes from height map
        map.generateFromHeightMap(heightMap);

        // Print the map
        try (SimpleWriter out = new SimpleWriter1L(System.out)) {
            out.println("Biome map generated from height map:");
            map.printMap(out);
        }
    }
}
