package ui;

import model.TestResult;
import model.TestResultCollection;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// This class lets the user decide between viewing past results and starting a new quiz
// add to git

public class Menu extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/testHistory.json";

    ArrayList<TestResultCollection> allResults;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: creates an instance of menu
    public Menu() {
        super("Ear Trainer");
        allResults = new ArrayList<>();
        initializeGUI();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //runMenu();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the Swing GUI
    private void initializeGUI() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JLabel title = new JLabel("Welcome to the world famous Musical Ear Training Tool!");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(title);

        initializeButtons();

        String sep = System.getProperty("file.separator");
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + sep
                + "imgs" + sep + "musicnotes.png");
        JLabel image = new JLabel(icon);
        add(image);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds main menu buttons to the JFrame
    private void initializeButtons() {
        JButton btnPlay = new JButton("Play");
        btnPlay.setActionCommand("Play");
        btnPlay.addActionListener(this);
        add(btnPlay);

        JButton btnViewResults = new JButton("View Results");
        btnViewResults.setActionCommand("View");
        btnViewResults.addActionListener(this);
        add(btnViewResults);

        JButton btnSave = new JButton("Save Results");
        btnSave.setActionCommand("Save");
        btnSave.addActionListener(this);
        add(btnSave);

        JButton btnLoad = new JButton("Load Results");
        btnLoad.setActionCommand("Load");
        btnLoad.addActionListener(this);
        add(btnLoad);
    }


    // EFFECTS: Turns the results into a JSON string
    public static JSONObject resultsToJson(ArrayList<TestResultCollection> allResults) {
        JSONObject json = new JSONObject();
        JSONArray easyJsonArray = new JSONArray();
        JSONArray mediumJsonArray = new JSONArray();
        JSONArray hardJsonArray = new JSONArray();
        for (TestResultCollection history : allResults) {
            if (history.getDifficulty().equals("EASY")) {
                easyJsonArray.put(history.testResultsToJson());
            } else if (history.getDifficulty().equals("MEDIUM")) {
                mediumJsonArray.put(history.testResultsToJson());
            } else {
                hardJsonArray.put(history.testResultsToJson());
            }
        }
        json.put("EASY", easyJsonArray);
        json.put("MEDIUM", mediumJsonArray);
        json.put("HARD", hardJsonArray);
        return json;
    }

    // EFFECTS: saves the test results to file
    private void saveTestResults() {
        try {
            jsonWriter.open();
            jsonWriter.write(Menu.resultsToJson(allResults));
            jsonWriter.close();
            System.out.println("Saved Test History");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads test results from file
    private void loadTestResults() {
        try {
            allResults = jsonReader.read();
            System.out.println("Loaded previous history from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) {
            Quiz quiz = new Quiz();
            allResults.add(quiz.getTestHistory());
        } else if (e.getActionCommand().equals("View")) {
            new ResultDisplay(allResults);
        } else if (e.getActionCommand().equals("Save")) {
            saveTestResults();
        } else if (e.getActionCommand().equals("Load")) {
            loadTestResults();
        }
    }
}
