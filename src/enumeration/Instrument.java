package enumeration;

public enum Instrument {
    VIOLA(42),

    CHOIR(53),

    FLUTE(74),

    BASS(36),

    GUITAR_DISTORTED(31),

    GUITAR_OVERDRIVEN(30),

    SYNTH_BRASS_ONE(63),

    SYNTH_BRASS_TWO(64),

    SYNTH_LEAD_BASS(88),

    SYNTH_PAD_SWEEP(96),

    PIANO(0);

    private int midiCode_;

    private Instrument(int midiCode) {
	this.midiCode_ = midiCode;
    }

    public int getMidiCode() {
	return midiCode_;
    }
}
