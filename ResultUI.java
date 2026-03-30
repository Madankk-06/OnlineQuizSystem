public class ResultUI {
    
}
import javax.swing.*;
import java.awt.*;

public class ResultUI extends JPanel {

    int score;
    int total;

    public ResultUI(int score, int total) {
        this.score = score;
        this.total = total;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int barWidth = 100;

        int correctHeight = (int)((score * 1.0 / total) * 200);
        int wrongHeight = 200 - correctHeight;

        // Correct (Green)
        g.setColor(Color.GREEN);
        g.fillRect(150, height - correctHeight - 50, barWidth, correctHeight);

        // Wrong (Red)
        g.setColor(Color.RED);
        g.fillRect(300, height - wrongHeight - 50, barWidth, wrongHeight);

        g.setColor(Color.WHITE);
        g.drawString("Correct", 170, height - 20);
        g.drawString("Wrong", 320, height - 20);
    }

    public static void showResult(int score, int total) {
        JFrame frame = new JFrame("Result Analysis");
        frame.setSize(600, 400);

        ResultUI panel = new ResultUI(score, total);
        panel.setBackground(new Color(18,18,18));

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}