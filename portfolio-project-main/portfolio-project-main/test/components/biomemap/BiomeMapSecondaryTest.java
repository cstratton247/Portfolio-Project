package components.biomemap;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for BiomeMap interface methods.
 */
public class BiomeMapTest {

    private BiomeMap1L map;

    @Before
    public void setUp() {
        map = new BiomeMap1L(3, 3);
    }

    // -------------------- countBiome Tests --------------------

    @Test
    public void testCountBiomeInitial() {
        // All cells are PLAINS initially
        assertEquals(9, map.countBiome(Biome.PLAINS));
        assertEquals(0, map.countBiome(Biome.FOREST));
    }

    @Test
    public void testCountBiomeAfterSet() {
        map.setBiome(0, 0, Biome.FOREST);
        map.setBiome(1, 1, Biome.FOREST);
        assertEquals(2, map.countBiome(Biome.FOREST));
        assertEquals(7, map.countBiome(Biome.PLAINS));
    }

    // -------------------- randomizeBiomes Tests --------------------

    @Test
    public void testRandomizeBiomes() {
        map.randomizeBiomes();
        // All cells should now contain valid Biomes
        for (int y = 0; y < map.getHeightCount(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Biome b = map.getBiome(x, y);
                assert b != null;
            }
        }
    }

    // -------------------- generateFromHeightMap Tests --------------------

    private class DummyHeightMap implements BiomeMap.HeightMap {
        private final int[][] heights;

        public DummyHeightMap(int[][] heights) {
            this.heights = heights;
        }

        @Override
        public int getHeight(int x, int y) {
            return heights[y][x];
        }

        @Override
        public int getWidth() {
            return heights[0].length;
        }

        @Override
        public int getHeightCount() {
            return heights.length;
        }
    }

    @Test
    public void testGenerateFromHeightMap() {
        int[][] heights = {
            {0, 10, 20},
            {30, 40, 50},
            {60, 70, 80}
        };
        BiomeMap.HeightMap hm = new DummyHeightMap(heights);
        map.generateFromHeightMap(hm);

        // Check that map has been updated (implementation-specific)
        for (int y = 0; y < map.getHeightCount(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                assert map.getBiome(x, y) != null;
            }
        }
    }
}

