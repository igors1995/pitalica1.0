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

import javax.swing.JLabel;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrisanjeOdredjenihPitanja extends JFrame {

	private JPanel contentPane;
	private JLabel lblOdaberiPitanjaKoja;
	private JScrollPane scrollPane;
	private JList list;
	private JLabel lblZaOdabirVise;
	private JButton btnObrisi;
	
	Algoritam a = new Algoritam();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeOdredjenihPitanja frame = new BrisanjeOdredjenihPitanja();
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
	public BrisanjeOdredjenihPitanja() {
		setTitle("Brisanje pitanja");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblOdaberiPitanjaKoja());
		contentPane.add(getScrollPane());
		contentPane.add(getLblZaOdabirVise());
		contentPane.add(getBtnObrisi());
	
	}
	private JLabel getLblOdaberiPitanjaKoja() {
		if (lblOdaberiPitanjaKoja == null) {
			lblOdaberiPitanjaKoja = new JLabel("Odaberi pitanja koja zelis da obrises");
			lblOdaberiPitanjaKoja.setHorizontalAlignment(SwingConstants.CENTER);
			lblOdaberiPitanjaKoja.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblOdaberiPitanjaKoja.setBounds(28, 11, 383, 35);
		}
		return lblOdaberiPitanjaKoja;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 40, 385, 152);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList getList() {
		
		LinkedList<Pitanje> pitanja = new LinkedList<>();
		//LinkedList<Ocena> ocene = new LinkedList<>();
		
		Util.iscitajPitanjaIUpisiUlistu(a.lokacijaPitanja, pitanja);
		//Util.iscitajOceneIUpisiUlistu(a.lokacijaOcena, ocene);
		
		String [] svaPitanja = new String[pitanja.size()];
		
//		for (int i = 0; i < svaPitanja.length; i++) {
//			svaPitanja[i] = pitanja.get(i).getTekst();
//		}
		
		for (int i = 0; i < pitanja.size(); i++) {
			svaPitanja[i] = pitanja.get(i).getTekst();
		}
		
		if (list == null) {
			list = new JList(svaPitanja);
			list.setVisibleRowCount(svaPitanja.length);
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			
		}
		return list;
	}
	private JLabel getLblZaOdabirVise() {
		if (lblZaOdabirVise == null) {
			lblZaOdabirVise = new JLabel("Za odabir vise pitanja drzite ctrl!");
			lblZaOdabirVise.setHorizontalAlignment(SwingConstants.CENTER);
			lblZaOdabirVise.setBounds(28, 193, 385, 18);
		}
		return lblZaOdabirVise;
	}
	private JButton getBtnObrisi() {
		if (btnObrisi == null) {
			btnObrisi = new JButton("Obrisi");
			btnObrisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					LinkedList<Pitanje> pitanja = new LinkedList<>();
					LinkedList<Ocena> ocene = new LinkedList<>();
					
					Util.iscitajPitanjaIUpisiUlistu(a.lokacijaPitanja, pitanja);
					Util.iscitajOceneIUpisiUlistu(a.lokacijaOcena, ocene);
					
					int [] indeksi = list.getSelectedIndices();
					
					
					for (int i = indeksi.length - 1; i >= 0; i--) {
						pitanja.remove(indeksi[i]);
						ocene.remove(indeksi[i]);
						System.out.println(indeksi[i]);
					}
					Util.sacuvajOceneUFile(a.lokacijaOcena, ocene);
					Util.sacuvajPitanjaUFile(a.lokacijaPitanja, pitanja);
					
					dispose();
				}
			});
			btnObrisi.setBounds(165, 222, 117, 39);
		}
		return btnObrisi;
	}
}
