import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MiConversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox<Moneda> cmbOrigen;
	private JComboBox<Moneda> cmbDestino;
	private JLabel lbl;
	private JFormattedTextField txt;

	public class Moneda {
		private String nombre; // el nombre de la moneda
		private double tasa; // la tasa de cambio con respecto al dolar

		public Moneda(String nombre, double tasa) {
			this.nombre = nombre;
			this.tasa = tasa;
		}

		public String getNombre() {
			return nombre;
		}

		public double getTasa() {
			return tasa;
		}

		public String toString() {
			return nombre; // para mostrar el nombre en el combo box
		}

		public double convertir(Moneda otra) {
			// regla de tres simple para convertir una moneda a otra
			return this.tasa / otra.tasa;
		}
	}

	public Moneda dolar = new Moneda("Dolar", 1.00);
	public Moneda euro = new Moneda("Euro", 0.92);
	public Moneda pesosmxn = new Moneda("Pesos MXN", 17.07);
	public Moneda libra = new Moneda("Libra", 0.79);
	public Moneda soles = new Moneda("Soles", 3.64);
	public double valorInput = 0.00; // agregamos esta línea para declarar la variable

	public NumberFormat nf = NumberFormat.getNumberInstance(); // para validar y formatear numeros

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txt = new JFormattedTextField(nf); // usamos un JFormattedTextField con el NumberFormat
		txt.setBounds(26, 32, 127, 23);
		frame.getContentPane().add(txt);

		// creamos dos combo box para elegir la moneda de origen y la moneda de destino
		cmbOrigen = new JComboBox<Moneda>();
		cmbOrigen.setModel(new DefaultComboBoxModel<>(new Moneda[] { dolar, euro, pesosmxn, libra, soles }));
		cmbOrigen.setBounds(26, 79, 127, 34);
		frame.getContentPane().add(cmbOrigen);

		cmbDestino = new JComboBox<Moneda>();
		cmbDestino.setModel(new DefaultComboBoxModel<>(new Moneda[] { dolar, euro, pesosmxn, libra, soles }));
		cmbDestino.setBounds(173, 79, 127, 34);
		frame.getContentPane().add(cmbDestino);

		// evento del boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(318, 85, 89, 23);
		frame.getContentPane().add(btn);

		lbl = new JLabel("00.00");
		lbl.setBounds(173, 32, 76, 23);
		frame.getContentPane().add(lbl);
	}

	public void Convertir() {
		if (Validar(txt.getText())) {
			Moneda origen = (Moneda) cmbOrigen.getSelectedItem(); // obtenemos la moneda de origen
			Moneda destino = (Moneda) cmbDestino.getSelectedItem(); // obtenemos la moneda de destino
			double res = destino.convertir(origen) * valorInput; // modificamos esta línea para multiplicar por el valor
																	// ingresado
			lbl.setText(Redondear(res)); // mostramos el resultado redondeado
		}

	}

	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}

	public boolean Validar(String texto) {
		try {
			double x = nf.parse(texto).doubleValue(); // usamos el NumberFormat para parsear el texto
			if (x > 0)
				;
			valorInput = x;
			return true;
		} catch (Exception e) {
			lbl.setText("Solo Numeros!!");
			return false;
		}
	}
}
