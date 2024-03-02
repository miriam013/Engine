package Engine_v2;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase Engine
 */
public class Engine implements ActionListener {

	// MARCO DE LA VENTANA
	private JFrame frame;

	// PANEL GENERAL QUE OCUPA TODA LA VENTANA
	private JPanel contentPanel;

	// PANEL NORTE QUE CONTIENE LA BASE EN LA QUE SE OPERA Y LA MARCA DE LA
	// CALCULADORA
	private JPanel northPanel;

	// PANEL CENTRAL QUE CONTIENE EL DISPLAY
	private JPanel displayPanel;

	// PANEL SUR QUE CONTIENE LOS BOTONES
	private JPanel buttonPanel;

	// PANEL SUR QUE CONTIENE EL BOTON EQUAL
	private JPanel equalPanel;

	// DISPLAY
	private JTextField display;

	// COMPONENTES DE LA PARTE NORTE DE LA CALCULADORA
	private JButton brand;
	private JLabel base_label;
	private JLabel model;

	// BOTONES
	private JButton b2;
	private JButton b8;
	private JButton b10;
	private JButton b16;
	private JButton a;
	private JButton b;
	private JButton c;
	private JButton d;
	private JButton e;
	private JButton f;
	private JButton info;
	private JButton owner;
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

	private enum ButtonType { // TIPO DE BOTON
		REGULAR, OPERATOR, BASE, EXTRA, VARIABLES, MARCA
	}

	private enum Base { // TIPO DE BASE
		B2, B8, B10, B16
	}

	// ALMACENA TEMPORALMENTE DETERMINADOS VALORES
	private int num1, num2, result;
	private char operation;
	private boolean startOperation;
	private Base _type;

	/**
	 * Constructora
	 */
	public Engine() {
		this.frame = new JFrame();

		this.contentPanel = new JPanel();
		this.northPanel = new JPanel();
		this.northPanel.setLayout(new GridLayout(1, 3));
		this.displayPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(7, 5, 20, 20));
		this.equalPanel = new JPanel();

		this.brand = new JButton("CASIO");
		this.base_label = new JLabel("");
		this.model = new JLabel("fx-83GT X");

		this.display = new JTextField(21);

		this.b2 = new JButton("B2");
		this.b8 = new JButton("B8");
		this.b10 = new JButton("B10");
		this.b16 = new JButton("B16");
		this.a = new JButton("A");
		this.b = new JButton("B");
		this.c = new JButton("C");
		this.d = new JButton("D");
		this.e = new JButton("E");
		this.f = new JButton("F");
		this.info = new JButton("INFO");
		this.owner = new JButton("OWNER");

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
	 * 
	 * @param _button
	 * @param _type
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		_button.setForeground(Color.WHITE);
		_button.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		_button.setBorder(BorderFactory.createEmptyBorder(10, 1, 10, 1));

		switch (_type) {
		case MARCA:
			_button.setBackground(Color.GRAY);
			_button.setForeground(Color.BLACK);
			_button.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
			break;

		case REGULAR:
			_button.setBackground(Color.decode("#5B2C6F"));
			break;

		case OPERATOR:
			_button.setBackground(Color.decode("#212F3C"));
			break;

		case BASE:
			_button.setBackground(Color.decode("#647E68"));
			break;

		case VARIABLES:
			_button.setForeground(Color.BLACK);
			_button.setBackground(Color.decode("#B6EADA"));
			break;
		case EXTRA:
			_button.setBackground(Color.decode("#016A70"));
			break;
		}

		if (_button.getText().equals("=")) {

			_button.setFont(new Font("Tw Cen MT", Font.BOLD, 27));
			_button.setBorder(BorderFactory.createEmptyBorder(5, 170, 5, 170));
		}
	}

	/**
	 * Diseño del display
	 * 
	 * @param _textField
	 */
	public void setFeaturesDisplay(JTextField _textField) {
		_textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		_textField.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		_textField.setEditable(false);
	}

	/**
	 * Diseño de las etiquetas
	 * 
	 * @param _label
	 */
	public void setFeaturesLabel(JLabel _label) {
		_label.setForeground(Color.GRAY);

		if (_label == this.base_label) {
			_label.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
			_label.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		} else {
			_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			_label.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		}
	}

	/**
	 * Configuracion de la ventana
	 */
	public void setSettings() {

		// DISEÑO DE LA MARCA Y MODELO DE LA CALCULADORA
		setFeaturesLabel(this.base_label);
		setFeaturesButton(this.brand, ButtonType.MARCA);
		setFeaturesLabel(this.model);

		// DISEÑO DE LOS BOTONES
		setFeaturesButton(this.b2, ButtonType.BASE);
		setFeaturesButton(this.b8, ButtonType.BASE);
		setFeaturesButton(this.b10, ButtonType.BASE);
		setFeaturesButton(this.b16, ButtonType.BASE);
		setFeaturesButton(this.a, ButtonType.VARIABLES);
		setFeaturesButton(this.b, ButtonType.VARIABLES);
		setFeaturesButton(this.c, ButtonType.VARIABLES);
		setFeaturesButton(this.d, ButtonType.VARIABLES);
		setFeaturesButton(this.e, ButtonType.VARIABLES);
		setFeaturesButton(this.f, ButtonType.VARIABLES);
		setFeaturesButton(this.info, ButtonType.EXTRA);
		setFeaturesButton(this.owner, ButtonType.EXTRA);

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

		// AÑADE LA MARCA Y MODELO DE LA CALCULADORA
		this.northPanel.add(this.brand);
		this.northPanel.add(this.base_label);
		this.northPanel.add(this.model);

		// AÑADE LOS BOTONES AL PANEL
		this.buttonPanel.add(this.b2);
		this.buttonPanel.add(this.b8);
		this.buttonPanel.add(this.b10);
		this.buttonPanel.add(this.b16);
		this.buttonPanel.add(this.d);
		this.buttonPanel.add(this.e);
		this.buttonPanel.add(this.f);
		this.buttonPanel.add(this.info);
		this.buttonPanel.add(this.a);
		this.buttonPanel.add(this.b);
		this.buttonPanel.add(this.c);
		this.buttonPanel.add(this.owner);
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

		this.contentPanel.add(this.northPanel);
		this.contentPanel.add(this.displayPanel);
		this.contentPanel.add(this.buttonPanel);
		this.contentPanel.add(this.equalPanel);

		this.frame.add(this.contentPanel);

		// DISEÑO DE LOS PANELES
		this.northPanel.setBackground(Color.BLACK);
		this.buttonPanel.setBackground(Color.BLACK);
		this.displayPanel.setBackground(Color.BLACK);
		this.equalPanel.setBackground(Color.BLACK);
		this.contentPanel.setBackground(Color.BLACK);

		// SETTINGS DE LA VENTANA
		this.frame.setTitle("Engine v2");
		this.frame.setLocation(450, 50);
		this.frame.setSize(400, 650);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Eventos de los botones
	 */
	public void addActionEvent() {

		this.brand.addActionListener(this);

		this.b2.addActionListener(this);
		this.b8.addActionListener(this);
		this.b10.addActionListener(this);
		this.b16.addActionListener(this);

		this.a.addActionListener(this);
		this.b.addActionListener(this);
		this.c.addActionListener(this);
		this.d.addActionListener(this);
		this.e.addActionListener(this);
		this.f.addActionListener(this);

		this.info.addActionListener(this);
		this.owner.addActionListener(this);

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
		String _result = "0";

		switch (this.operation) { // DECIDE OPERACION
			case '+': this.result = this.num1 + this.num2;
				break;
	
			case '-': this.result = this.num1 - this.num2;
				break;
	
			case '*': this.result = this.num1 * this.num2;
				break;
	
			case '/': this.result = this.num1 / this.num2;
				break;
		}

		// SI LOS CALCULOS SON EN BASE DECIMAL NO ES NECESARIO CAMBIAR NADA
		switch (this._type) {
			case B2: _result = Integer.toBinaryString(this.result);
				break;
	
			case B8: _result = Integer.toOctalString(this.result);
				break;
	
			case B16: _result = Integer.toHexString(this.result);
				break;
	
			default: _result = String.valueOf(this.result);
				break;
		}

		this.display.setText(String.valueOf(_result).toUpperCase());

	}

	/**
	 * Metodo que contiene la logica de la calculadora
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String input_text = e.getActionCommand();
		String _num1 = "0";
		String _num2 = "0";

		switch (input_text) {
		case "CASIO": webCasio();
			break;

		case "OWNER": JOptionPane.showMessageDialog(null, "Proyecto: Calculadora v2\nPor: Miriam Ramírez", "Información",
				JOptionPane.INFORMATION_MESSAGE);
			break;

		case "INFO": JOptionPane.showMessageDialog(null, "Aqui va la info ⊂(◉‿◉)つ", "Información",
				JOptionPane.INFORMATION_MESSAGE);
			break;
		}

		if (source == reset) { // PULSAR SOBRE RESET

			this.operation = '\0';
			this.num1 = 0;
			this.num2 = 0;
			this.result = 0;
			this.base_label.setText("");
			this.display.setText("");
			this.startOperation = false;

		} else if (source == this.back) { // PULSAR SOBRE EL BOTON "<=", BORRA EL ULTIMO CARACTER

			this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));

		} else if (!startOperation) { // PARA EMPEZAR A OPERAR, HAY QUE SELECCIONAR UNA BASE

			handleBase(source);

		} else {

			if (source == equal) { // PULSAR SOBRE EL BOTON '='

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
					_num2 = display.getText().substring((posicionesOperadores.get(1) + 1),
							this.display.getText().length());

				} else if ((posicionesOperadores.size() == 1)
						|| (this.display.getText().indexOf("x") < this.display.getText().indexOf("-"))
						|| (this.display.getText().indexOf("x") < this.display.getText().indexOf("+"))) {

					this.operation = display.getText().charAt(posicionesOperadores.get(0));
					_num1 = display.getText().substring(0, posicionesOperadores.get(0));
					_num2 = display.getText().substring(posicionesOperadores.get(0) + 1);

				} else {

					this.operation = display.getText().charAt(posicionesOperadores.get(1));
					_num1 = display.getText().substring(0, posicionesOperadores.get(1) - 1);
					_num2 = display.getText().substring((posicionesOperadores.get(1) + 1),
							this.display.getText().length());

				}

				switch (_type) {
				case B2:
					this.num1 = Integer.parseInt(_num1, 2);
					this.num2 = Integer.parseInt(_num2, 2);
					break;

				case B8:
					this.num1 = Integer.parseInt(_num1, 8);
					this.num2 = Integer.parseInt(_num2, 8);
					break;

				case B10:
					this.num1 = Integer.parseInt(_num1, 10);
					this.num2 = Integer.parseInt(_num2, 10);
					break;

				case B16:
					this.num1 = Integer.parseInt(_num1, 16);
					this.num2 = Integer.parseInt(_num2, 16);
					break;

				}
				operation();

			} else if (source != this.b2 && source != this.b8 && source != this.b10 && source != this.b16 &&
					source != this.owner && source != this.info && source != this.brand) {

				this.display.setText(display.getText() + input_text);

			}
		}

	}

	/**
	 * Metodo que apunta a la web de Casio para ver los modelos de calculadoras 
	 * @param url
	 */
	public void webCasio() {
		try {
			Desktop.getDesktop().browse(new URI("https://www.casio.com/es/scientific-calculators/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que verifica el tipo de base con la que se va a operar 
	 * @param source
	 */
	public void handleBase(Object source) {
		this.startOperation = true;

		if (source == this.b2) {

			this.base_label.setText("Base: Binario");
			this._type = Base.B2;

		} else if (source == this.b8) {

			this.base_label.setText("Base: Octal");
			this._type = Base.B8;

		} else if (source == this.b10) {

			this.base_label.setText("Base: Decimal");
			this._type = Base.B10;

		} else if (source == this.b16) {

			this.base_label.setText("Base: Hexadecimal");
			this._type = Base.B16;

		} else {

			this.base_label.setText("Elige una base");
			this.startOperation = false;

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