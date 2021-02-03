public class Main {
    public static void main(String[] args) {
        PasswordCracker passwordCracker = new PasswordCracker();
        System.out.println(passwordCracker.getPassword("127.0.0.1", 55555));
    }
}
