package ui;

import model.IntervalDictionary;
import model.NoteDictionary;
import model.TestResult;
import model.TestResultCollection;
import sound.MidiSynth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// This class holds all the quiz functionality and is called by menu to create a new quiz instance

public class Quiz extends JFrame implements ActionListener {

    String quizDifficulty;
    TestResultCollection testHistory;
    JButton easy;
    JButton medium;
    JButton hard;
    JLabel title;
    JLabel question;
    JLabel correctness;
    JLabel image;
    ArrayList<JButton> answers;
    //JButton quit;
    JButton relisten;
    int testNum;
    int playedInterval;
    ArrayList<Integer> allowedIntervals;
    int note1;
    int note2;

    // EFFECTS: runs the quiz
    public Quiz() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        title = new JLabel("Please Choose A Difficulty!");
        title.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        add(title);

        question = new JLabel("What do you hear?");
        add(question);
        question.setVisible(false);
        initializeDifficultyButtons();
        initializeRelisten();
        relisten.setVisible(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        testNum = 1;
        testHistory = new TestResultCollection(new ArrayList<>(), quizDifficulty);
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons to select difficulty
    private void initializeDifficultyButtons() {
        easy = new JButton("Easy");
        easy.setActionCommand("Easy");
        easy.addActionListener(this);
        add(easy);

        medium = new JButton("Medium");
        medium.setActionCommand("Medium");
        medium.addActionListener(this);
        add(medium);

        hard = new JButton("Hard");
        hard.setActionCommand("Hard");
        hard.addActionListener(this);
        add(hard);
    }


    // MODIFIES: this
    // EFFECTS: Main function that runs the quiz
    private void runQuizGUI(ArrayList<Integer> allowedIntervals) {
        easy.setVisible(false);
        medium.setVisible(false);
        hard.setVisible(false);
        title.setVisible(false);
        question.setVisible(true);

        initializeAnswerButtons(allowedIntervals);

        String sep = System.getProperty("file.separator");
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + sep
                + "imgs" + sep + "musicnotes.png");
        image = new JLabel(icon);
        add(image);

        correctness = new JLabel();
        add(correctness);

        printQuestion(allowedIntervals, testNum);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: Initializes buttons for answer input
    private void initializeAnswerButtons(ArrayList<Integer> allowedIntervals) {
        IntervalDictionary intervalDictionary = new IntervalDictionary();
        answers = new ArrayList<>();
        for (int interval : allowedIntervals) {
            JButton button = new JButton(intervalDictionary.getInterval(interval));
            button.setActionCommand(Integer.toString(interval));
            button.addActionListener(this);
            add(button);
            answers.add(button);
        }

        relisten.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the relisten button
    private void initializeRelisten() {
        relisten = new JButton("Relisten");
        relisten.setActionCommand("Relisten");
        relisten.addActionListener(this);
        add(relisten);
    }

    // EFFECTS: Generates and prints a random quiz question
    private void printQuestion(ArrayList<Integer> allowedIntervals, int testNum) {
        note1 = (int) (Math.random() * 12);
        int intervalIndex = (int) (Math.random() * allowedIntervals.size());
        int interval = allowedIntervals.get(intervalIndex);
        playedInterval = interval;
        note2 = note1 + interval;

        NoteDictionary noteDictionary = new NoteDictionary();

        printStartOfQuestion(testNum, noteDictionary.getNoteName(note1), noteDictionary.getNoteName(note2));
        playNotes(note1 + 60, note2 + 60);
    }

    // EFFECTS: prints the start of the question
    private void printStartOfQuestion(int testNum, String note1, String note2) {
        System.out.println("TEST NUMBER " + testNum);
        System.out.println("Note 1: " + note1);
        System.out.println("Note 2: " + note2);
    }

    // EFFECTS: plays the notes of the question
    private void playNotes(int note1, int note2) {
        MidiSynth midi = new MidiSynth();
        midi.open();
        midi.play(0, note1, 100);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        midi.play(0, note2, 100);
    }


    public TestResultCollection getTestHistory() {
        return testHistory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Easy") || command.equals("Medium") || command.equals("Hard")) {
            handleDifficulty(command);
        } else if (command.equals("Relisten")) {
            System.out.println("Relistening");
            playNotes(note1 + 60, note2 + 60);
        } else {
            handleAnswer(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the difficulty selection
    private void handleDifficulty(String command) {
        Integer[] list;
        if (command.equals("Easy")) {
            list = new Integer[] {3, 4, 5, 7};
            allowedIntervals = new ArrayList<>(Arrays.asList(list));
        } else if (command.equals("Medium")) {
            list = new Integer[] {2, 3, 4, 5, 7, 9, 11, 12};
            allowedIntervals = new ArrayList<>(Arrays.asList(list));
        } else {
            list = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
            allowedIntervals = new ArrayList<>(Arrays.asList(list));
        }
        quizDifficulty = command.toUpperCase();
        testHistory.setDifficulty(quizDifficulty);
        runQuizGUI(allowedIntervals);
    }

    // MODIFIES: this
    // EFFECTS: records answer and gives feedback
    private void handleAnswer(String answer) {
        int numAnswer = Integer.parseInt(answer);
        String sep = System.getProperty("file.separator");
        ImageIcon icon;
        if (numAnswer == playedInterval) {
            correctness.setText("CORRECT! :D");
            icon = new ImageIcon(System.getProperty("user.dir") + sep
                    + "imgs" + sep + "thumbsup.png");
        } else {
            correctness.setText("INCORRECT! >:(");
            icon = new ImageIcon(System.getProperty("user.dir") + sep
                    + "imgs" + sep + "angry.gif");
        }
        image.setIcon(icon);
        IntervalDictionary dictionary = new IntervalDictionary();
        String correctAnswer = dictionary.getInterval(playedInterval);
        String inputtedAnswer = dictionary.getInterval(numAnswer);
        TestResult result = new TestResult(correctAnswer, inputtedAnswer);
        testHistory.addResultToCollection(result);

        System.out.print(getTestHistory());
        testNum++;
        printQuestion(allowedIntervals, testNum);
    }

    @Override
    public void dispose() {
        System.out.println("Closed");
        super.dispose();
    }
}
