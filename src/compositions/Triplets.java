package compositions;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

import enumeration.Instrument;

/*
 * D + F# + B 
 * D + F# + C# 
 * E + F# + C# 
 * E + G# + C# 
 */
public class Triplets extends Composition {

    private static int NUMBER_OF_HYPERMEASURES_ = 5;

    private static int NUMBER_OF_MEASURES_PER_HYPERMEASURE_ = 4;

    private static final Pattern PIANO_CHORDS_ = new Pattern().setVoice(0)
	    .setInstrument(Instrument.PIANO.getMidiCode())
	    // .add("Dw+F#4w+B4w | D4w+F#4w+C#4w | E4w+F#4w+C#4w | E4w+G#4w+C#4W")
	    .add("BminW | Dmaj7W | C#maj6W | Emaj6W")
	    .repeat(NUMBER_OF_HYPERMEASURES_);

    private static final Pattern DRUMS_ = new Pattern().setVoice(1).add(
	    createRhythm());

    private static final Pattern createRhythm() {

	String restMeasure = "........";

	return new Rhythm()
		.addLayer("````````")
		.addLayer("....x...")
		.setLength(
			NUMBER_OF_HYPERMEASURES_
				* NUMBER_OF_MEASURES_PER_HYPERMEASURE_)
		.addRangedAltLayer(0, 0, 3, restMeasure)
		.addRangedAltLayer(1, 0, 3, restMeasure).getPattern();
    }

    public Triplets() {
	addParts(PIANO_CHORDS_, DRUMS_);
    }

    public static void main(String... args) {
	Triplets composition = new Triplets();
	composition.perform();
	// composition.generateMidi();
    }

}
