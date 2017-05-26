/**
 * Java. TeRoundButtonsCalculator
 *
 * @author Ternyuk Igor
 * @version 1.0 dated May 25, 2017
 */
package teroundbuttonscalculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import teroundbuttonscalculator.Model.Operation;

public class View {

    private final String TITLE_OF_WINDOW = "TeMetalCalculator";
    private final int WINDOW_WIDTH = 365, WINDOW_HEIGHT = 500, DY = 28;
    private final int NUM_BUTTON_ROW = 5, NUM_BUTTON_COLUMN = 4, GRID_LAYOUT_GAP = 5;
    private final int INPUT_FIELD_HEIGHT = 50;    
    private final Font fontBtn = new Font("Arial", Font.PLAIN, 35);
    private final Font fontInput = new Font("Arial", Font.PLAIN, 40);
    private final String[] btnCaptions = {"<", "C", "^", "/", "7", "8", "9", "-", "4", "5", "6", "+", "1", "2",
                "3", "*", "0", ".", "+/-", "="};
    private final ArrayList<JButton> buttons;
    private final Model model = new Model();
    private double first, second;
    private Operation op;
    private final JFrame window;
    private final JTextField input;
    private final JPanel btnPanel;

    public View() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        window = new JFrame(TITLE_OF_WINDOW);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(true);
        input = new JTextField();
        input.setBounds(GRID_LAYOUT_GAP, GRID_LAYOUT_GAP, WINDOW_WIDTH - 2
                * GRID_LAYOUT_GAP, INPUT_FIELD_HEIGHT);
        input.setFont(fontInput);
        input.setHorizontalAlignment(JTextField.RIGHT);
        input.setForeground(Color.BLACK);
        input.setBackground(Color.WHITE);
        input.setEditable(false);
        btnPanel = new JPanel();
        buttons = new ArrayList<>();
        GridLayout gl = new GridLayout(NUM_BUTTON_ROW, NUM_BUTTON_COLUMN,
                GRID_LAYOUT_GAP, GRID_LAYOUT_GAP);
        for (int i = 0; i < NUM_BUTTON_ROW * NUM_BUTTON_COLUMN; ++i) {
            JButton btn = new JButton(btnCaptions[i]);
            btn.setFont(fontBtn);
            btnPanel.add(btn);
            if (i == 0) {
                //This button clears one symbol from the right side of the input area
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        String text = input.getText();
                        if(text.length() > 0){
                            text = text.substring(0, text.length() - 1);
                            input.setText(text);
                        }
                    }
                });
            } else if (i == 1) {
                //This button clears all symbols
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        input.setText("");
                    }
                });

            } else if(i == 2 || i == 3 || i == 7 || i == 11 || i == 15){
                //These buttons are operation buttons
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        first = Double.valueOf(input.getText());
                        switch (btn.getText()) {
                            case "+":
                                op = Operation.ADDITION;
                                break;
                            case "-":
                                op = Operation.SUBTRACTION;
                                break;
                            case "*":
                                op = Operation.MULTIPLICATION;
                                break;
                            case "/":
                                op = Operation.DIVISION;
                                break;
                            case "^":
                                op = Operation.POWER;
                                break;
                            default:
                                break;
                        }
                        input.setText("");
                    }
                });
                
            } else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10)
                    || (i >= 12 && i <= 14) || i == 16) {
                //These buttons are number buttons
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        input.setText(input.getText() + btn.getText());
                    }
                });

            } else if (i == 17) {
                //This button adds separator
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        String text = input.getText();
                        if (!text.contains(".")) {
                            input.setText(input.getText() + btn.getText());
                        }
                    }
                });
            } else if (i == 18) {
                //This button changes the sign of the value in the text area
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        String text = input.getText();
                        if(!text.isEmpty()){
                            double value = Double.parseDouble(text);
                            value = -value;
                            input.setText(String.valueOf(value));
                        }
                    }
                });
            } else if(i == 19){
                //This button changes the sign of the value in the text area
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        second = Double.valueOf(input.getText());
                        model.calculate(first, second, op);
                        String result = String.format("%10.5f", model.getDigitalResult());
                        input.setText(model.getTextResult());
                    }
                });
            }
        }
        btnPanel.setLayout(gl);
        btnPanel.setBounds(GRID_LAYOUT_GAP, input.getBounds().y + INPUT_FIELD_HEIGHT
                + GRID_LAYOUT_GAP, WINDOW_WIDTH - 4 * GRID_LAYOUT_GAP, WINDOW_HEIGHT
                - INPUT_FIELD_HEIGHT - 3 * GRID_LAYOUT_GAP - DY);
        window.setLayout(null);
        window.add(input);
        window.add(btnPanel);
        window.setVisible(true);
    }
}
