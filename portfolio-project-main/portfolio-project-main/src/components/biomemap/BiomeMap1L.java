package components.biomemap;

import java.util.Arrays;

/**
 * Kernel implementation of {@code BiomeMap}.
 *
 * <p>
 * Representation:
 * <pre>
 *     private Biome[][] map;
 *     private int width;
 *     private int height;
 * </pre>
 * </p>
 *
 * <p><b>Convention:</b></p>
 * <ul>
 *   <li>map is not {@code null}.</li>
 *   <li>map.length == height.</li>
 *   <li>for every 0 <= i &lt; height: map[i].length == width.</li>
 *   <li>width &gt; 0 and height &gt; 0.</li>
 *   <li>for every valid (x,y): map[y][x] is not {@code null} (contains a Biome).</li>
 * </ul>
 *
 * <p><b>Correspondence:</b></p>
 * <ul>
 *   <li>The abstract BiomeMap's cell at coordinate (x,y) corresponds to map[y][x].</li>
 *   <li>{@code getWidth()} corresponds to {@code width}.</li>
 *   <li>{@code getHeightCount()} corresponds to {@code height}.</li>
 * </ul>
 */
public class BiomeMap1L extends BiomeMapSecondary {

    /**
     * Representation of the biome grid. Indexing: map[y][x].
     */
    private Biome[][] map;

    /**
     * Width (number of columns).
     */
    private int width;

    /**
     * Height (number of rows).
     */
    private int height;

    /**
     * Default single-cell dimension used to re-initialize transferred-from
     * instances so they remain valid under the convention.
     */
    private static final int DEFAULT_DIMENSION = 1;

    /**
     * Default biome for initialization so no cell is null.
     */
    private static final Biome DEFAULT_BIOME = Biome.PLAINS;

    /**
     * Create a fresh representation of the given size and initialize every
     * cell with the default biome. This method must not call any public
     * methods of this or other components.
     *
     * @param w width (must be > 0)
     * @param h height (must be > 0)
     */
    private void createNewRep(int w, int h) {
        this.width = w;
        this.height = h;
        this.map = new Biome[h][];
        for (int y = 0; y < h; y++) {
            this.map[y] = new Biome[w];
            for (int x = 0; x < w; x++) {
                this.map[y][x] = DEFAULT_BIOME;
            }
        }
    }

    /**
     * No-argument constructor. Creates a minimal valid map.
     */
    public BiomeMap1L() {
        this.createNewRep(DEFAULT_DIMENSION, DEFAULT_DIMENSION);
    }

    /**
     * Constructor specifying width and height.
     *
     * @param w width (must be > 0)
     * @param h height (must be > 0)
     * @throws IllegalArgumentException if w <= 0 or h <= 0
     */
    public BiomeMap1L(int w, int h) {
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("width and height must be > 0");
        }
        this.createNewRep(w, h);
    }

    // Kernel methods -----------------------------------------------------------

    @Override
    public void setBiome(int x, int y, Biome b) {
        if (b == null) {
            throw new IllegalArgumentException("biome must not be null");
        }
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new IndexOutOfBoundsException("coordinates out of range");
        }
        this.map[y][x] = b;
    }

    @Override
    public Biome getBiome(int x, int y) {
        Biome result = null;
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new IndexOutOfBoundsException("coordinates out of range");
        }
        result = this.map[y][x];
        return result;
    }

    @Override
    public int getWidth() {
        int result = this.width;
        return result;
    }

    @Override
    public int getHeightCount() {
        int result = this.height;
        return result;
    }

    // Standard methods -------------------------------------------------------------------


    /**
     * Reset this map to a minimal valid state (1×1) containing the default
     * biome. Uses createNewRep to respect kernel purity.
     */
    @Override
    public final void clear() {
        this.createNewRep(DEFAULT_DIMENSION, DEFAULT_DIMENSION);
    }

    /**
     * Transfer the representation from {@code source} into this object.
     *
     * After this call:
     * - this contains the representation formerly in source,
     * - source is reset to a valid minimal representation.
     *
     * @param source the BiomeMap to transfer from
     * @throws IllegalArgumentException if source is null or source == this
     * @throws ClassCastException if source is not a BiomeMap1L
     */
    @Override
    public final void transferFrom(BiomeMap source) {
        if (source == null) {
            throw new IllegalArgumentException("source must not be null");
        }
        if (source == this) {
            throw new IllegalArgumentException("cannot transferFrom self");
        }

        BiomeMap1L src = (BiomeMap1L) source;
        this.map = src.map;
        this.width = src.width;
        this.height = src.height;
        src.createNewRep(DEFAULT_DIMENSION, DEFAULT_DIMENSION);
    }

    /**
     * Return a new instance of this component's implementation class (empty).
     *
     * @return a new BiomeMap1L instance
     */
    @Override
    public BiomeMap newInstance() {
        BiomeMap result = new BiomeMap1L();
        return result;
    }
}
