public class Main {
    public static void main(String[] args) {
        DecoderAbstraction decoder = new Decoder();

        decoder.decode("12");
        decoder.decode("3");
        decoder.decode("");
        decoder.decode("4");
        decoder.decode("a");
        decoder.decode("b");
        decoder.decode("cd");

        System.out.println(decoder.getBuffer());
    }
}
