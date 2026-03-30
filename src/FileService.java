import java.io.*;
import java.util.*;

public class FileService {

    public static List<User> loadUsers(String path) throws Exception {
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            users.add(new User(data[0], data[1]));
        }
        br.close();
        return users;
    }

    public static List<Question> loadQuestions(String path) throws Exception {

    List<Question> questions = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(path));
    String line;

    while ((line = br.readLine()) != null) {

        String[] data = line.split("\\|");

        int id = Integer.parseInt(data[0]);
        String difficulty = data[1];
        String questionText = data[2];

        String[] options = {data[3], data[4], data[5], data[6]};
        int correct = Integer.parseInt(data[7]);

        questions.add(new Question(id, difficulty, questionText, options, correct));
    }

    br.close();
    return questions;
}
    

    public static void saveResult(String path, String username, int score) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(username + "," + score);
        bw.newLine();
        bw.close();
    }
    public static void showLeaderboard(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        System.out.println("\nLeaderboard:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    br.close();
    }
}