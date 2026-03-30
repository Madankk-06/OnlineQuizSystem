import java.util.*;

public class QuizService {

    public static int startQuiz(List<Question> questions) {
    Scanner sc = new Scanner(System.in);
    int score = 0;

    for (Question q : questions) {

        System.out.println("\n" + q.question);
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " + q.options[i]);
        }

        long start = System.currentTimeMillis();

        int ans = sc.nextInt();

        long end = System.currentTimeMillis();

        if ((end - start) > 10000) {
            System.out.println("Time Up!");
            continue;
        }

        if (ans == q.correctAnswer) {
            score++;
        }
    }

    return score;
}
    
}