import components.random.Random1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * BiomeMap demonstrates a component that classifies terrain values into biomes.
 *
 * @author Christian Stratton
 */
public final class BiomeMap {

    /**
     * Enumeration of possible biomes.
     */
    public enum Biome {
        OCEAN, BEACH, PLAINS, DESERT, MOUNTAIN
    }

    /**
     * Represents a simple height map storing elevation values.
     */
    private static final class HeightMap {
        private final int[][] heights;

        /**
         * Constructor to create a height map with example pattern values.
         *
         * @param width
         *            number of columns
         * @param height
         *            number of rows
         */
        HeightMap(int width, int height) {
            this.heights = new int[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    this.heights[y][x] = (x + y) % 10;
                }
            }
        }

        /**
         * Reports the height at (x, y).
         *
         * @param x
         *            column index
         * @param y
         *            row index
         * @return height value
         */
        int getHeight(int x, int y) {
            return this.heights[y][x];
        }

        /**
         * Reports number of columns.
         *
         * @return width
         */
        int getWidth() {
            return this.heights[0].length;
        }

        /**
         * Reports number of rows.
         *
         * @return height count
         */
        int getHeightCount() {
            return this.heights.length;
        }
    }

    /**
     * Internal 2D array storing the biomes.
     */
    private final Biome[][] map;

    /**
     * Constructor to initialize a biome map.
     *
     * @param width
     *            number of columns
     * @param height
     *            number of rows
     */
    public BiomeMap(int width, int height) {
        this.map = new Biome[height][width];
    }

    /**
     * Kernel method: sets the biome at (x, y).
     *
     * @param x
     *            column index
     * @param y
     *            row index
     * @param b
     *            biome to set
     */
    public void setBiome(int x, int y, Biome b) {
        this.map[y][x] = b;
    }

    /**
     * Kernel method: reports the biome at (x, y).
     *
     * @param x
     *            column index
     * @param y
     *            row index
     * @return biome at given coordinates
     */
    public Biome getBiome(int x, int y) {
        return this.map[y][x];
    }

    /**
     * Kernel method: reports number of columns.
     *
     * @return width
     */
    public int getWidth() {
        return this.map[0].length;
    }

    /**
     * Kernel method: reports number of rows.
     *
     * @return height count
     */
    public int getHeightCount() {
        return this.map.length;
    }

    /**
     * Secondary method: assigns biomes based on height map values.
     *
     * @param h
     *            height map
     */
    public void generateFromHeightMap(HeightMap h) {
        for (int y = 0; y < h.getHeightCount(); y++) {
            for (int x = 0; x < h.getWidth(); x++) {
                int height = h.getHeight(x, y);
                Biome b;
                if (height < 3) {
                    b = Biome.OCEAN;
                } else if (height < 5) {
                    b = Biome.BEACH;
                } else if (height < 7) {
                    b = Biome.PLAINS;
                } else if (height < 9) {
                    b = Biome.DESERT;
                } else {
                    b = Biome.MOUNTAIN;
                }
                this.setBiome(x, y, b);
            }
        }
    }

    /**
     * Secondary method: counts how many cells contain the given biome.
     *
     * @param b
     *            biome to count
     * @return number of occurrences
     */
    public int countBiome(Biome b) {
        int count = 0;
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                if (this.getBiome(x, y) == b) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Secondary method: fills the map with random biomes.
     */
    public void randomizeBiomes() {
        Random1L rand = new Random1L();
        Biome[] biomes = Biome.values();
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                int index = Math.abs(rand.nextInt() % biomes.length);
                this.setBiome(x, y, biomes[index]);
            }
        }
    }

    /**
     * Utility method to print the biome map.
     *
     * @param out
     *            output stream
     */
    public void printMap(SimpleWriter out) {
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                Biome b = this.getBiome(x, y);
                out.print(b.toString().charAt(0));
                out.print(' ');
            }
            out.println();
        }
    }

    /**
     * Main method to demonstrate the BiomeMap component.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        out.println("--- BiomeMap Component Proof of Concept ---");
        BiomeMap map = new BiomeMap(8, 5);
        HeightMap heights = new HeightMap(8, 5);

        out.println("\nGenerating biomes from HeightMap:");
        map.generateFromHeightMap(heights);
        map.printMap(out);

        out.println("\nCounting biomes:");
        for (Biome b : Biome.values()) {
            out.println(b + ": " + map.countBiome(b));
        }

        out.println("\nRandomizing biomes:");
        map.randomizeBiomes();
        map.printMap(out);

        out.close();
    }
}
