import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LinesComponent extends JComponent {
    private static class Line {
        final int startX;
        final int startY;
        final int endX;
        final int endY;


        public Line(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    private final LinkedList<Line> lines = new LinkedList<>();

    public void addLine(int startX, int startY, int endX, int endY) {
        lines.add(new Line(startX, startY, endX, endY));
        repaint();
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);

        g2.setStroke(new BasicStroke(5));

        g2.setColor(Color.green);
        for (Line line : lines) {
            g2.drawLine(line.startX, line.startY, line.endX, line.endY);
        }
    }
}

class Application extends JFrame implements ActionListener {
    LinesComponent linesComponent = new LinesComponent();
    private final Map<String, JLabel> labelMap = new HashMap<>();

    private boolean penDown;
    private int posX;
    private int posY;
    private int corner;

    private final String[] buttons = {
            "Left",
            "Right",
            "Forward",
            "Pen Up",
            "Pen Down",
            "Clean",
            "Quit"
    };

    private final String[] labels = {
            "Położenie:",
            "Kierunek:",
            "Pióro:"
    };

    public Application() {
        super("Logo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        resetBoard();
        initLayout();
        initButtons();
        initTextFields();
        initPaint();
        setVisible(true);
    }

    private void initLayout() {
        setLayout(new BorderLayout());
    }

    private void initPaint() {
        add(linesComponent, BorderLayout.CENTER);
    }

    private void initButtons() {
        JPanel buttonsPanel = new JPanel();

        for (String button : buttons) {
            JButton tempButton = new JButton(button);
            buttonsPanel.add(tempButton);
            tempButton.addActionListener(this);
        }

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void resetBoard() {
        posX = 784 / 2;
        posY = 698 / 2;
        corner = 0;
        penDown = false;

        linesComponent.clearLines();
    }

    private void initTextFields() {
        JPanel textFieldsPanel = new JPanel();

        for (String label : labels) {
            JLabel tempLabel = new JLabel(label);
            tempLabel.setBorder(BorderFactory.createCompoundBorder(tempLabel.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            labelMap.put(label, tempLabel);
            textFieldsPanel.add(tempLabel);
        }

        add(textFieldsPanel, BorderLayout.NORTH);
        updateLabels();
    }

    private int updateCorner(int dir) {
        int tempCorner = corner + dir;

        if (tempCorner > 270) {
            return 0;
        } else if(tempCorner < 0) {
            return 270;
        }

        return tempCorner;
    }

    private void addCornerToPos() {
        int addX = 0;
        int addY = 0;

        if (corner == 0) {
            addY = -25;
        } else if (corner == 90) {
            addX = 25;
        } else if (corner == 180) {
            addY = 25;
        } else {
            addX = -25;
        }

        if (penDown) {
            linesComponent.addLine(posX, posY, posX + addX, posY + addY);
        }

        posX += addX;
        posY += addY;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();

        switch (command) {
            case "Left": corner = updateCorner(-90); break;
            case "Right": corner = updateCorner(90);  break;
            case "Forward": addCornerToPos();  break;
            case "Pen Up": penDown = false;  break;
            case "Pen Down": penDown = true;  break;
            case "Clean": resetBoard();  break;
            case "Quit": System.exit(0);
        }
        updateLabels();
    }

    private void updateLabels() {
        JLabel temp;
        String text;

        temp = labelMap.get(labels[0]);
        text = String.format("%s (%dx, %dy)", labels[0], posX, posY);
        temp.setText(text);

        temp = labelMap.get(labels[1]);
        text = String.format("%s %s %d", labels[1], cornerToString(), corner);
        temp.setText(text);

        temp = labelMap.get(labels[2]);
        text = String.format("%s %s", labels[2], penDown ? "Opuszczone" : "Podniesione");
        temp.setText(text);
    }

    private String cornerToString() {
        switch (corner) {
            case 0: return "Góra";
            case 90: return "Prawo";
            case 180: return "Dół";
            case 270: return "Lewo";
        }
        return "";
    }
}

public class Start {
    public static void main(String[] args) {
        Runnable initFrame = Application::new;
        SwingUtilities.invokeLater(initFrame);
    }
}
