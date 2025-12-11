package components.biomemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for BiomeMap1L (kernel implementation).
 */
public class BiomeMap1LTest {

    private BiomeMap1L map1x1;
    private BiomeMap1L map5x5;

    @Before
    public void setUp() {
        map1x1 = new BiomeMap1L();       // Default 1x1
        map5x5 = new BiomeMap1L(5, 5);   // 5x5 map
    }

    // -------------------- Constructor Tests --------------------

    @Test
    public void testDefaultConstructor() {
        assertEquals(1, map1x1.getWidth());
        assertEquals(1, map1x1.getHeightCount());
        assertEquals(Biome.PLAINS, map1x1.getBiome(0, 0));
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(5, map5x5.getWidth());
        assertEquals(5, map5x5.getHeightCount());
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                assertEquals(Biome.PLAINS, map5x5.getBiome(x, y));
            }
        }
    }

    @Test
    public void testConstructorInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new BiomeMap1L(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new BiomeMap1L(5, 0));
        assertThrows(IllegalArgumentException.class, () -> new BiomeMap1L(-1, -1));
    }

    // -------------------- Accessor/Mutator Tests --------------------

    @Test
    public void testGetSetBiome() {
        map5x5.setBiome(2, 3, Biome.FOREST);
        assertEquals(Biome.FOREST, map5x5.getBiome(2, 3));
    }

    @Test
    public void testSetBiomeNull() {
        assertThrows(IllegalArgumentException.class, () -> map5x5.setBiome(0, 0, null));
    }

    @Test
    public void testGetBiomeOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.getBiome(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.getBiome(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.getBiome(5, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.getBiome(0, 5));
    }

    @Test
    public void testSetBiomeOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.setBiome(-1, 0, Biome.DESERT));
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.setBiome(0, -1, Biome.DESERT));
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.setBiome(5, 0, Biome.DESERT));
        assertThrows(IndexOutOfBoundsException.class, () -> map5x5.setBiome(0, 5, Biome.DESERT));
    }

    @Test
    public void testGetWidthHeightCount() {
        assertEquals(5, map5x5.getWidth());
        assertEquals(5, map5x5.getHeightCount());
    }

    // -------------------- Standard Methods --------------------

    @Test
    public void testClear() {
        map5x5.setBiome(1, 1, Biome.FOREST);
        map5x5.clear();
        assertEquals(1, map5x5.getWidth());
        assertEquals(1, map5x5.getHeightCount());
        assertEquals(Biome.PLAINS, map5x5.getBiome(0, 0));
    }

    @Test
    public void testTransferFrom() {
        BiomeMap1L source = new BiomeMap1L(2, 2);
        source.setBiome(0, 0, Biome.DESERT);
        map1x1.transferFrom(source);

        // map1x1 now has source's previous state
        assertEquals(2, map1x1.getWidth());
        assertEquals(2, map1x1.getHeightCount());
        assertEquals(Biome.DESERT, map1x1.getBiome(0, 0));

        // source should be reset to 1x1
        assertEquals(1, source.getWidth());
        assertEquals(1, source.getHeightCount());
        assertEquals(Biome.PLAINS, source.getBiome(0, 0));
    }

    @Test
    public void testTransferFromInvalid() {
        assertThrows(IllegalArgumentException.class, () -> map1x1.transferFrom(null));
        assertThrows(IllegalArgumentException.class, () -> map1x1.transferFrom(map1x1));
    }

    @Test
    public void testNewInstance() {
        BiomeMap instance = map5x5.newInstance();
        assertEquals(1, instance.getWidth());
        assertEquals(1, instance.getHeightCount());
        assertEquals(Biome.PLAINS, instance.getBiome(0, 0));
    }
}
