package ui;

import model.TestResult;
import model.TestResultCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// This class displays past results to the user

public class ResultDisplay extends JFrame implements ActionListener {
    ArrayList<TestResultCollection> allResults;
    ArrayList<ArrayList<JLabel>> allResultLabels;
    JLabel title;
    JButton toggleEasy;
    JButton toggleMedium;
    JButton toggleHard;

    // EFFECTS: runs the result display window
    public ResultDisplay(ArrayList<TestResultCollection> allResults) {
        this.allResults = allResults;
        allResultLabels = new ArrayList<>();
        initializeResultLabels(allResults);
        initializeGUI();
    }

    // MODIFIES: this
    // EFFECTS: creates the labels for the results based on allResults
    private void initializeResultLabels(ArrayList<TestResultCollection> allResults) {
        int index = 1;
        for (TestResultCollection resultCollection : allResults) {
            ArrayList<JLabel> resultLabel = new ArrayList<>();
            JLabel quizNum = new JLabel("QUIZ NUMBER " + index);
            resultLabel.add(quizNum);
            int correctTests = 0;
            for (TestResult result : resultCollection.getCollection()) {
                JLabel correctAns = new JLabel("Correct Answer: " + result.getCorrectAnswer());
                JLabel inputtedAns = new JLabel("Inputted Answer: " + result.getInputtedAnswer());
                resultLabel.add(correctAns);
                resultLabel.add(inputtedAns);
                if (result.isCorrect()) {
                    correctTests++;
                }
            }
            int totalTests = resultCollection.getCollection().size();
            JLabel totalCorrect = new JLabel(" \n You got " + correctTests + "/" + totalTests + " correct!");
            resultLabel.add(totalCorrect);
            allResultLabels.add(resultLabel);
            index++;
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes the Swing GUI
    private void initializeGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        title = new JLabel("Here are all your past results");
        title.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        add(title);
        initializeToggleButtons();

        for (ArrayList<JLabel> resultLabel : allResultLabels) {
            for (JLabel label : resultLabel) {
                add(label);
            }
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons to hide and show items of certain difficulties
    private void initializeToggleButtons() {
        toggleEasy = new JButton("Hide all Easy Quizzes");
        toggleEasy.setActionCommand("Hide Easy");
        toggleEasy.addActionListener(this);
        add(toggleEasy);

        toggleMedium = new JButton("Hide all Medium Quizzes");
        toggleMedium.setActionCommand("Hide Medium");
        toggleMedium.addActionListener(this);
        add(toggleMedium);

        toggleHard = new JButton("Hide all Hard Quizzes");
        toggleHard.setActionCommand("Hide Hard");
        toggleHard.addActionListener(this);
        add(toggleHard);
    }


    // MODIFIES: this
    // EFFECTS: hides all quiz results of a certain difficulty
    private void hideAllOfDifficulty(String difficulty) {
        for (int i = 0; i < allResults.size(); i++) {
            allResults.get(i).setVisibleFalse();
            if (allResults.get(i).getDifficulty().equals(difficulty)) {
                for (JLabel label : allResultLabels.get(i)) {
                    label.setVisible(false);
                }
            }
        }
        pack();
    }

    // MODIFIES: this
    // EFFECTS: shows all quiz results of a certain difficulty
    private void showAllOfDifficulty(String difficulty) {
        for (int i = 0; i < allResults.size(); i++) {
            allResults.get(i).setVisibleTrue();
            if (allResults.get(i).getDifficulty().equals(difficulty)) {
                for (JLabel label : allResultLabels.get(i)) {
                    label.setVisible(true);
                }
            }
        }
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkHideButtons(e);
        checkShowButtons(e);
    }

    // MODIFIES: this
    // EFFECTS: checks if a button to hide results is pressed
    private void checkHideButtons(ActionEvent e) {
        if (e.getActionCommand().equals("Hide Easy")) {
            toggleEasy.setText("Show all Easy Quizzes");
            toggleEasy.setActionCommand("Show Easy");
            hideAllOfDifficulty("EASY");
        } else if (e.getActionCommand().equals("Hide Medium")) {
            toggleMedium.setText("Show all Medium Quizzes");
            toggleMedium.setActionCommand("Show Medium");
            hideAllOfDifficulty("MEDIUM");
        } else if (e.getActionCommand().equals("Hide Hard")) {
            toggleHard.setText("Show all Hard Quizzes");
            toggleHard.setActionCommand("Show Hard");
            hideAllOfDifficulty("HARD");
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if a button to show results is pressed
    private void checkShowButtons(ActionEvent e) {
        if (e.getActionCommand().equals("Show Easy")) {
            toggleEasy.setText("Hide all Easy Quizzes");
            toggleEasy.setActionCommand("Hide Easy");
            showAllOfDifficulty("EASY");
        } else if (e.getActionCommand().equals("Show Medium")) {
            toggleMedium.setText("Hide all Medium Quizzes");
            toggleMedium.setActionCommand("Hide Medium");
            showAllOfDifficulty("MEDIUM");
        } else if (e.getActionCommand().equals("Show Hard")) {
            toggleHard.setText("Hide all Hard Quizzes");
            toggleHard.setActionCommand("Hide Hard");
            showAllOfDifficulty("HARD");
        }
    }
}
