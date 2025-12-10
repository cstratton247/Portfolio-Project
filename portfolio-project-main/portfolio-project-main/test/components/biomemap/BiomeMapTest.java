package components.biomemap;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * JUnit tests for BiomeMap and BiomeMap1L.
 */
public class BiomeMapTest {

    private BiomeMap1L map;

    @Before
    public void setUp() {
        map = new BiomeMap1L(3, 3);
        // Set some biomes for testing
        map.setBiome(0, 0, Biome.PLAINS);
        map.setBiome(1, 0, Biome.FOREST);
        map.setBiome(2, 0, Biome.DESERT);
        map.setBiome(0, 1, Biome.MOUNTAIN);
        map.setBiome(1, 1, Biome.SNOW);
        map.setBiome(2, 1, Biome.FOREST);
        map.setBiome(0, 2, Biome.SWAMP);
        map.setBiome(1, 2, Biome.DESERT);
        map.setBiome(2, 2, Biome.PLAINS);
    }

    @Test
    public void testGetBiome() {
        assertEquals(Biome.PLAINS, map.getBiome(0, 0));
        assertEquals(Biome.FOREST, map.getBiome(1, 0));
        assertEquals(Biome.DESERT, map.getBiome(2, 0));
        assertEquals(Biome.MOUNTAIN, map.getBiome(0, 1));
        assertEquals(Biome.SNOW, map.getBiome(1, 1));
        assertEquals(Biome.FOREST, map.getBiome(2, 1));
        assertEquals(Biome.SWAMP, map.getBiome(0, 2));
        assertEquals(Biome.DESERT, map.getBiome(1, 2));
        assertEquals(Biome.PLAINS, map.getBiome(2, 2));
    }

    @Test
    public void testSetBiome() {
        map.setBiome(0, 0, Biome.SWAMP);
        assertEquals(Biome.SWAMP, map.getBiome(0, 0));
    }

    @Test
    public void testWidthHeight() {
        assertEquals(3, map.getWidth());
        assertEquals(3, map.getHeightCount());
    }

    @Test
    public void testClear() {
        map.clear();
        assertEquals(1, map.getWidth());
        assertEquals(1, map.getHeightCount());
        assertEquals(Biome.PLAINS, map.getBiome(0, 0));
    }

    @Test
    public void testTransferFrom() {
        BiomeMap1L other = new BiomeMap1L(2, 2);
        other.setBiome(0, 0, Biome.FOREST);
        map.transferFrom(other);
        assertEquals(2, map.getWidth());
        assertEquals(2, map.getHeightCount());
        assertEquals(Biome.FOREST, map.getBiome(0, 0));
        // Ensure other is reset
        assertEquals(1, other.getWidth());
        assertEquals(1, other.getHeightCount());
    }

    @Test
    public void testNewInstance() {
        BiomeMap newMap = map.newInstance();
        assertEquals(1, newMap.getWidth());
        assertEquals(1, newMap.getHeightCount());
    }

    @Test
    public void testCountBiome() {
        assertEquals(2, map.countBiome(Biome.PLAINS));
        assertEquals(2, map.countBiome(Biome.DESERT));
        assertEquals(2, map.countBiome(Biome.FOREST));
        assertEquals(1, map.countBiome(Biome.SNOW));
        assertEquals(1, map.countBiome(Biome.SWAMP));
        assertEquals(1, map.countBiome(Biome.MOUNTAIN));
    }

    @Test
    public void testRandomizeBiomes() {
        map.randomizeBiomes();
        boolean allSame = true;
        Biome first = map.getBiome(0, 0);
        for (int y = 0; y < map.getHeightCount(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.getBiome(x, y) != first) {
                    allSame = false;
                }
            }
        }
        // Randomize should produce some variation (not guaranteed, but likely)
        assert !allSame;
    }

    @Test
    public void testPrintMapOutput() {
        // Capture output using StringWriter
        StringWriter stringWriter = new StringWriter();
        SimpleWriter out = new SimpleWriter1L(new PrintWriter(stringWriter, true));

        map.printMap(out);

        String output = stringWriter.toString();

        // Check that the output contains all the biome names
        assert output.contains("PLAINS");
        assert output.contains("FOREST");
        assert output.contains("DESERT");
        assert output.contains("MOUNTAIN");
        assert output.contains("SNOW");
        assert output.contains("SWAMP");

        // Check that each row appears roughly correct
        String[] rows = output.split("\n");
        assertEquals(3, rows.length); // 3 rows
        for (String row : rows) {
            assert row.split("\\s+").length == 3; // Split into 3 columns per row with matching whitespace
        }
    }
}

