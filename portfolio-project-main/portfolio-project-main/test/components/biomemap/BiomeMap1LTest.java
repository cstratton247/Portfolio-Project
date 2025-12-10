package components.biomemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test fixture for {@code BiomeMap1L}.
 * Tests kernel-level methods directly.
 */
public class BiomeMap1LTest {

    private BiomeMap1L map;

    @Before
    public void setUp() {
        map = new BiomeMap1L(3, 3); // small 3x3 map for testing
    }

    @Test
    public void testGetWidthAndHeight() {
        assertEquals(3, map.getWidth());
        assertEquals(3, map.getHeightCount());
    }

    @Test
    public void testSetAndGetBiome() {
        map.setBiome(0, 0, Biome.FOREST);
        assertEquals(Biome.FOREST, map.getBiome(0, 0));

        map.setBiome(2, 2, Biome.DESERT);
        assertEquals(Biome.DESERT, map.getBiome(2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBiomeNull() {
        map.setBiome(1, 1, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBiomeOutOfBounds() {
        map.getBiome(-1, 0);
    }

    @Test
    public void testClear() {
        map.setBiome(0, 0, Biome.FOREST);
        map.clear();
        assertEquals(1, map.getWidth());
        assertEquals(1, map.getHeightCount());
        assertEquals(Biome.PLAINS, map.getBiome(0, 0));
    }

    @Test
    public void testTransferFrom() {
        BiomeMap1L source = new BiomeMap1L(2, 2);
        source.setBiome(0, 0, Biome.MOUNTAIN);
        map.transferFrom(source);

        // source should be reset
        assertEquals(1, source.getWidth());
        assertEquals(1, source.getHeightCount());

        // map should now have source's previous state
        assertEquals(2, map.getWidth());
        assertEquals(2, map.getHeightCount());
        assertEquals(Biome.MOUNTAIN, map.getBiome(0, 0));
    }

    @Test
    public void testNewInstance() {
        BiomeMap newMap = map.newInstance();
        assertNotNull(newMap);
        assertEquals(1, newMap.getWidth());
        assertEquals(1, newMap.getHeightCount());
    }
}

