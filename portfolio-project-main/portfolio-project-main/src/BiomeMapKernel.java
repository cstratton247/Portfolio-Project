import components.simplewriter.SimpleWriter;

/**
 * Kernel component for {@code BiomeMap}.
 *
 * @author Christian Stratton
 * @mathsubtypes <pre>
 * BiomeMapKernel is modeled as a 2D grid of Biomes
 * </pre>
 * @mathmodel <pre>
 * this = [ [b_{0,0}, b_{1,0}, ..., b_{w-1,0}],
 *          [b_{0,1}, b_{1,1}, ..., b_{w-1,1}],
 *          ...
 *          [b_{0,h-1}, ..., b_{w-1,h-1}] ]
 * </pre>
 */
public interface BiomeMapKernel {

    /**
     * Enumeration of possible biomes.
     */
    enum Biome {
        OCEAN, BEACH, PLAINS, DESERT, MOUNTAIN
    }

    /**
     * Sets the biome at the given coordinates.
     *
     * @updates this
     * @requires 0 <= x < this.getWidth() and 0 <= y < this.getHeightCount()
     * @ensures getBiome(x, y) = b
     *
     * @param x
     *            column index
     * @param y
     *            row index
     * @param b
     *            biome to set
     */
    void setBiome(int x, int y, Biome b);

    /**
     * Reports the biome at the given coordinates.
     *
     * @requires 0 <= x < this.getWidth() and 0 <= y < this.getHeightCount()
     * @ensures getBiome(x, y) = b at position (x, y)
     *
     * @param x
     *            column index
     * @param y
     *            row index
     * @return biome at (x, y)
     */
    Biome getBiome(int x, int y);

    /**
     * Reports the number of columns in the map.
     *
     * @return number of columns
     */
    int getWidth();

    /**
     * Reports the number of rows in the map.
     *
     * @return number of rows
     */
    int getHeightCount();
}
