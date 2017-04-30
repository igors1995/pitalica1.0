package igor.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class InfoGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblInformacijeOAplikaciji;
	private JScrollPane scrollPane;
	private JTextPane txtpnOvaAplikacijaNamenjena;
	private JPanel panel;
	private JLabel label;
	private JLabel lblDeveloperIgor;
	private JLabel lblContactIgorskiljevicgmailcom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoGUI frame = new InfoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoGUI() {
		setTitle("Info");
		getContentPane().setBackground(new Color(253, 245, 230));
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getLblInformacijeOAplikaciji());
		getContentPane().add(getScrollPane());
		getContentPane().add(getPanel());
	}
	private JLabel getLblInformacijeOAplikaciji() {
		if (lblInformacijeOAplikaciji == null) {
			lblInformacijeOAplikaciji = new JLabel("Informacije o aplikaciji");
			lblInformacijeOAplikaciji.setBackground(UIManager.getColor("ToolBar.light"));
			lblInformacijeOAplikaciji.setBounds(0, 0, 444, 22);
			lblInformacijeOAplikaciji.setHorizontalAlignment(SwingConstants.CENTER);
			lblInformacijeOAplikaciji.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblInformacijeOAplikaciji;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 33, 424, 228);
			scrollPane.setViewportView(getTxtpnOvaAplikacijaNamenjena());
		}
		return scrollPane;
	}
	private JTextPane getTxtpnOvaAplikacijaNamenjena() {
		if (txtpnOvaAplikacijaNamenjena == null) {
			txtpnOvaAplikacijaNamenjena = new JTextPane();
			txtpnOvaAplikacijaNamenjena.setEditable(false);
			txtpnOvaAplikacijaNamenjena.setText(" Ova aplikacija namenjena je studentima i omogucava im da provere svoje znanje.\r\nAplikacija vam na slucajan nacin postavlja pitanja koja ste prethodno uneli.\r\nKada dobijete pitanje, potrebo je da ocenite tezinu pitanja i u skladu sa time ce vam ubuduce neka pitanja biti cesce, a neke redje postavljana.\r\nU slucaju da ne ocenite pitanje, racunace se kao da ste ga znali.\r\n\r\n Pitanja se unose po izboru, koristeci dugme \"Unesi pitanja\".\r\nPitanja se moraju ISKLJUCIVO zavrsavati upitnikom, jer u suprotnom nece biti pravilno formirana.\r\nPotrebno je makar jednom uneti pitanja, a kasnije se po zelji pitanja mogu dodavati.\r\n\r\n Pritiskom na dugme Zapocni kviz, pojavice vam se tasteri \"Postavi pitanje\" i \"Zavrsi kviz\".\r\nKako biste dobili pitanje potrebno je da pritisnete dugme \"Postavi pitanje\".\r\nKada dobijete pitanje mozete ga oceniti klikom na tastere \"Znam\", \"Slabo znam\" i \"Ne znam\".\r\n U skladu sa vasim odgovorima, teza pitanja ce vam se cesce ponavljati, a laksa manje.\r\nKako bi ste se vratili na pocetni prozor pritisnite taster \"Zavrsi kviz\".\r\n\r\n Ukoliko zelite da vidite sva unesena pitanja to mozete uraditi na vise nacina:\r\n-pritiskom na taster \"Prikazi sva pitanja\" aplikacija ce vam izbaciti sva pitanja koja ste uneli\r\n-u Menu baru, klikom na opciju \"Prikazi\" na padajucem meniju ce vam se prikazati opcije \r\n\"Prikazi najteza\"  \"Prikazi srednja\" i \"Prikazi najlaksa\".\r\n\r\n Kako bi ste obrisali pitanja u Menu baru mozete izabrati dve opcije\"\r\n-\"Obrisi sva pitanja\" koja brise sva pitanja koja ste uneli\r\n-\"Izaberi pitanja koja zelis da obrises\" gde od ponudjenih pitanja mozete izabrati ona koja zelite da obrisete. \r\n\r\n\r\n");
			txtpnOvaAplikacijaNamenjena.setBackground(new Color(253, 245, 230));
			txtpnOvaAplikacijaNamenjena.setCaretPosition(0);
		}
		return txtpnOvaAplikacijaNamenjena;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 261, 424, 51);
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getLblDeveloperIgor());
			panel.add(getLblContactIgorskiljevicgmailcom());
			panel.setBackground(new Color(253, 245, 230));
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setBounds(212, 5, 0, 0);
		}
		return label;
	}
	private JLabel getLblDeveloperIgor() {
		if (lblDeveloperIgor == null) {
			lblDeveloperIgor = new JLabel("Created by : Igor \u0160kiljevi\u0107");
			lblDeveloperIgor.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
			lblDeveloperIgor.setBounds(0, 5, 298, 20);
		}
		return lblDeveloperIgor;
	}
	private JLabel getLblContactIgorskiljevicgmailcom() {
		if (lblContactIgorskiljevicgmailcom == null) {
			lblContactIgorskiljevicgmailcom = new JLabel("Contact : igorskiljevic@gmail.com");
			lblContactIgorskiljevicgmailcom.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
			lblContactIgorskiljevicgmailcom.setBounds(0, 26, 414, 25);
		}
		return lblContactIgorskiljevicgmailcom;
	}
}


