

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

    public class ProductApp extends JFrame {

        private JTextField txtCustomerName;
        private JComboBox<String> cmbProduct;
        private JTextField txtPrice;
        private JButton btnProcess;

        public ProductApp() {
            setTitle("Product App");
            setSize(450, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 2, 10, 10));


            add(new JLabel("Customer Name:"));
            txtCustomerName = new JTextField();
            add(txtCustomerName);

            add(new JLabel("Select Product:"));
            cmbProduct = new JComboBox<>(new String[]{
                    "Cozy Nights Monarch",
                    "Res tonic Barbados",
                    "Sealy Phuket"});

            add(cmbProduct);

            add(new JLabel("Enter Price:"));
            txtPrice = new JTextField();
            add(txtPrice);

            add(new JLabel(""));
            btnProcess = new JButton("PROCESS");
            add(btnProcess);


            JMenuBar menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");
            JMenuItem mnuExit = new JMenuItem("Exit");
            fileMenu.add(mnuExit);

            JMenu toolsMenu = new JMenu("Tools");
            JMenuItem mnuProcess = new JMenuItem("Process");
            JMenuItem mnuClear = new JMenuItem("Clear");
            toolsMenu.add(mnuProcess);
            toolsMenu.add(mnuClear);

            menuBar.add(fileMenu);
            menuBar.add(toolsMenu);
            setJMenuBar(menuBar);

            btnProcess.addActionListener(e -> processSale());
            mnuProcess.addActionListener(e -> processSale());

            mnuClear.addActionListener(e -> {
                txtCustomerName.setText("");
                txtPrice.setText("");
                cmbProduct.setSelectedIndex(0);});


            mnuExit.addActionListener(e -> System.exit(0));
        }

        private void processSale() {
            try {
                String customerName = txtCustomerName.getText();
                String product = (String) cmbProduct.getSelectedItem();
                double price = Double.parseDouble(txtPrice.getText());

                if (customerName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter customer name");
                    return;
                }

                ProductCalculations calc = new ProductCalculations(price);
                double vat = calc.GetVat();
                double commission = calc.GetCommission();
                double total = calc.GetTotal();

                String message = "Customer Name: " + customerName
                        + "\nProduct: " + product
                        + "\nVAT: R " + vat
                        + "\nCOMMISSION: R " + commission
                        + "\nTOTAL: R " + total;
                JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);


                try (FileWriter writer = new FileWriter("data.txt", true)) {
                    writer.write("DATA LOG\n");
                    writer.write("********************************\n");
                    writer.write("Customer Name: " + customerName + "\n");
                    writer.write("Product: " + product + "\n");
                    writer.write("VAT: R " + vat + "\n");
                    writer.write("COMMISSION: R " + commission + "\n");
                    writer.write("TOTAL: R " + total + "\n");
                    writer.write("********************************\n");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price");
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
        }
    }
}
