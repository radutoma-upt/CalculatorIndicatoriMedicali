package tema.Swing;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.border.BevelBorder;

public class CalculatorApp extends JFrame {

	/*
	 * @author Radu Toma (rtoma@vitasromania.ro)
	 * Acest proiect are scop utilizarea bibliotecii Swing si nu are acuratete dpdv medical
	 */
	
	// declarare variabile
	private static final long serialVersionUID = 1L;
	private JPanel content_Panou;
	private JLabel lblLabel_Text, lblLabel_Gen, lblLabel_Greutate, lblLabel_Inaltime;
	private JTextField textField_Inaltime, textField_Greutate;
	private JComboBox comboBox_Gen;
	private JButton btnButton_IMC, btnButton_GreutateIdeala, btnButton_Apa;
			
	/*
	 * Rulare aplicatie
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorApp frame = new CalculatorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Fereastra principala
	 */
	public CalculatorApp() {
		setTitle("Programare Java - Calculator Medical");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 264);
		content_Panou = new JPanel();
		content_Panou.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content_Panou);
		content_Panou.setLayout(null);
		
		lblLabel_Text = new JLabel("Selectati Genul si introduceti Greutatea si Inaltimea dvs.");
		lblLabel_Text.setSize(384, 13);
		content_Panou.add(lblLabel_Text);
		lblLabel_Text.setLocation (55,10);
		lblLabel_Text.setForeground(Color.blue);
		lblLabel_Text.setOpaque(true);
		lblLabel_Text.setBackground(Color.LIGHT_GRAY);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		horizontalBox.setBounds(55, 33, 384, 112);
		content_Panou.add(horizontalBox);
		
		lblLabel_Gen = new JLabel("Gen");
		lblLabel_Gen.setBounds(109, 56, 121, 13);
		content_Panou.add(lblLabel_Gen);
		
		String[] lista_Gen = {"", "Masculin", "Feminin"};
		comboBox_Gen = new JComboBox(lista_Gen);
		comboBox_Gen.setBounds(247, 56, 121, 21);
		content_Panou.add(comboBox_Gen);
					
		lblLabel_Greutate = new JLabel("Greutate (kg)");
		lblLabel_Greutate.setBounds(109, 90, 110, 13);
		content_Panou.add(lblLabel_Greutate);
		
		textField_Greutate = new JTextField();
		textField_Greutate.setBounds(247, 87, 121, 20);
		textField_Greutate.setColumns(10);
		content_Panou.add(textField_Greutate);
		
		lblLabel_Inaltime = new JLabel("Inaltime (cm)");
		lblLabel_Inaltime.setBounds(109, 119, 110, 13);
		content_Panou.add(lblLabel_Inaltime);
		
		textField_Inaltime = new JTextField();
		textField_Inaltime.setBounds(247, 116, 121, 20);
		textField_Inaltime.setColumns(10);
		content_Panou.add(textField_Inaltime);

		btnButton_IMC = new JButton("Calculeaza IMC");
		btnButton_IMC.addActionListener(new Mesaj_IMC());
		btnButton_IMC.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnButton_IMC.setToolTipText("Calculeaza Indicele de Masa Corporala (IMC)");
		btnButton_IMC.setBounds(55, 160, 120, 21);
		content_Panou.add(btnButton_IMC);
		
		btnButton_GreutateIdeala = new JButton("Greutate Ideala");
		btnButton_GreutateIdeala.addActionListener(new Mesaj_GrIdeala());
		btnButton_GreutateIdeala.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnButton_GreutateIdeala.setToolTipText("Calculeaza greutatea ideala (kg)");
		btnButton_GreutateIdeala.setBounds(187, 160, 120, 21);
		content_Panou.add(btnButton_GreutateIdeala);
		
		btnButton_Apa = new JButton("Calculeaza Apa");
		btnButton_Apa.addActionListener(new Mesaj_Apa());
		btnButton_Apa.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnButton_Apa.setToolTipText("Calculeaza cantitatea zilnica recomandata de apa (L)");
		btnButton_Apa.setBounds(319, 160, 120, 21);
		content_Panou.add(btnButton_Apa);
				
	}	
	
	// Eveniment apasare buton "Calculeaza IMC"
	class Mesaj_IMC implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			if (textField_Greutate.getText().isEmpty() || textField_Inaltime.getText().isEmpty()) {
				JOptionPane.showMessageDialog(CalculatorApp.this,
						"Campurile Greutate & Inaltime trebuie completate!");
			} else {
				float Greutate= Float.parseFloat(textField_Greutate.getText());
				float Inaltime= Float.parseFloat(textField_Inaltime.getText());
				float IMC = calculeaza_IMC(Greutate, Inaltime);
				String message = "Indicele de Masa Corporala este %.2f!";
				message= String.format(message, IMC);
				JOptionPane.showMessageDialog(CalculatorApp.this, message);
			}
		}
		
		/* Formula diponibila pe https://en.wikipedia.org/wiki/Body_mass_index
		 * 
		 */
		private float calculeaza_IMC (float Greutate, float Inaltime) {
			return (float) (Greutate / Math.pow(Inaltime/100, 2));
		}
	}
	
	// Eveniment apasare buton "Greutate Ideala"
	class Mesaj_GrIdeala implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			if (textField_Greutate.getText().isEmpty() || textField_Inaltime.getText().isEmpty() || comboBox_Gen.getItemCount() == 0) {
				JOptionPane.showMessageDialog(CalculatorApp.this,
						"Campurile Gen & Greutate & Inaltime trebuie completate!");
			} else {
				float Greutate= Float.parseFloat(textField_Greutate.getText());
				float Inaltime= Float.parseFloat(textField_Inaltime.getText());
				String Gen = (String) comboBox_Gen.getSelectedItem();
				if (Gen.equals("Masculin")) {
					float IdealaM = calculeaza_IdealaM(Greutate, Inaltime);
					String message = "Greutatea ideala pentru barbati este %.2f kg!";
					message= String.format(message, IdealaM);
					JOptionPane.showMessageDialog(CalculatorApp.this, message);
				} else if (Gen.equals("Feminin")) {
					float IdealaF = calculeaza_IdealaF(Greutate, Inaltime);
					String message = "Greutatea ideala pentru femei este %.2f kg!";
					message= String.format(message, IdealaF);
					JOptionPane.showMessageDialog(CalculatorApp.this, message);
				}
			}
		}
	}
	
		/* Formula lui D. R. Miller Formula (1983)
		 * https://www.calculator.net/ideal-weight-calculator.html
		 * Male:	56.2 kg + 1.41 kg per inch over 5 feet
		 * Female:	53.1 kg + 1.36 kg per inch over 5 feet
		 */
		private float calculeaza_IdealaM (float Greutate, float Inaltime) {
			return (float) (56.4+(1.41+ (Inaltime - 152.4)/2.54));
		}
		
		private float calculeaza_IdealaF (float Greutate, float Inaltime) {
			return (float) (53.1+(1.36+ (Inaltime - 152.4)/2.54));
		}

	// Eveniment apasare buton "Calculeaza Apa"
	class Mesaj_Apa implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			if (textField_Greutate.getText().isEmpty()) {
				JOptionPane.showMessageDialog(CalculatorApp.this,
						"Campul Greutate trebuie completat!");
			} else {	
			float Greutate= Float.parseFloat(textField_Greutate.getText());
			float CantitateApa = calculeaza_Apa(Greutate);
			String message = "Cantitatea recomandata de apa este %.2f L zilnic!";
			message= String.format(message, CantitateApa);
			JOptionPane.showMessageDialog(CalculatorApp.this, message);
			}
		}
		
		/*Diverse formule existente pe internet. Pentru acest proiect am utilizat formula disponibila la: 
		 * https://rhitrition.com/how-much-water-should-drink-every-day/ 
		 * Water (in litres) to drink a day = Your Weight (in Kg) multiplied by 0.033
		 */
		private float calculeaza_Apa (float Greutate) {
			return Greutate * 0.033f;
		}
	}
}

