package compositions;

import java.io.File;

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
     * Gets all the parts of the composition. Each part can be thought of as a track that will be started at the same time when the composition is played.
     * 
     * @return a collection of the parts of the composition
     */
    public abstract Pattern[] getParts();

    /**
     * @return the name to give the generated midi file of this composition, excluding period and file extension.
     */
    protected abstract String getMidiFileName();

    /**
     * Plays the composition
     */
    public void perform() {
	Player player = new Player();
	player.play(getParts());
    }

    /**
     * Creates a midi file of the composition, saved into the "generated/midi" directory of this project
     */
    public void generateMidi() {

	try {
	    Player player = new Player();
	    Sequence sequence = player.getSequence(getParts());
	    MidiFileManager.save(sequence, new File(GENERATED_MIDI_DIRECTORY_ + getMidiFileName() + "." + MIDI_FILE_EXTENSION_));
	} catch (Exception e) {
	    System.err.println("The midi file " + getMidiFileName() + " was not generated.");
	}
    }
}
