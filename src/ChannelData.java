package nomaoi;

import java.io.*;
import java.util.StringTokenizer;

class ChannelData {
    int program = 0;
    int velocity = 64;
    int pressure = 0;
    int bend = 8192;
    int reverb = 40;
    int sustain = 0;
    boolean mute = false;
    boolean solo = false;
    boolean mono = false;

    static ChannelData load(String filename) {
        ChannelData result = new ChannelData();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                parseLine(line, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void parseLine(String line, ChannelData result) {
        StringTokenizer tokens = new StringTokenizer(line);
        if (!tokens.hasMoreTokens()) {
            return;
        }
        String name = tokens.nextToken();
        if (!tokens.hasMoreTokens()) {
            return;
        }
        String value = tokens.nextToken();
        try {
            if (name.equals("Program:")) {
                result.program = Integer.parseInt(value);
            } else if (name.equals("Velocity:")) {
                result.velocity = Integer.parseInt(value);
            } else if (name.equals("Pressure:")) {
                result.pressure = Integer.parseInt(value);
            } else if (name.equals("Bend:")) {
                result.bend = Integer.parseInt(value);
            } else if (name.equals("Reverb:")) {
                result.reverb = Integer.parseInt(value);
            } else if (name.equals("Sustain:")) {
                result.sustain = Integer.parseInt(value);
            } else if (name.equals("Mute:")) {
                result.mute = Boolean.parseBoolean(value);
            } else if (name.equals("Solo:")) {
                result.solo = Boolean.parseBoolean(value);
            } else if (name.equals("Mono:")) {
                result.mono = Boolean.parseBoolean(value);
            }
        } catch (Exception e) {
        }
    }

    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("ChannelData[");
        pw.println(" Program: " + program);
        pw.println(" Velocity: " + velocity);
        pw.println(" Pressure: " + pressure);
        pw.println(" Bend: " + bend);
        pw.println(" Reverb: " + reverb);
        pw.println(" Sustain: " + sustain);
        pw.println(" Mute: " + mute);
        pw.println(" Solo: " + solo);
        pw.println(" Mono: " + mono);
        pw.println("]");
        return sw.toString();
    }
}
