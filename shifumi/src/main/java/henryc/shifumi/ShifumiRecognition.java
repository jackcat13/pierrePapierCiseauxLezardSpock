/*
 * Copyright 2013 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 */

package henryc.shifumi;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import henryc.shifumi.model.ShifumiChoices;
import henryc.shifumi.model.ShifumiGame;

import java.util.HashMap;
import java.util.Map;


public class ShifumiRecognition {

    private static final String ACOUSTIC_MODEL =
        "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private static final String DICTIONARY_PATH =
        "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
    private static final String GRAMMAR_PATH =
        "resource:/dialog/";

    private static final Map<String, Integer> DIGITS =
        new HashMap<String, Integer>();

    private static void recognizerShifumi(LiveSpeechRecognizer recognizer) {

        ShifumiGame shifumiGame = new ShifumiGame();

        System.out.println("This is shifumi menu");
        System.out.println("-------------------------------");
        System.out.println("Example: lizard");
        System.out.println("Example: spock");
        System.out.println("Example: paper");
        System.out.println("Example: scisors");
        System.out.println("Example: stone");
        System.out.println("-------------------------------");

        recognizer.startRecognition(true);

        while (true) {
            String utterance = recognizer.getResult().getHypothesis();
            ShifumiChoices shifumiChoice = null;
            if (utterance.endsWith("exit")) {
                break;
            } else if (utterance.startsWith("lizard")) {
                shifumiChoice = ShifumiChoices.LIZARD;
            } else if (utterance.startsWith("paper")) {
                shifumiChoice = ShifumiChoices.PAPER;
            } else if (utterance.startsWith("spock")) {
                shifumiChoice = ShifumiChoices.SPOCK;
            } else if (utterance.startsWith("scissors")) {
                shifumiChoice = ShifumiChoices.SCISSORS;
            } else if (utterance.startsWith("stone")) {
                shifumiChoice = ShifumiChoices.STONE;
            } else {
                shifumiChoice = ShifumiChoices.NOTHING;
            }
            System.out.println("Computer choice : " + shifumiGame.randomComputerChoice());
            System.out.println("Your choice : " + shifumiChoice);
            shifumiGame.playRound(shifumiChoice);
            System.out.println("And this round winner is " + shifumiGame.getWinner() + "\nScore : PLAYER " + shifumiGame.getScore()[0] + " COMPUTER : " + shifumiGame.getScore()[1]);
        }

        recognizer.stopRecognition();
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath(ACOUSTIC_MODEL);
        configuration.setDictionaryPath(DICTIONARY_PATH);
        configuration.setGrammarPath(GRAMMAR_PATH);
        configuration.setUseGrammar(true);

        configuration.setGrammarName("dialog");
        LiveSpeechRecognizer jsgfRecognizer =
            new LiveSpeechRecognizer(configuration);

        recognizerShifumi(jsgfRecognizer);

    }
}
