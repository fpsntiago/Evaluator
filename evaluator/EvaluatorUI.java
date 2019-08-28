package edu.csc413.calculator.evaluator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import edu.csc413.calculator.operators.Constants;

public class EvaluatorUI extends JFrame implements ActionListener {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -6884244063721926338L;
	private TextField txField = new TextField();
	private Panel buttonPanel = new Panel();
	private Evaluator cal = new Evaluator();

	// total of 20 buttons on the calculator,
	// numbered from left to right, top to bottom
	// bText[] array contains the text for corresponding buttons
	private static final String[] bText = { Constants.SEVEN, Constants.EIGHT, Constants.NINE, Constants.ADD,
			Constants.FOUR, Constants.FIVE, Constants.SIX, Constants.SUBTRACT, Constants.ONE, Constants.TWO,
			Constants.THREE, Constants.MULTIPLY, Constants.ZERO, Constants.POWER, Constants.EQUAL, Constants.DIVIDE,
			Constants.OPEN_BARCE, Constants.CLOSE_BARCE, Constants.C, Constants.CE };

	/**
	 * C is for clear, clears entire expression CE is for clear expression, clears
	 * last entry up until the last operator.
	 */
	private JButton[] buttons = new JButton[bText.length];

	/**
	 * Main method of the application
	 * 
	 * @param argv - Array of command line arguments
	 */
	public static void main(String argv[]) {
		EvaluatorUI calc = new EvaluatorUI();
		calc.init();
	}

	/**
	 * Default constructor
	 */
	public EvaluatorUI() {
	}

	/**
	 * Initialize the UI
	 */
	private void init() {
		setLayout(new BorderLayout());
		this.txField.setPreferredSize(new Dimension(600, 50));
		this.txField.setFont(new Font("Courier", Font.BOLD, 28));

		add(txField, BorderLayout.NORTH);
		txField.setEditable(false);

		add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(new GridLayout(5, 4));

		// create 20 buttons with corresponding text in bText[] array
		JButton bt;
		for (int i = 0; i < EvaluatorUI.bText.length; i++) {
			bt = new JButton(bText[i]);
			bt.setFont(new Font("Courier", Font.BOLD, 28));
			buttons[i] = bt;
		}

		// add buttons to button panel
		for (int i = 0; i < EvaluatorUI.bText.length; i++) {
			buttonPanel.add(buttons[i]);
		}

		// set up buttons to listen for mouse input
		for (int i = 0; i < EvaluatorUI.bText.length; i++) {
			buttons[i].addActionListener(this);
		}

		setTitle("Calculator");
		setSize(400, 400);
		setLocationByPlatform(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Action to be performed on click of button
	 */
	public void actionPerformed(ActionEvent event) {
		switch (((JButton) event.getSource()).getText()) {
		case Constants.ZERO:
		case Constants.ONE:
		case Constants.TWO:
		case Constants.THREE:
		case Constants.FOUR:
		case Constants.FIVE:
		case Constants.SIX:
		case Constants.SEVEN:
		case Constants.EIGHT:
		case Constants.NINE:
		case Constants.ADD:
		case Constants.SUBTRACT:
		case Constants.MULTIPLY:
		case Constants.DIVIDE:
		case Constants.POWER:
		case Constants.OPEN_BARCE:
		case Constants.CLOSE_BARCE:
			txField.setText(txField.getText() + ((JButton) event.getSource()).getText());
			break;
		case Constants.CE:
		case Constants.C:
			txField.setText(Constants.EMPTY_STRING_SPACE);
			break;
		case Constants.EQUAL:
			try {
				txField.setText(Integer.toString(cal.eval(txField.getText())));
			} catch (Exception e) {

				txField.setText(Constants.EMPTY_STRING_SPACE);
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		default:
			break;
		}

	}
}
