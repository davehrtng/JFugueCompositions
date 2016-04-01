package player;

import java.util.HashSet;
import java.util.Set;

import compositions.Composition;
import compositions.DeeDoo;
import compositions.LazyBassDriven;
import compositions.Triplets;

public class Player {

    private static Set<Composition> compositions = new HashSet<>();
    static {
	compositions.add(new DeeDoo());
	compositions.add(new LazyBassDriven());
	compositions.add(new Triplets());
    }

    public static void generateAllMidi() {
	compositions.forEach(composition -> composition.generateMidi());
    }

    /**
     * Use this method to perform compositions or generate midi.
     */
    public static void main(String... args) {
	generateAllMidi();
	// Composition composition = new Triplets();
	// composition.perform();
    }
}
