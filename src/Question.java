public class Question {
    int id;
    String difficulty;
    String question;
    String[] options;
    int correctAnswer;

    public Question(int id, String difficulty, String question, String[] options, int correctAnswer) {
        this.id = id;
        this.difficulty = difficulty;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}