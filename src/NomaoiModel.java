package nomaoi;

import javax.sound.midi.*;

class NomaoiModel {
    private static final int SUSTAIN = 64;
    private static final int REVERB  = 91;

    private Synthesizer synthesizer;
    private MidiChannel channel;
    private ChannelData channelData = new ChannelData();
    private int note;

    public NomaoiModel(ChannelData channelData) {
        if (channelData != null) {
            this.channelData = channelData;
        }
    }

    public void open() {
        if (!openSynthesizer()) {
            return;
        }
        dumpInstruments();
        setupChannels();
        dumpChannel();
    }

    private boolean openSynthesizer() {
        try {
            if (synthesizer == null) {
                synthesizer = MidiSystem.getSynthesizer();
                if (synthesizer == null) {
                    System.out.println("getSynthesizer() failed!");
                    return false;
                }
            }
            synthesizer.open();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void dumpInstruments() {
        Instrument[] instruments = synthesizer.getLoadedInstruments();
        for (int i = 0; i < instruments.length; ++i) {
            System.out.println(instruments[i]);
        }
    }

    private void setupChannels() {
        MidiChannel channels[] = synthesizer.getChannels();
        channel = channels[0];
        applyChannelData();
    }

    private void applyChannelData() {
        channel.programChange(channelData.program);
        channel.setChannelPressure(channelData.pressure);
        channel.setPitchBend(channelData.bend);
        channel.controlChange(REVERB, channelData.reverb);
        channel.controlChange(SUSTAIN, channelData.sustain);
        channel.setMute(channelData.mute);
        channel.setSolo(channelData.solo);
        channel.setMono(channelData.mono);
    }

    private void dumpChannel() {
        System.out.println("Program: " + channelData.program);
        System.out.println("Velocity: " + channelData.velocity);
        System.out.println("Pressure: " + channel.getChannelPressure());
        System.out.println("Bend: " + channel.getPitchBend());
        System.out.println("Reverb: " + channel.getController(REVERB));
        System.out.println("Sustain: " + channel.getController(SUSTAIN));
        System.out.println("Mute: " + channel.getMute());
        System.out.println("Solo: " + channel.getSolo());
        System.out.println("Mono: " + channel.getMono());
    }

    public void close() {
        if (synthesizer != null) {
            synthesizer.close();
        }
        synthesizer = null;
        channel = null;
    }

    public void noteOn(int noteNumber) {
        noteOff();
        note = noteNumber;
        channel.noteOn(note, channelData.velocity);
    }

    public void noteOff() {
        channel.noteOff(note, channelData.velocity);
    }
}
