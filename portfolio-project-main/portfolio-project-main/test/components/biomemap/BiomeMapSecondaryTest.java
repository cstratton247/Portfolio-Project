package components.biomemap;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test fixture for {@code BiomeMapSecondary}.
 * Tests abstract-class methods that rely on kernel methods.
 */
public class BiomeMapSecondaryTest {

    private BiomeMap map;

    @Before
    public void setUp() {
        map = new BiomeMap1L(3, 3); // use kernel implementation to test secondary logic
    }

    @Test
    public void testCountBiome() {
        map.setBiome(0, 0, Biome.FOREST);
        map.setBiome(1, 0, Biome.FOREST);
        map.setBiome(2, 0, Biome.DESERT);
        assertEquals(2, map.countBiome(Biome.FOREST));
        assertEquals(1, map.countBiome(Biome.DESERT));
        assertEquals(6, map.countBiome(Biome.PLAINS)); // remaining cells
    }

    @Test
    public void testRandomizeBiomes() {
        map.randomizeBiomes();
        // just verify no cell is null
        for (int y = 0; y < map.getHeightCount(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                assertEquals(true, map.getBiome(x, y) != null);
            }
        }
    }

    @Test
    public void testGenerateFromHeightMap() {
        final int[][] heights = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
        };

        BiomeMap.HeightMap hMap = new BiomeMap.HeightMap() {

            @Override
            public int getHeight(int x, int y) {
                return heights[y][x];
            }

            @Override
            public int getWidth() {
                return 3;
            }

            @Override
            public int getHeightCount() {
                return 3;
            }
        };

        map.generateFromHeightMap(hMap);

        // just check all cells are non-null (actual biome assignment may vary)
        for (int y = 0; y < map.getHeightCount(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                assertEquals(true, map.getBiome(x, y) != null);
            }
        }
    }
}
