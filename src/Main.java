public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("Starting Online Quiz System...");

            new LoginUI(FileService.loadUsers("./data/users.txt"));

        } catch (Exception e) {
            System.out.println("Error starting application");
            e.printStackTrace();
        }

    }
}