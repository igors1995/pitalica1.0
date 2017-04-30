package igor.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import igor.domen.Ocena;
import igor.domen.Pitanje;
import igor.logika.Algoritam;
import igor.util.Util;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class UnosVisePitanja extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblUnesiteUPolja;
	private JLabel lblInfo;
	private JLabel lblInfo_1;
	private JButton btnDodajPitanja;

	
	Algoritam a = new Algoritam();
	private JScrollPane scrollPane;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosVisePitanja frame = new UnosVisePitanja();
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
	public UnosVisePitanja() {
		setTitle("Unos vise pitanja");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblUnesiteUPolja());
		contentPane.add(getLblInfo());
		contentPane.add(getLblInfo_1());
		contentPane.add(getBtnDodajPitanja());
		contentPane.add(getScrollPane());
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JLabel getLblUnesiteUPolja() {
		if (lblUnesiteUPolja == null) {
			lblUnesiteUPolja = new JLabel("Unesite pitanja u polje");
			lblUnesiteUPolja.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblUnesiteUPolja.setHorizontalAlignment(SwingConstants.CENTER);
			lblUnesiteUPolja.setBounds(58, 53, 303, 29);
		}
		return lblUnesiteUPolja;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("Pitanja se moraju zavrsavati upitnikom!");
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfo.setBounds(60, 200, 301, 14);
		}
		return lblInfo;
	}
	private JLabel getLblInfo_1() {
		if (lblInfo_1 == null) {
			lblInfo_1 = new JLabel("Upitnik se sme koristiti samo na kraju pitanja!");
			lblInfo_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfo_1.setBounds(64, 221, 297, 14);
		}
		return lblInfo_1;
	}
	private JButton getBtnDodajPitanja() {
		if (btnDodajPitanja == null) {
			btnDodajPitanja = new JButton("Dodaj pitanja");
			btnDodajPitanja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					
					LinkedList<Pitanje> pitanja = new LinkedList<>();
					LinkedList<Ocena> ocene = new LinkedList<>();
					
					Util.iscitajPitanjaIUpisiUlistu(a.lokacijaPitanja, pitanja);
					Util.iscitajOceneIUpisiUlistu(a.lokacijaOcena, ocene);
					
					String tekst = textArea.getText();
					String [] celine = tekst.split("[?]"); 
					
					for (String string : celine) {
						String pitanje = string;
						
						if(pitanje.contains("\n")){
							String [] p = pitanje.split("\n");
							pitanje = "";
							for (String string2 : p) {
								pitanje += string2;
							}
						}
						pitanje += "?";
						
						Pitanje p = new Pitanje();
						Ocena o = new Ocena();
						
						p.setId(pitanja.size());
						p.setTekst(pitanje);
						
						o.setId(ocene.size());
						o.setOcena(1);
						
						pitanja.add(p);
						ocene.add(o);
					}
					if(pitanja.get(pitanja.size() - 1).getTekst().equals("?")){pitanja.remove(pitanja.size() - 1); ocene.remove(ocene.size() - 1);}
					Util.sacuvajOceneUFile(a.lokacijaOcena, ocene);
					Util.sacuvajPitanjaUFile(a.lokacijaPitanja, pitanja);
					
					textArea.setText("Uspesno ste uneli pitanja!");
				}
			});
			btnDodajPitanja.setBounds(106, 19, 216, 23);
		}
		return btnDodajPitanja;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(58, 81, 301, 108);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
}
