package frames;

import cities.Cities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    JButton button = new JButton("Зробити хід");
    JTextField text = new JTextField();
    String str = "";
    JLabel label = new JLabel("Введіть назву міста");
    JLabel label2 = new JLabel("Комп'ютер:");
    JLabel label3 = new JLabel("-");
    Cities cities = new Cities();
    int turnCount = 0;
    int compScore = 0;

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

        this.setTitle("Міста");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(450, 350);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(button);
        this.add(text);
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
                    if (turnCount == 0) {
                        char lastChar = Character.toLowerCase(str.charAt(str.length() - 1));
                        if (cities.cityList.contains(str)) {
                            String compCity = cities.findCity(lastChar);
                            if (compCity == null) {
                                cities.incrementUserScore();
                                resultMessage("Урааа! Ви виграли");
                            } else {
                                label3.setText(compCity);
                                compScore++;
                                cities.incrementUserScore();
                                text.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Місто не знайдено або вже використано", "ERROR", JOptionPane.ERROR_MESSAGE);
                            text.setText("");
                        }

                    } else {
                        String lastCompCity = cities.getLastCity();
                        char lastChar = Character.toLowerCase(lastCompCity.charAt(lastCompCity.length() - 1));
                        if (Character.toLowerCase(str.charAt(0)) == lastChar) {
                            if (cities.cityList.contains(str)) {
                                String compCity = cities.findCity(str.charAt(str.length() - 1));
                                if (compCity == null) {
                                    cities.incrementUserScore();
                                    resultMessage("Урааа! Ви виграли");
                                } else {
                                    label3.setText(compCity);
                                    turnCount++;
                                    text.setText("");
                                    compScore++;
                                    cities.incrementUserScore();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Місто не знайдено або вже використано", "ERROR", JOptionPane.ERROR_MESSAGE);
                                text.setText("");
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Помилка! Введіть слово, яке починається на останню букву слова комп'ютера.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            text.setText("");
                        }
                    }
                }
            }
        }
    }

    public void resultMessage(String message) {
        String[] options = {"Нова гра", "Вихід"};
        int choice = JOptionPane.showOptionDialog(this, message + " з рахунком " + cities.getUserScore() + ":" + compScore, "Результат", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            this.dispose();
            new GameFrame();
        } else if (choice == 1) {
            this.dispose();
        }
    }
}
