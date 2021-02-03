public class Decoder extends DecoderAbstraction {
    private final StringBuilder stringBuilder = new StringBuilder();
    private final StringBuilder subStringBuilder = new StringBuilder();

    private int repeatElements = 1;

    private boolean subStringBegin = false;
    private boolean command = false;

    @Override
    public void decode(String input) {
        for (char c: input.replaceAll("\\s+", "").toCharArray()) {
            if (this.command) {
                handleCommand(c);
            } else {
                if (c == '0') {
                    this.command = true;
                } else {
                    if (this.subStringBegin) {
                        this.subStringBuilder.append(c);
                    } else {
                        writeToBuffer(String.valueOf(c));
                    }
                }
            }
        }
    }

    private void handleCommand(char c) {
        if (c == '0') {
            reset();
        } else if (c == '1') {
            if (!this.subStringBegin) {
                this.subStringBegin = true;
            } else {
                this.subStringBegin = false;
                writeToBuffer(this.subStringBuilder.toString());
                this.subStringBuilder.setLength(0);
            }
        } else {
            this.repeatElements = Character.getNumericValue(c);
        }
        this.command = false;
    }

    private void writeToBuffer(String s) {
        this.stringBuilder.append(s.repeat(this.repeatElements));
        this.repeatElements = 1;
    }

    @Override
    public String getBuffer() {
        return this.stringBuilder.toString();
    }

    @Override
    public void reset() {
        this.stringBuilder.setLength(0);
        this.subStringBuilder.setLength(0);
        this.subStringBegin = false;
        this.command = false;
    }
}
