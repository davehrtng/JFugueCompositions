package compositions;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

import enumeration.Instrument;

public class LazyBassDriven extends Composition {

    private static final String REST_FOUR_MEASURES_ = "Rw | Rw | Rw | Rw";

    private static final String BASS_LINE_ = "C3q C3h C3q | A3q A3h A3q | F3q F3h F3q | F3q F3h F3q";

    private static final String PAD_CHORDS_ = "CmajW | AminW | FmajH FmajH | FmajH FmajH";

    private static final String GUITAR_LINE_ = "Gq. Aq. Cq | Gq. Aq. Cq | Gq. Aq. Cq | Gq. Aq. Cq";

    private static final int NUMBER_OF_MEASURES_ = 9;

    private static final Pattern DRUMS_;
    static {
	Rhythm rhythm = new Rhythm()
		.addLayer("O.O.O.O.O.O.O.O.O.O.O.O.O.O.O.O.");
	DRUMS_ = rhythm.getPattern().repeat(NUMBER_OF_MEASURES_);
    }

    private static final Pattern BASS_ = new Pattern().setVoice(0)
	    .setInstrument(Instrument.SYNTH_LEAD_BASS.getMidiCode())
	    .add(REST_FOUR_MEASURES_)
	    .add(new Pattern(BASS_LINE_).repeat(NUMBER_OF_MEASURES_ - 1));

    private static final Pattern PAD_ = new Pattern().setVoice(1)
	    .setInstrument(Instrument.SYNTH_PAD_SWEEP.getMidiCode())
	    .add(new Pattern(REST_FOUR_MEASURES_).repeat(3))
	    .add(new Pattern(PAD_CHORDS_).repeat(NUMBER_OF_MEASURES_ - 3));

    private static final Pattern GUITAR_ = new Pattern().setVoice(2)
	    .setInstrument(Instrument.GUITAR_DISTORTED.getMidiCode())
	    .add(new Pattern(REST_FOUR_MEASURES_).repeat(5))
	    .add(new Pattern(GUITAR_LINE_).repeat(NUMBER_OF_MEASURES_ - 5));

    public LazyBassDriven() {
	addParts(DRUMS_, BASS_, PAD_, GUITAR_);
    }
}
