package compositions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public abstract class Composition {
    /**
     * Directory to put generated midi files into
     */
    private static final String GENERATED_MIDI_DIRECTORY_ = "C:\\Users\\David\\workspace\\JFugueComposition\\generated\\midi\\";

    /**
     * File extension for MIDI files
     */
    private static final String MIDI_FILE_EXTENSION_ = "mid";

    /**
     * List of all "parts" of the piece. All parts will be played
     * simultaneously.
     */
    private List<Pattern> partList = new ArrayList<>();

    /**
     * Gets all the parts of the composition. Each part can be thought of as a
     * track that will be started at the same time when the composition is
     * played.
     * 
     * @return a collection of the parts of the composition
     */
    private Pattern[] getParts() {
	return partList.toArray(new Pattern[partList.size()]);
    }

    /**
     * @return the name to give the generated midi file of this composition,
     *         excluding period and file extension.
     */
    protected abstract String getMidiFileName();

    /**
     * Adds a part to the composition. All parts will be played simultaneously
     * when the piece is performed. A part can be thought of a single person
     * playing a single instrument in a band or orchestra.
     * 
     * @param part
     *            a single part of the composition
     */
    protected void addPart(Pattern part) {
	partList.add(part);
    }

    /**
     * Add multiple parts to the composition in a single call. Convenience
     * method that passes through to {@link #addPart(Pattern)}.
     * 
     * @param parts
     *            array of parts to add to the composition
     */
    protected void addParts(Pattern... parts) {
	for (Pattern part : parts) {
	    addPart(part);
	}
    }

    /**
     * Plays the composition
     */
    public void perform() {
	Player player = new Player();
	player.play(getParts());
    }

    /**
     * Creates a midi file of the composition, saved into the "generated/midi"
     * directory of this project
     */
    public void generateMidi() {

	try {
	    Player player = new Player();
	    Sequence sequence = player.getSequence(getParts());
	    MidiFileManager.save(sequence, new File(GENERATED_MIDI_DIRECTORY_
		    + getMidiFileName() + "." + MIDI_FILE_EXTENSION_));
	} catch (Exception e) {
	    System.err.println("The midi file " + getMidiFileName()
		    + " was not generated.");
	}
    }
}
