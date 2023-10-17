import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ventana{
    private JPanel principal;
    private JComboBox cboPrioridad;
    private JLabel lblAviso;
    private JTextField txtNombre;
    private JTextField txtSintomas;
    private JLabel lblSintomas;
    private JLabel lblNombre;
    private JButton btnAgregar;
    private JList lstListaPacientes;
    private JButton btnAtender;

    private Clinica clinica= new Clinica();
    private  DefaultListModel dlm = new DefaultListModel();

    public void llenarJlist(){
        dlm.removeAllElements();
        Stream<Paciente> ordenado = clinica.listarPacientes().stream().sorted();
        List<Paciente> listaOrdenada = ordenado.collect(Collectors.toList());
        for (Paciente p:listaOrdenada){
            dlm.addElement(p);

        }
        lstListaPacientes.setModel(dlm);
    }

    public Ventana() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int prioridad= Integer.parseInt(cboPrioridad.getSelectedItem().toString());
                String nombre= txtNombre.getText();
                String sintomas=txtSintomas.getText();
                Paciente nuevo=new Paciente(prioridad,nombre,sintomas);
                clinica.encolar(nuevo);
                llenarJlist();
            }
        });
        btnAtender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Paciente atendido=clinica.atentido();
                    JOptionPane.showMessageDialog(null, "Paciente atendido" + atendido);
                    llenarJlist();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
