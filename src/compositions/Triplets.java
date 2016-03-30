package compositions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    private static int NUMBER_OF_HYPERMEASURES_ = 10;

    private static int NUMBER_OF_MEASURES_PER_HYPERMEASURE_ = 4;

    private static final String REST_FOUR_MEASURES_ = "Rw | Rw | Rw | Rw";

    private static final Pattern PIANO_CHORDS_ = new Pattern().setVoice(0)
	    .setInstrument(Instrument.PIANO.getMidiCode())
	    .add("BminW | Dmaj7W | C#maj6W | Emaj6W")
	    .repeat(NUMBER_OF_HYPERMEASURES_);

    private static final Pattern DRUMS_ = new Pattern().setVoice(1).add(
	    createRhythm());

    private static final Pattern LONG_ = new Pattern().setVoice(2)
	    .setInstrument(Instrument.SYNTH_CHARANG.getMidiCode())
	    .add(REST_FOUR_MEASURES_).add(REST_FOUR_MEASURES_)
	    .add(new Pattern("Bh. Ai C#w | Ri | Bh. Ai C#w | Ri").repeat(4));

    private static final Pattern BASS_ = new Pattern().setVoice(3)
	    .setInstrument(Instrument.SYNTH_CALIOPE.getMidiCode())
	    .add(REST_FOUR_MEASURES_)
	    .add(REST_FOUR_MEASURES_)
	    // TODO: create a method that returns a specified number of rest
	    // measures or hypermeasures
	    .add(REST_FOUR_MEASURES_)
	    .add(REST_FOUR_MEASURES_)
	    .add(new Pattern("X[Volume]=1000"
		    + createMeasureOfSixteenths("D3", "F#3", "B4")
		    + createMeasureOfSixteenths("D3", "F#3", "C#4")
		    + createMeasureOfSixteenths("E3", "F#3", "C#4")
		    + createMeasureOfSixteenths("E3", "G#3", "C#4"))
		    .repeat(NUMBER_OF_HYPERMEASURES_ - 4));

    private static final Pattern SOLO_ = new Pattern().setVoice(4)
	    .setInstrument(Instrument.PIANO.getMidiCode())
	    .add(REST_FOUR_MEASURES_).add(REST_FOUR_MEASURES_)
	    .add(REST_FOUR_MEASURES_).add(REST_FOUR_MEASURES_)
	    .add(REST_FOUR_MEASURES_).add(REST_FOUR_MEASURES_)
	    .add(new Pattern(generateSolo((NUMBER_OF_HYPERMEASURES_ - 6) * 4)));

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

    /**
     * sixteenths might sound better than triplets
     */
    private static final String createMeasureOfSixteenths(String note1,
	    String note2, String note3) {
	String ending = "s";

	String quarter = note1 + ending + " " + note2 + ending + " " + note3
		+ ending + " " + note2 + ending + " ";

	String measure = quarter + quarter + quarter + quarter;

	return measure;
    }

    private static final String createMeasureOfTriplets(String note1,
	    String note2, String note3) {
	String tripletEnding = "q*3:1";

	String triplet = note1 + tripletEnding + " " + note2 + tripletEnding
		+ " " + note3 + tripletEnding + " ";

	String measure = triplet + triplet + triplet + triplet;

	return measure;
    }

    private static final String generateSolo(int numberOfMeasures) {
	Map<String, Integer> lengthModifierToLength = new HashMap<>();
	lengthModifierToLength.put("s", 1);
	lengthModifierToLength.put("i", 2);
	lengthModifierToLength.put("i.", 3);
	lengthModifierToLength.put("q", 4);
	lengthModifierToLength.put("q.", 6);
	lengthModifierToLength.put("h", 8);
	lengthModifierToLength.put("h.", 12);
	lengthModifierToLength.put("w", 16);

	String[] length = { "q", "q.", "h", "h.", "i", "i.", "s", "w" };
	String[] note = { "A", "B", "C#", "D", "E", "F#", "G#" };

	StringBuilder solo = new StringBuilder();
	solo.append("X[Volume]=16383");

	Random generator = new Random();

	int numberOfSixteenthNotesToGenerate = numberOfMeasures * 16;

	while (numberOfSixteenthNotesToGenerate > 0) {
	    String selectedNote = note[generator.nextInt(note.length)];
	    String selectedLength = length[generator.nextInt(length.length)];
	    solo.append(selectedNote);
	    solo.append(selectedLength);
	    solo.append(" ");

	    numberOfSixteenthNotesToGenerate -= lengthModifierToLength
		    .get(selectedLength);
	}

	return solo.toString();

    }

    public Triplets() {
	addParts(PIANO_CHORDS_, DRUMS_, LONG_, BASS_, SOLO_);
    }
}
