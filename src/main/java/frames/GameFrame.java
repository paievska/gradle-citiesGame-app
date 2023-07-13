package frames;

import cities.CitiesSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameFrame extends JFrame implements ActionListener {
    private final int hintCost = 150;
    private final String MENU_TEXT_TEMPLATE = "<html>Підказка <span style='color: red'> -" + hintCost
            + "$</span></html>";
    private JLabel label3;
    private final JLabel timeLabel = new JLabel("Час: 0 с");
    private final JButton button = new JButton("Зробити хід");
    private final JTextField text = new JTextField();
    private final CitiesSearch citiesSearch;
    private String str = "";
    private int movesCount = 0;
    private int compScore = 0;
    private int userScore = 0;
    private int userAccount = 0;
    private int recordScore;
    private final JLabel scoreLabel = new JLabel("<html>Бали: " + userAccount
            + "<span style='color: green'>$</span></html>");
    private long startTime;
    private Timer timer;
    private final String fileName = "recordScore.txt";

    public GameFrame() {
        initializeUI();
        setMenuBar();
        setLabels();
        setButtonAndTextField();
        setTimer();
        citiesSearch = new CitiesSearch();
        recordScore = readRecordScoreFromFile();
    }

    private void initializeUI() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image.png")));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Міста");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(480, 350);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void setMenuBar() {
        JMenuItem hintItem = new JMenuItem(MENU_TEXT_TEMPLATE);
        JMenuItem newGameItem = new JMenuItem("Нова гра");
        JMenuItem exitItem = new JMenuItem("Вихід");
        JMenu menu = new JMenu("Меню");
        JMenuBar menuBar = new JMenuBar();
        newGameItem.addActionListener(this);
        hintItem.addActionListener(this);
        exitItem.addActionListener(this);
        menu.add(newGameItem);
        menu.add(hintItem);
        menu.add(exitItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void setLabels() {
        JLabel label = new JLabel("Введіть назву міста");
        label.setFont(new Font("Main", Font.BOLD, 15));
        label.setBounds(200, 85, 200, 50);
        JLabel label2 = new JLabel("Комп'ютер:");
        label2.setFont(new Font("Main", Font.BOLD, 15));
        label2.setBounds(200, 150, 200, 50);
        label3 = new JLabel("-");
        label3.setFont(new Font("Main", Font.BOLD, 15));
        label3.setBounds(290, 150, 200, 50);
        timeLabel.setFont(new Font("Main", Font.BOLD, 10));
        timeLabel.setBounds(95, 250, 100, 30);
        scoreLabel.setFont(new Font("Main", Font.BOLD, 10));
        scoreLabel.setBounds(30, 250, 100, 30);
        this.add(scoreLabel);
        this.add(timeLabel);
        this.add(label);
        this.add(label2);
        this.add(label);
        this.add(label3);
    }

    private void setButtonAndTextField() {
        button.setBounds(30, 160, 150, 31);
        button.setFocusable(false);
        button.addActionListener(this);
        text.setBounds(30, 100, 150, 31);
        this.add(button);
        this.add(text);
    }

    private void setTimer() {
        timer = new Timer(1000, e -> {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000;
            timeLabel.setText("Час: " + elapsedTime + " с");
        });
        startTime = System.currentTimeMillis();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            str = text.getText().toLowerCase();
            if (!str.isEmpty()) {
                if (str.equals("здаюсь")) {
                    compScore++;
                    resultMessage("Упс! Ви програли");
                } else {
                    if (movesCount > 0) {
                        char lastChar = citiesSearch.getLastChar(citiesSearch.getLastCity());
                        if (!(str.charAt(0) == lastChar)) {
                            JOptionPane.showMessageDialog(this, "Помилка! Введіть слово, яке " +
                                    "починається на останню букву слова комп'ютера.", "Помилка",
                                    JOptionPane.ERROR_MESSAGE);
                            text.setText("");
                            return;
                        }
                    }
                    if (citiesSearch.getCityList().stream().anyMatch(city -> city.equals(str))) {
                        citiesSearch.removeCity(str);
                        userScore++;
                        int correctAnswerCost = 100;
                        userAccount += correctAnswerCost;
                        String compCity = citiesSearch.findCity(citiesSearch.getLastChar(str));
                        if (compCity == null) {
                            scoreLabel.setText("<html>Бали: " + userAccount + "<span style='color: green'>$</span>" +
                                    "</html>");
                            resultMessage("Урааа! Ви виграли");
                        } else {
                            citiesSearch.removeCity(compCity);
                            label3.setText(citiesSearch.getPrettyName(compCity));
                            label3.setForeground(Color.BLUE);
                            movesCount++;
                            text.setText("");
                            compScore++;
                            scoreLabel.setText("<html>Бали: " + userAccount + "<span style='color: green'>$</span>" +
                                    "</html>");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Місто не знайдено або вже " +
                                "використано", "Помилка", JOptionPane.ERROR_MESSAGE);
                        text.setText("");
                    }
                }
            }
        } else if (e.getActionCommand().equals(MENU_TEXT_TEMPLATE)) {
            if (userAccount >= hintCost) {
                String lastCompCity = citiesSearch.getLastCity();
                char lastChar = Character.toLowerCase(lastCompCity.charAt(lastCompCity.length() - 1));
                String hintCity = citiesSearch.getCityList().stream().filter(city ->
                        Character.toLowerCase(city.charAt(0)) == Character.toLowerCase(lastChar)).findFirst()
                        .orElse("Немає підказок");
                if (!hintCity.equalsIgnoreCase("Немає підказок")) {
                    userAccount -= hintCost;
                }
                text.setText(citiesSearch.getPrettyName(hintCity));
            } else {
                JOptionPane.showMessageDialog(this, "Недостатньо коштів", "Помилка",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Нова гра")) {
            resultMessage(userScore > compScore ? "Урааа! Ви виграли" : userScore == compScore ? "Нічія." :
                    "Упс! Ви програли");
            this.dispose();
            saveRecordScoreToFile(recordScore);
            new GameFrame();
        } else if (e.getActionCommand().equals("Вихід")) {
            resultMessage(userScore > compScore ? "Урааа! Ви виграли" : userScore == compScore ? "Нічія " :
                    "Упс! Ви програли");
            saveRecordScoreToFile(recordScore);
            System.exit(0);
        }
    }

    private void resultMessage(String message) {
        timer.stop();
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        String[] options = {"Нова гра", "Вихід"};
        if (userAccount > recordScore) {
            recordScore = userAccount;
            JOptionPane.showMessageDialog(this, "Ви встановили новий рекорд!" +
                    "\nНовий рекорд: " + recordScore + " балів");
        }
        int choice = JOptionPane.showOptionDialog(this, message + " з рахунком " +
                userScore + ":" + compScore + "\nЧас: " + totalTime + "с" + "\nКількість балів: " + userAccount +
                "\nРекорд: " + recordScore + " балів", "Результат", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            this.dispose();
            saveRecordScoreToFile(recordScore);
            new GameFrame();
        } else if (choice == 1) {
            saveRecordScoreToFile(recordScore);
            System.exit(0);
        }
    }

    private void saveRecordScoreToFile(int highScore) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.write(Integer.toString(highScore));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int readRecordScoreFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentRecordScore = br.readLine();
            if (currentRecordScore == null) {
                saveRecordScoreToFile(0);
                return 0;
            }
            return Integer.parseInt(currentRecordScore);
        } catch (FileNotFoundException e) {
            saveRecordScoreToFile(0);
            return 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

