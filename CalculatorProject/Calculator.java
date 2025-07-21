import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener, KeyListener {

    private JTextField display;
    private String currentInput = "";
    private String operator = "";
    private double result = 0;
    private boolean startNewInput = true;

    public Calculator() {
        setTitle("Swing Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.addKeyListener(this);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "C", "^", "/", "*",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "=",
            "0", ".", "", ""
        };

        for (String text : buttons) {
            if (text.equals("")) {
                buttonPanel.add(new JLabel());
            } else {
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.BOLD, 24));
                button.addActionListener(this);
                button.setFocusable(false);
                buttonPanel.add(button);
            }
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Handle Button Presses
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        handleInput(input);
    }

    // Handle Keyboard Input
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (Character.isDigit(key) || key == '.') {
            handleInput(String.valueOf(key));
        } else if (key == '+' || key == '-' || key == '*' || key == '/') {
            handleInput(String.valueOf(key));
        } else if (key == '\n' || key == '=') {
            handleInput("=");
        } else if (key == '\b') {
            handleInput("^");
        } else if (key == 27) {
            handleInput("C");
        }
    }

    public void keyReleased(KeyEvent e) {}

    private void handleInput(String input) {
        switch (input) {
            case "C":
                currentInput = "";
                operator = "";
                result = 0;
                display.setText("0");
                break;

            case "^":
                if (currentInput.length() > 0) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    display.setText(currentInput.isEmpty() ? "0" : currentInput);
                }
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                compute();
                operator = input;
                startNewInput = true;
                break;

            case "=":
                compute();
                operator = "";
                display.setText(String.valueOf(result));
                startNewInput = true;
                break;

            case ".":
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    display.setText(currentInput);
                }
                break;

            default:  // Number
                if (startNewInput) {
                    currentInput = input;
                    startNewInput = false;
                } else {
                    currentInput += input;
                }
                display.setText(currentInput);
        }
    }

    private void compute() {
        try {
            double inputVal = currentInput.isEmpty() ? 0 : Double.parseDouble(currentInput);

            switch (operator) {
                case "":
                    result = inputVal;
                    break;
                case "+":
                    result += inputVal;
                    break;
                case "-":
                    result -= inputVal;
                    break;
                case "*":
                    result *= inputVal;
                    break;
                case "/":
                    if (inputVal == 0) {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result /= inputVal;
                    break;
            }
            currentInput = String.valueOf(result);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
