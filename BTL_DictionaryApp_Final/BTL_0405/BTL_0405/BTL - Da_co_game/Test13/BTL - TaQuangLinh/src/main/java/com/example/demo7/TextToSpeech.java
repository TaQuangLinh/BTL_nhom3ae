package com.example.demo7;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TextToSpeech {
    private Synthesizer synthesizer;

    public TextToSpeech() {
        try {
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"
            );


            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            synthesizer = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            synthesizer.allocate();
            synthesizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // test xem đã import đúng thư viện chưa.
    public static void main(String[] args) throws EngineException {
        TextToSpeech noi = new TextToSpeech();
        noi.speak("What is your name?");
        noi.close();
    }

    public void speak(String text) {
        try {
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() throws EngineException {
        synthesizer.deallocate();
    }
}




