package components.biomemap;

import components.random.Random1L;
import components.simplewriter.SimpleWriter;

/**
 * Abstract class implementing all secondary methods for {@code BiomeMap}.
 *
 * <p>
 * This class implements only secondary methods using the kernel methods defined
 * in {@code BiomeMapKernel}. It should not access any internal representation
 * directly.
 * </p>
 *
 * @author Christian Stratton
 */
public abstract class BiomeMapSecondary implements BiomeMap {

    @Override
    public void generateFromHeightMap(HeightMap h) {
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
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

    @Override
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

    @Override
    public void randomizeBiomes() {
        Random1L rand = new Random1L();
        Biome[] biomes = Biome.values();
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                int index = Math.abs(rand.nextInt()) % biomes.length;
                Biome randomBiome = biomes[index];
                this.setBiome(x, y, randomBiome);
            }
        }
    }

    @Override
    public void printMap(SimpleWriter out) {
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                out.print(this.getBiome(x, y).toString().charAt(0));
                out.print(' ');
            }
            out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                sb.append(this.getBiome(x, y).toString().charAt(0)).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BiomeMap)) {
            return false;
        }
        BiomeMap other = (BiomeMap) obj;
        if (this.getWidth() != other.getWidth()
                || this.getHeightCount() != other.getHeightCount()) {
            return false;
        }
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                if (this.getBiome(x, y) != other.getBiome(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int y = 0; y < this.getHeightCount(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                hash = 31 * hash + this.getBiome(x, y).hashCode();
            }
        }
        return hash;
    }
}
