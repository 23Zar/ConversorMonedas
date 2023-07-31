import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrencyConverterFrame extends JFrame {


    private static final long serialVersionUID = 1L;
    private JButton btn;
    private JComboBox<Moneda> cmbOrigen;
    private JComboBox<Moneda> cmbDestino;
    private JLabel lbl;
    private JFormattedTextField txt;
    private double valorInput = 0.00;

    private NumberFormat nf = NumberFormat.getNumberInstance();

    public CurrencyConverterFrame() {
        initialize();
    }

    private void initialize() {
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setTitle("Alura Conversor");

        txt = new JFormattedTextField(nf);
        txt.setBounds(26, 32, 127, 23);
        this.getContentPane().add(txt);

        cmbOrigen = new JComboBox<Moneda>();
        cmbOrigen.setModel(new DefaultComboBoxModel<>(new Moneda[] { new Moneda("Dolar", 1.00),
                new Moneda("Euro", 0.92), new Moneda("Pesos MXN", 17.07), new Moneda("Libra", 0.79),
                new Moneda("Soles", 3.64) }));
        cmbOrigen.setBounds(26, 79, 127, 34);
        this.getContentPane().add(cmbOrigen);

        cmbDestino = new JComboBox<Moneda>();
        cmbDestino.setModel(new DefaultComboBoxModel<>(new Moneda[] { new Moneda("Dolar", 1.00),
                new Moneda("Euro", 0.92), new Moneda("Pesos MXN", 17.07), new Moneda("Libra", 0.79),
                new Moneda("Soles", 3.64) }));
        cmbDestino.setBounds(173, 79, 127, 34);
        this.getContentPane().add(cmbDestino);

        btn = new JButton("Convertir");
        btn.addMouseListener(new ConvertirButtonListener());
        btn.setBounds(318, 85, 89, 23);
        this.getContentPane().add(btn);

        lbl = new JLabel("00.00");
        lbl.setBounds(173, 32, 76, 23);
        this.getContentPane().add(lbl);
    }

    private class ConvertirButtonListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (validar(txt.getText())) {
                Moneda origen = (Moneda) cmbOrigen.getSelectedItem();
                Moneda destino = (Moneda) cmbDestino.getSelectedItem();
                double res = destino.convertir(origen) * valorInput;
                lbl.setText(redondear(res));
            }
        }

        private String redondear(double valor) {
            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.HALF_UP);
            return df.format(valor);
        }

        private boolean validar(String texto) {
            try {
                double x = nf.parse(texto).doubleValue();
                if (x > 0) {
                    valorInput = x;
                    return true;
                } else {
                    lbl.setText("El valor debe ser mayor que 0.");
                    return false;
                }
            } catch (Exception e) {
                lbl.setText("Solo se permiten números.");
                return false;
            }
        }
    }
}



