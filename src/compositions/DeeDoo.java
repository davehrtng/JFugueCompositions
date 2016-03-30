package compositions;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

import enumeration.Instrument;

public class DeeDoo extends Composition {

    private static final String FLUTE_STRING_ = "Eq Ch. | Eq Ch. | Dq Eq Dq Cq";

    private static final String CHOIR_STRING_ = "AminQQQQ | CmajQQQQ | GmajQQQ CmajQ";

    private static final String GUITAR_STRING_ = "Aq Aq Bq Gq | Aq Aq Bq Gq | Aq Aq Bq Gq";

    private static final String REST_ = "Rw | Rw | Rw";

    private static final Pattern FLUTE_ = new Pattern().setVoice(0)
	    .setInstrument(Instrument.FLUTE.getMidiCode()).add(FLUTE_STRING_)
	    .add(FLUTE_STRING_).add(FLUTE_STRING_).add(FLUTE_STRING_);

    private static final Pattern CHOIR_ = new Pattern().setVoice(1)
	    .setInstrument(Instrument.CHOIR.getMidiCode()).add(CHOIR_STRING_)
	    .add(CHOIR_STRING_).add(CHOIR_STRING_).add(CHOIR_STRING_);

    private static final Pattern GUITAR_ = new Pattern().setVoice(2)
	    .setInstrument(Instrument.GUITAR_DISTORTED.getMidiCode())
	    .add(REST_).add(REST_).add(GUITAR_STRING_).add(GUITAR_STRING_);

    private static final Pattern DRUMS_;
    static {
	Rhythm rhythm = new Rhythm().addLayer("O..oO...O..oOO..O..oOO..")
		.addLayer("..S...S...S...S...S...S.")
		.addLayer("````````````````````````")
		.addLayer(".......................+");
	DRUMS_ = new Pattern().setVoice(3).add(REST_).add(REST_)
		.add(rhythm.getPattern().repeat(4));
    }

    public DeeDoo() {
	addParts(DRUMS_, FLUTE_, CHOIR_, GUITAR_);
    }
}
