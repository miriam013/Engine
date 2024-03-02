package Engine_v1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine implements ActionListener {

	// MARCO DE LA VENTANA
	private JFrame frame;

	// PANEL GENERAL QUE OCUPA TODA LA VENTANA
	private JPanel contentPanel;

	// PANEL NORTE QUE CONTIENE EL DISPLAY
	private JPanel displayPanel;

	// PANEL SUR QUE CONTIENE LOS BOTONES
	private JPanel buttonPanel;

	// PANEL SU QUE CONTIENE EL BOTON BACK
	private JPanel equalPanel;

	// DISPLAY
	private JTextField display;

	// BOTONES
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton back;
	private JButton divide;
	private JButton multiply;
	private JButton subtract;
	private JButton add;
	private JButton equal;
	private JButton reset;

	// TIPO DE BOTON
	private enum ButtonType {
		REGULAR, OPERATOR
	}

	// ALMACENA TEMPORALMENTE DETERMINADOS VALORES
	private int num1, num2, result;
	private char operation;

	/**
	 * Constructora
	 */
	public Engine() {

		this.frame = new JFrame();

		this.contentPanel = new JPanel();
		this.displayPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(4, 5, 20, 20));
		this.equalPanel = new JPanel();

		this.display = new JTextField(21);

		this.n0 = new JButton("0");
		this.n1 = new JButton("1");
		this.n2 = new JButton("2");
		this.n3 = new JButton("3");
		this.n4 = new JButton("4");
		this.n5 = new JButton("5");
		this.n6 = new JButton("6");
		this.n7 = new JButton("7");
		this.n8 = new JButton("8");
		this.n9 = new JButton("9");

		this.divide = new JButton("/");
		this.multiply = new JButton("x");
		this.subtract = new JButton("-");
		this.add = new JButton("+");
		this.back = new JButton("<=");
		this.equal = new JButton("=");
		this.reset = new JButton("R");
	}

	/**
	 * Diseño de los botones
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		_button.setForeground(Color.WHITE);
		_button.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		_button.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));

		if (_button.getText().equals("=")) {

			_button.setBorder(BorderFactory.createEmptyBorder(10, 117, 10, 117));
			_button.setBackground(Color.decode("#212F3C"));

		} else if (_type == ButtonType.REGULAR) {

			_button.setBackground(Color.decode("#5B2C6F"));

		} else {

			_button.setBackground(Color.decode("#212F3C"));

		}
	}

	/**
	 * Diseño del display
	 * @param _textField
	 */
	public void setFeaturesDisplay(JTextField _textField) {
		_textField.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		_textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		_textField.setEditable(false);
	}

	/**
	 * Configuracion de la ventana
	 */
	public void setSettings() {

		// DISEÑO DE LOS BOTONES

		setFeaturesButton(this.n0, ButtonType.REGULAR);
		setFeaturesButton(this.n1, ButtonType.REGULAR);
		setFeaturesButton(this.n2, ButtonType.REGULAR);
		setFeaturesButton(this.n3, ButtonType.REGULAR);
		setFeaturesButton(this.n4, ButtonType.REGULAR);
		setFeaturesButton(this.n5, ButtonType.REGULAR);
		setFeaturesButton(this.n6, ButtonType.REGULAR);
		setFeaturesButton(this.n7, ButtonType.REGULAR);
		setFeaturesButton(this.n8, ButtonType.REGULAR);
		setFeaturesButton(this.n9, ButtonType.REGULAR);

		setFeaturesButton(this.back, ButtonType.OPERATOR);

		setFeaturesButton(this.divide, ButtonType.OPERATOR);
		setFeaturesButton(this.multiply, ButtonType.OPERATOR);
		setFeaturesButton(this.subtract, ButtonType.OPERATOR);
		setFeaturesButton(this.add, ButtonType.OPERATOR);
		setFeaturesButton(this.equal, ButtonType.OPERATOR);
		setFeaturesButton(this.reset, ButtonType.OPERATOR);

		// DISEÑO DEL DISPLAY

		setFeaturesDisplay(this.display);

		// AÑADE LOS BOTONES AL PANEL

		this.buttonPanel.add(this.n7);
		this.buttonPanel.add(this.n8);
		this.buttonPanel.add(this.n9);
		this.buttonPanel.add(this.divide);
		this.buttonPanel.add(this.n4);
		this.buttonPanel.add(this.n5);
		this.buttonPanel.add(this.n6);
		this.buttonPanel.add(this.multiply);
		this.buttonPanel.add(this.n1);
		this.buttonPanel.add(this.n2);
		this.buttonPanel.add(this.n3);
		this.buttonPanel.add(this.subtract);
		this.buttonPanel.add(this.n0);
		this.buttonPanel.add(this.reset);
		this.buttonPanel.add(this.back);
		this.buttonPanel.add(this.add);

		this.displayPanel.add(this.display);

		this.equalPanel.add(this.equal);

		this.contentPanel.add(this.displayPanel);
		this.contentPanel.add(this.buttonPanel);
		this.contentPanel.add(this.equalPanel);

		this.frame.add(this.contentPanel);

		// DISEÑO DE LOS PANELES

		this.buttonPanel.setBackground(Color.BLACK);
		this.displayPanel.setBackground(Color.BLACK);
		this.equalPanel.setBackground(Color.BLACK);
		this.contentPanel.setBackground(Color.BLACK);

		// SETTINGS DE LA VENTANA

		this.frame.setTitle("Engine v1");
		this.frame.setLocation(100, 100);
		this.frame.setSize(300, 500);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Eventos de los botones
	 */
	public void addActionEvent() {
		this.n0.addActionListener(this);
		this.n1.addActionListener(this);
		this.n2.addActionListener(this);
		this.n3.addActionListener(this);
		this.n4.addActionListener(this);
		this.n5.addActionListener(this);
		this.n6.addActionListener(this);
		this.n7.addActionListener(this);
		this.n8.addActionListener(this);
		this.n9.addActionListener(this);

		this.back.addActionListener(this);
		this.add.addActionListener(this);
		this.subtract.addActionListener(this);
		this.multiply.addActionListener(this);
		this.divide.addActionListener(this);

		this.reset.addActionListener(this);
		this.equal.addActionListener(this);
	}

	/**
	 * Metodo que realiza la operacion correspondiente
	 */
	public void operation() {
		/*
		 * SI EL OPERADOR ES UNA 'x', LA OPERACION QUE SE QUIERE REALIZAR ES LA
		 * MULTIPLICACION Y SE CAMBIAR EL CARACTER 'x' POR EL '*' QUE ES EL CARACTER
		 * GRACIAS AL QUE SE MULTIPLICA
		 */
		if (this.operation == 'x') {
			this.operation = '*';
		}
		this.result = 0;
		switch (this.operation) {
		case '+':
			this.result = this.num1 + this.num2;
			break;

		case '-':
			this.result = this.num1 - this.num2;
			break;

		case '*':
			this.result = this.num1 * this.num2;
			break;

		case '/':
			this.result = this.num1 / this.num2;
			break;
		}
		display.setText(String.valueOf(this.result));
	}

	/**
	 * Metodo que contiene la logica de la calculadora
	 */
	public void actionPerformed(ActionEvent e) {
		setFeaturesDisplay(display);
		Object source = e.getSource();
		String input_text = e.getActionCommand();
		String _num1 = "0";
		String _num2 = "0";

		if (source == back) { // PULSAR SOBRE EL BOTON "<=", BORRA EL ULTIMO CARACTER

			this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));

		} else if (source == equal) { // PULSAR SOBRE EL BOTON '='

			String[] operadores = { "+", "-", "/", "x" };

			ArrayList<Integer> posicionesOperadores = new ArrayList<>();

			// ENCONTRAR TODOS LOS OPERADORES
			for (String operador : operadores) {
				int index = -1;

				/*
				 * CUANDO SE ENCUENTRA EL PRIMER OPERADOR, SE ALMACENA Y EL BUCLE CONTINUA,
				 * CUANDO NO SE ENCUENTREN MÁS OPERADORES, indexOf DEVUELVE -1 Y EL WHILE
				 * FINALIZA
				 */
				while ((index = display.getText().indexOf(operador, index + 1)) != -1) {
					posicionesOperadores.add(index);
				}
			}

			// ORDENAR EL ORDEN DE APARICION DE LOS OPERADORES
			for (int i = 1; i < posicionesOperadores.size(); i++) {
				int temp = posicionesOperadores.get(i);
				int j = i - 1;

				while (j >= 0 && posicionesOperadores.get(j) > temp) {
					posicionesOperadores.set(j + 1, posicionesOperadores.get(j));
					j--;
				}

				posicionesOperadores.set(j + 1, temp);
			}

			if (this.display.getText().startsWith("-") || this.display.getText().startsWith("+")) {

				this.operation = display.getText().charAt(posicionesOperadores.get(1));
				_num1 = display.getText().substring(0, posicionesOperadores.get(1));
				_num2 = display.getText().substring((posicionesOperadores.get(1) + 1), this.display.getText().length());

			} else if ((posicionesOperadores.size() == 1)
					|| (this.display.getText().indexOf("x") < this.display.getText().indexOf("-"))
					|| (this.display.getText().indexOf("x") < this.display.getText().indexOf("+"))) {

				this.operation = display.getText().charAt(posicionesOperadores.get(0));
				_num1 = display.getText().substring(0, posicionesOperadores.get(0));
				_num2 = display.getText().substring(posicionesOperadores.get(0) + 1);

			} else {

				this.operation = display.getText().charAt(posicionesOperadores.get(1));
				_num1 = display.getText().substring(0, posicionesOperadores.get(1) - 1);
				_num2 = display.getText().substring((posicionesOperadores.get(1) + 1), this.display.getText().length());

			}

			this.num1 = Integer.valueOf(_num1);
			this.num2 = Integer.valueOf(_num2);

			operation();

		} else if (source == reset) { // PULSAR SOBRE RESET
			this.operation = '\0';
			this.num1 = 0;
			this.num2 = 0;
			this.result = 0;
			display.setText("");

			// SI EL USUARIO NO PULSAR NI '=' NI RESET SIGNIFICA EL QUIERE SEGUIR
			// ENCADENANDO CARACTERES
		} else {

			display.setText(display.getText() + input_text);

		}

	}

	/**
	 * Metodo para iniciar la calculadora
	 */
	public void start() {
		new Engine();
		addActionEvent();
		setSettings();
	}
}