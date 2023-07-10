package frames;

import cities.Cities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameFrame extends JFrame implements ActionListener {
    JButton button = new JButton("Зробити хід");
    JTextField text = new JTextField();
    String str = "";
    JLabel label = new JLabel("Введіть назву міста");
    JLabel label2 = new JLabel("Комп'ютер:");
    JLabel label3 = new JLabel("-");
    Cities cities = new Cities();
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Меню");
    JMenuItem newGameItem = new JMenuItem("Нова гра");
    JMenuItem hintItem = new JMenuItem("<html>Підказка <span style='color: red'> -150$</span></html>");
    JMenuItem exitItem = new JMenuItem("Вихід");
    int movesCount = 0;
    int compScore = 0;
    int userScore = 0;
    int recordScore;
    JLabel scoreLabel = new JLabel("<html>Бали: " + userScore + "<span style='color: green'>$</span></html>");
    JLabel timeLabel = new JLabel("Час: 0 с");
    long startTime;
    Timer timer;
    String fileName = "recordScore.txt";

    public GameFrame() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image.png")));
        label.setFont(new Font("Main", Font.BOLD, 15));
        label.setBounds(200, 85, 200, 50);
        label2.setFont(new Font("Main", Font.BOLD, 15));
        label2.setBounds(200, 150, 200, 50);
        label3.setFont(new Font("Main", Font.BOLD, 15));
        label3.setBounds(290, 150, 200, 50);
        button.setBounds(30, 160, 150, 31);
        button.setFocusable(false);
        button.addActionListener(this);
        text.setBounds(30, 100, 150, 31);
        hintItem.addActionListener(this);
        newGameItem.addActionListener(this);
        exitItem.addActionListener(this);
        menu.add(newGameItem);
        menu.add(hintItem);
        menu.add(exitItem);
        menuBar.add(menu);
        scoreLabel.setFont(new Font("Main", Font.BOLD, 10));
        scoreLabel.setBounds(30, 250, 100, 30);
        this.add(scoreLabel);
        timeLabel.setFont(new Font("Main", Font.BOLD, 10));
        timeLabel.setBounds(95, 250, 100, 30);
        this.add(timeLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menuBar);
        this.setTitle("Міста");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(480, 350);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(button);
        this.add(text);
        timer = new Timer(1000, e -> {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000;
            timeLabel.setText("Час: " + elapsedTime + " с");
        });
        startTime = System.currentTimeMillis();
        timer.start();
        recordScore = readRecordScoreFromFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            str = text.getText();
            if (!str.isEmpty()) {
                if (str.equalsIgnoreCase("Здаюсь")) {
                    compScore++;
                    resultMessage("Упс! Ви програли");
                } else {
                    if (movesCount == 0) {
                        char lastChar = Character.toLowerCase(str.charAt(str.length() - 1));
                        if (cities.cityList.stream().anyMatch(city -> city.equalsIgnoreCase(str))) {
                            String compCity = cities.findCity(lastChar);
                            if (compCity == null) {
                                cities.incrementUserScore();
                                userScore += 100;
                                scoreLabel.setText("<html>Бали: " + userScore + "<span style='color: green'>$</span></html>");
                                resultMessage("Урааа! Ви виграли");
                            } else {
                                label3.setText(compCity);
                                label3.setForeground(Color.getHSBColor(39f, 1f, 1f));
                                compScore++;
                                userScore += 100;
                                scoreLabel.setText("<html>Бали: " + userScore + "<span style='color: green'>$</span></html>");
                                cities.incrementUserScore();
                                text.setText("");
                                cities.cityList.remove(str);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Місто не знайдено або вже використано", "Помилка", JOptionPane.ERROR_MESSAGE);
                            text.setText("");
                        }

                    } else {
                        String lastCompCity = cities.getLastCity();
                        char lastChar = Character.toLowerCase(lastCompCity.charAt(lastCompCity.length() - 1));
                        if (Character.toLowerCase(str.charAt(0)) == lastChar) {
                            if (cities.cityList.stream().anyMatch(city -> city.equalsIgnoreCase(str))) {
                                String compCity = cities.findCity(str.charAt(str.length() - 1));
                                if (compCity == null) {
                                    cities.incrementUserScore();
                                    userScore += 100;
                                    scoreLabel.setText("<html>Бали: " + userScore + "<span style='color: green'>$</span></html>");
                                    resultMessage("Урааа! Ви виграли");
                                } else {
                                    label3.setText(compCity);
                                    movesCount++;
                                    text.setText("");
                                    compScore++;
                                    cities.incrementUserScore();
                                    userScore += 100;
                                    scoreLabel.setText("<html>Бали: " + userScore + "<span style='color: green'>$</span></html>");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Місто не знайдено або вже використано", "Помилка", JOptionPane.ERROR_MESSAGE);
                                text.setText("");
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Помилка! Введіть слово, яке починається на останню букву слова комп'ютера.", "Помилка", JOptionPane.ERROR_MESSAGE);
                            text.setText("");
                        }
                    }
                }
            }
        } else if (e.getActionCommand().equals("<html>Підказка <span style='color: red'> -150$</span></html>")) {
            if (userScore >= 150) {
                String lastCompCity = cities.getLastCity();
                char lastChar = Character.toLowerCase(lastCompCity.charAt(lastCompCity.length() - 1));
                String hintCity = cities.cityList.stream().filter(city -> Character.toLowerCase(city.charAt(0)) == Character.toLowerCase(lastChar)).findFirst().orElse("Немає підказок");
                if (!hintCity.equalsIgnoreCase("Немає підказок")) {
                    userScore -= 150;
                }
                text.setText(hintCity);
            } else {
                JOptionPane.showMessageDialog(this, "Недостатньо коштів", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Нова гра")) {
            this.dispose();
            saveRecordScoreToFile(recordScore);
            new GameFrame();
        } else if (e.getActionCommand().equals("Вихід")) {
            this.dispose();
            saveRecordScoreToFile(recordScore);
        }
    }

    public void resultMessage(String message) {
        timer.stop();
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        String[] options = {"Нова гра", "Вихід"};
        if (userScore > recordScore) {
            recordScore = userScore;
            JOptionPane.showMessageDialog(this, "Ви встановили новий рекорд!" + "\nНовий рекорд: " + recordScore + " балів");
        }
        int choice = JOptionPane.showOptionDialog(this, message + " з рахунком " + cities.getUserScore() + ":" + compScore + "\nЧас: " + totalTime + "с" + "\nКількість балів: " + userScore + "\nРекорд: " + recordScore + " балів", "Результат", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            this.dispose();
            saveRecordScoreToFile(recordScore);
            new GameFrame();
        } else if (choice == 1) {
            this.dispose();
            saveRecordScoreToFile(recordScore);
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

