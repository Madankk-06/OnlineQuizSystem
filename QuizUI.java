import java.awt.*;
import java.util.List;
import javax.swing.*;

public class QuizUI {

    int index = 0, score = 0, timeLeft = 10;
    List<Question> questions;

    JLabel questionLabel, timerLabel;
    JRadioButton[] options;
    ButtonGroup group;
    Timer timer;

    public QuizUI(String username) {

        try {
            questions = FileService.loadQuestions("./data/questions.txt");

            Color bg = new Color(18,18,18);
            Color card = new Color(30,30,30);
            Color accent = new Color(0,120,215);

            JFrame frame = new JFrame("Quiz");
            frame.setSize(700, 450);
            frame.getContentPane().setBackground(bg);
            frame.setLayout(new BorderLayout(10,10));

            JPanel top = new JPanel(new BorderLayout());
            top.setBackground(card);

            questionLabel = new JLabel();
            questionLabel.setForeground(Color.WHITE);
            questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

            timerLabel = new JLabel("10");
            timerLabel.setForeground(Color.RED);

            top.add(questionLabel, BorderLayout.CENTER);
            top.add(timerLabel, BorderLayout.EAST);

            frame.add(top, BorderLayout.NORTH);

            JPanel center = new JPanel(new GridLayout(4,1,10,10));
            center.setBackground(bg);

            options = new JRadioButton[4];
            group = new ButtonGroup();

            for(int i=0;i<4;i++){
                options[i] = new JRadioButton();
                options[i].setBackground(card);
                options[i].setForeground(Color.WHITE);
                options[i].setFocusPainted(false);
                group.add(options[i]);
                center.add(options[i]);
            }

            frame.add(center, BorderLayout.CENTER);

            JButton next = new JButton("Next");
            next.setBackground(accent);
            next.setForeground(Color.WHITE);

            next.addActionListener(e -> {
                check();
                nextQ(frame, username);
            });

            frame.add(next, BorderLayout.SOUTH);

            load();
            startTimer(frame, username);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    void load(){
        Question q = questions.get(index);
        questionLabel.setText((index+1)+". "+q.question);

        for(int i=0;i<4;i++){
            options[i].setText(q.options[i]);
        }

        group.clearSelection();
        timeLeft = 10;
    }

    void check(){
        for(int i=0;i<4;i++){
            if(options[i].isSelected() && (i+1)==questions.get(index).correctAnswer){
                score++;
            }
        }
    }

    void nextQ(JFrame f, String user){
        timer.stop();
        index++;

        int total = questions.size();
double percent = (score * 100.0) / total;

JOptionPane.showMessageDialog(f,
        "Total Questions: " + total +
        "\nCorrect Answers: " + score +
        "\nPercentage: " + String.format("%.2f", percent) + "%"
);
        // SHOW GRAPH
        ResultUI.showResult(score, total);

        try {
            FileService.saveResult("./data/results.txt", user, score);
        } catch (Exception e) {}

        f.dispose();
    }
    void startTimer(JFrame f, String user){
        timer = new Timer(1000, e -> {
            timerLabel.setText("Time: "+timeLeft);
            timeLeft--;

            if(timeLeft<0){
                check();
                nextQ(f,user);
            }
        });
        timer.start();
    }
}