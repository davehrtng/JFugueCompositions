package enumeration;

public enum Instrument {
    VIOLA(42),

    CHOIR(53),

    FLUTE(74),

    BASS(36),

    CHOIR_AAH(52),

    CHOIR_OOH(53),

    GUITAR_DISTORTED(31),

    GUITAR_OVERDRIVEN(30),

    SYNTH_BRASS_ONE(63),

    SYNTH_BRASS_TWO(64),

    SYNTH_LEAD_BASS(88),

    SYNTH_PAD_SWEEP(96),

    SYNTH_SQUARE(81),

    SYNTH_SAWTOOTH(82),

    SYNTH_CALIOPE(83),

    SYNTH_CHIFF(84),

    SYNTH_CHARANG(85),

    SYNTH_VOICE(86),

    PIANO(0),

    ORGAN_DRAWBAR(17),

    ORGAN_PERCUSSIVE(18),

    ORGAN_ROCK(19),

    ORGAN_CHURCH(20),

    ORGAN_REED(21);

    private int midiCode_;

    private Instrument(int midiCode) {
	this.midiCode_ = midiCode;
    }

    public int getMidiCode() {
	return midiCode_;
    }
}
