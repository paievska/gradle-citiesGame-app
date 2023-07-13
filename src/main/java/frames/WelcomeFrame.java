package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame implements ActionListener {
    private final JButton button = new JButton("OK");

    public WelcomeFrame() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image.png")));
        button.setBounds(280, 25, 80, 20);
        button.addActionListener(this);
        button.setFocusable(false);
        JLabel label = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        label.setFont(new Font("Main", Font.BOLD, 10));
        label.setBounds(20, 30, 250, 10);
        this.setTitle("Вітаємо");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(400, 100);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(label);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
            new GameFrame();
        }
    }
}

