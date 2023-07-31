
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ConvertirButtonListener implements ActionListener {

    private JLabel lbl;
    private JFormattedTextField txt;
    private JComboBox<Moneda> cmbOrigen;
    private JComboBox<Moneda> cmbDestino;
    private double valorInput = 0.00;

    private NumberFormat nf = NumberFormat.getNumberInstance();

    public ConvertirButtonListener(JLabel lbl, JFormattedTextField txt, JComboBox<Moneda> cmbOrigen,
            JComboBox<Moneda> cmbDestino) {
        this.lbl = lbl;
        this.txt = txt;
        this.cmbOrigen = cmbOrigen;
        this.cmbDestino = cmbDestino;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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


