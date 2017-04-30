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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class BrisanjePitanja extends JFrame {

	private JPanel contentPane;
	private JLabel lblDaLiHocete;
	private JButton btnDa;
	private JButton btnNe;
	
	Algoritam a = new Algoritam();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjePitanja frame = new BrisanjePitanja();
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
	public BrisanjePitanja() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 300, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblDaLiHocete());
		contentPane.add(getBtnDa());
		contentPane.add(getBtnNe());
	}

	private JLabel getLblDaLiHocete() {
		if (lblDaLiHocete == null) {
			lblDaLiHocete = new JLabel("Da li hocete da pobrisete sva pitanja?");
			lblDaLiHocete.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDaLiHocete.setHorizontalAlignment(SwingConstants.CENTER);
			lblDaLiHocete.setBounds(0, 11, 284, 30);
		}
		return lblDaLiHocete;
	}
	private JButton getBtnDa() {
		if (btnDa == null) {
			btnDa = new JButton("Da");
			btnDa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LinkedList<Pitanje> pitanja = new LinkedList<>();
					LinkedList<Ocena> ocene = new LinkedList<>();
					
					Util.sacuvajPitanjaUFile(a.lokacijaPitanja, pitanja);
					Util.sacuvajOceneUFile(a.lokacijaOcena, ocene);
					
					
					dispose();
				}
			});
			btnDa.setBounds(29, 91, 100, 36);
		}
		return btnDa;
	}
	private JButton getBtnNe() {
		if (btnNe == null) {
			btnNe = new JButton("NE");
			btnNe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNe.setBounds(168, 91, 100, 36);
		}
		return btnNe;
	}
}
