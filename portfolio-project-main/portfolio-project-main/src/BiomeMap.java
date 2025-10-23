import components.simplewriter.SimpleWriter;
import components.random.Random1L;

/**
 * Enhanced interface for {@code BiomeMap}, extending {@code BiomeMapKernel}.
 *
 * @author Christian Stratton
 *
 */
public interface BiomeMap extends BiomeMapKernel {

    /**
     * Represents a height map providing elevation data.
     */
    interface HeightMap {
        /**
         * Reports the height at the given coordinates.
         *
         * @param x
         *            column index
         * @param y
         *            row index
         * @return height value
         */
        int getHeight(int x, int y);

        /**
         * Reports the number of columns.
         *
         * @return width
         */
        int getWidth();

        /**
         * Reports the number of rows.
         *
         * @return height count
         */
        int getHeightCount();
    }

    /**
     * Generates biomes from a given height map.
     *
     * @updates this
     * @requires h.getWidth() = this.getWidth() and h.getHeightCount() = this.getHeightCount()
     * @ensures <pre>
     * Biomes are assigned according to height thresholds:
     *   height < 3 → OCEAN
     *   3 ≤ height < 5 → BEACH
     *   5 ≤ height < 7 → PLAINS
     *   7 ≤ height < 9 → DESERT
     *   height ≥ 9 → MOUNTAIN
     * </pre>
     *
     * @param h
     *            height map source
     */
    void generateFromHeightMap(HeightMap h);

    /**
     * Counts the number of cells containing the given biome.
     *
     * @param b
     *            biome to count
     * @return number of occurrences of {@code b}
     */
    int countBiome(Biome b);

    /**
     * Randomizes all biomes in the map.
     *
     * @updates this
     * @ensures every cell in this contains a random Biome
     */
    void randomizeBiomes();

    /**
     * Prints the map contents using the given output stream.
     *
     * @param out
     *            the output stream
     */
    void printMap(SimpleWriter out);
}
