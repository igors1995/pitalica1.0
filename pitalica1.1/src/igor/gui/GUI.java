package igor.gui;

//toDo: da se na X sacuvajuPitanja, pozivanjem a.unesiSvaPitanjaIzListe();

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import igor.domen.Ocena;
import igor.domen.Pitanje;
import igor.logika.Algoritam;
import igor.util.Util;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.FocusAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnZnam;
	private JRadioButton rdbtnSlaboZnam;
	private JRadioButton rdbtnNeZnam;
	private JButton btnPrikaziSvaPitanja;
	private JButton btnPostaviPitanje;
	private JTextArea textArea;

	Algoritam a = new Algoritam();
	private JButton btnPocniKviz;
	private JButton btnZavrsiKviz;

	int brojac1 = 0;
	int brojac2 = 0;
	int brojac3 = 0;

	private JButton btnVisePitanja;
	private JButton btnUnosPitanja;
	private JMenuBar menuBar;
	private JMenu mnObrisi;
	private JMenuItem mntmSvaPitanja;
	private JMenuItem mntmIzaberiPitanja;
	private JSeparator separator_1;
	private JMenuItem mntmInfo;
	private JMenu mnPrikazi;
	private JMenuItem mntmNajtezaPitanja;
	private JMenuItem mntmSrednjaPitanja;
	private JMenuItem mntmNajlaksaPitanja;
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setTitle("Kviz.1.0");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.setBackground(UIManager.getColor("List.selectionBackground"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getRdbtnZnam());
		contentPane.add(getRdbtnSlaboZnam());
		contentPane.add(getRdbtnNeZnam());
		contentPane.add(getBtnPrikaziSvaPitanja());
		contentPane.add(getBtnPostaviPitanje());
		contentPane.add(getBtnPocniKviz());
		contentPane.add(getBtnZavrsiKviz());
		contentPane.add(getBtnVisePitanja());
		contentPane.add(getBtnUnosPitanja());
		// contentPane.add(getScrollPane());

		// textArea.setVisible(true);
		// textArea.setText("");
		contentPane.add(getScrollPane_1());

		btnPostaviPitanje.setVisible(false);
		btnPocniKviz.setVisible(true);
		btnVisePitanja.setVisible(false);

		btnPrikaziSvaPitanja.setVisible(true);
		btnZavrsiKviz.setVisible(false);

		rdbtnNeZnam.setVisible(false);
		rdbtnSlaboZnam.setVisible(false);
		rdbtnZnam.setVisible(false);

		a.napuniListuPitanjima();
		a.unesiSvaPitanjaIzListe();

		a.napuniListuOcenama();
		a.unesiSveOceneIzListe();
	}

	// public JScrollPane getScrollPane() {
	// if(scrollPane == null){
	// scrollPane = new JScrollPane();
	// scrollPane.setViewportView(getTextArea());
	// }
	// return scrollPane;
	// }

	// private JScrollPane getScrollPaneSong() {
	// if (scrollPaneSong == null) {
	// scrollPaneSong = new JScrollPane();
	// scrollPaneSong.setViewportView(getLblIllustration());
	// }
	// return scrollPaneSong;
	// }

	private JRadioButton getRdbtnZnam() {
		if (rdbtnZnam == null) {
			rdbtnZnam = new JRadioButton("Znam");
			rdbtnZnam.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String tekst = textArea.getText();
					if (!tekst.equals("")) {
						int id = a.prondajiID(tekst);

						a.nadjiPitanjeUFileOcenaIPostaviOcenu(id, 1);
						a.setBrojac1(a.getBrojac1() + 1);

						textArea.setText("");
					}
					rdbtnNeZnam.setSelected(false);
					rdbtnSlaboZnam.setSelected(false);
					rdbtnZnam.setSelected(false);
					textArea.setText("");

					try {
						tekst = a.postaviPitanje();
						textArea.setText(tekst);
						a.setPoslednjePitanje(tekst);
					} catch (Exception e1) {

						textArea.setVisible(true);
						textArea.setText("");

						btnPostaviPitanje.setVisible(false);
						btnPocniKviz.setVisible(true);
						btnZavrsiKviz.setVisible(false);
						btnUnosPitanja.setVisible(true);

						btnPrikaziSvaPitanja.setVisible(true);

						rdbtnNeZnam.setVisible(false);
						rdbtnSlaboZnam.setVisible(false);
						rdbtnZnam.setVisible(false);

						// textArea.setText("Odgovorili ste na sva pitanja!");
						JOptionPane.showMessageDialog(null,
								"Odgovorili ste na sva pitanja!" + "\n" + "Znali ste " + a.getBrojac1() + "/"
										+ (a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) + " pitanja.",
								"Rezultat", JOptionPane.INFORMATION_MESSAGE);

					}
				}	});

			rdbtnZnam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSlaboZnam.setSelected(false);
					rdbtnNeZnam.setSelected(false);
				}
			});
			rdbtnZnam.setBounds(60, 200, 109, 23);
		}
		return rdbtnZnam;
	}

	private JRadioButton getRdbtnSlaboZnam() {
		if (rdbtnSlaboZnam == null) {
			rdbtnSlaboZnam = new JRadioButton("Slabo znam");
			rdbtnSlaboZnam.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String tekst = textArea.getText();
					if (!tekst.equals("")) {
						int id = a.prondajiID(tekst);

						a.nadjiPitanjeUFileOcenaIPostaviOcenu(id, 2);
						a.setBrojac2(a.getBrojac2() + 1);

						textArea.setText("");
					}
					rdbtnNeZnam.setSelected(false);
					rdbtnSlaboZnam.setSelected(false);
					rdbtnZnam.setSelected(false);
					textArea.setText("");

					try {
						tekst = a.postaviPitanje();
						textArea.setText(tekst);
						a.setPoslednjePitanje(tekst);
					} catch (Exception e1) {

						textArea.setVisible(true);
						textArea.setText("");

						btnPostaviPitanje.setVisible(false);
						btnPocniKviz.setVisible(true);
						btnZavrsiKviz.setVisible(false);
						btnUnosPitanja.setVisible(true);

						btnPrikaziSvaPitanja.setVisible(true);

						rdbtnNeZnam.setVisible(false);
						rdbtnSlaboZnam.setVisible(false);
						rdbtnZnam.setVisible(false);

						// textArea.setText("Odgovorili ste na sva pitanja!");
						JOptionPane.showMessageDialog(null,
								"Odgovorili ste na sva pitanja!" + "\n" + "Znali ste " + a.getBrojac1() + "/"
										+ (a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) + " pitanja.",
								"Rezultat", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			});
			rdbtnSlaboZnam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnZnam.setSelected(false);
					rdbtnNeZnam.setSelected(false);
				}
			});
			rdbtnSlaboZnam.setBounds(183, 200, 109, 23);
		}
		return rdbtnSlaboZnam;
	}

	private JRadioButton getRdbtnNeZnam() {
		if (rdbtnNeZnam == null) {
			rdbtnNeZnam = new JRadioButton("Ne znam");
			rdbtnNeZnam.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					String tekst = textArea.getText();
					if (!tekst.equals("")) {
						int id = a.prondajiID(tekst);

						a.nadjiPitanjeUFileOcenaIPostaviOcenu(id, 3);
						a.setBrojac3(a.getBrojac3() + 1);

						textArea.setText("");
					}
					rdbtnNeZnam.setSelected(false);
					rdbtnSlaboZnam.setSelected(false);
					rdbtnZnam.setSelected(false);
					textArea.setText("");

					try {
						tekst = a.postaviPitanje();
						textArea.setText(tekst);
						a.setPoslednjePitanje(tekst);
					} catch (Exception e1) {

						textArea.setVisible(true);
						textArea.setText("");

						btnPostaviPitanje.setVisible(false);
						btnPocniKviz.setVisible(true);
						btnZavrsiKviz.setVisible(false);
						btnUnosPitanja.setVisible(true);

						btnPrikaziSvaPitanja.setVisible(true);

						rdbtnNeZnam.setVisible(false);
						rdbtnSlaboZnam.setVisible(false);
						rdbtnZnam.setVisible(false);

						// textArea.setText("Odgovorili ste na sva pitanja!");
						JOptionPane.showMessageDialog(null,
								"Odgovorili ste na sva pitanja!" + "\n" + "Znali ste " + a.getBrojac1() + "/"
										+ (a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) + " pitanja.",
								"Rezultat", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			});
			rdbtnNeZnam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSlaboZnam.setSelected(false);
					rdbtnZnam.setSelected(false);
				}
			});
			rdbtnNeZnam.setBounds(323, 200, 109, 23);
		}
		return rdbtnNeZnam;
	}

	private JButton getBtnPrikaziSvaPitanja() {
		if (btnPrikaziSvaPitanja == null) {
			btnPrikaziSvaPitanja = new JButton("Prikazi sva Pitanja");
			btnPrikaziSvaPitanja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String svaPitanja = a.prikaziSvaPitanja();
					if (svaPitanja.equals("")) {
						textArea.setText("Niste uneli pitanja!");
					} else {
						textArea.setText(svaPitanja);
					}
				}
			});
			btnPrikaziSvaPitanja.setBounds(10, 42, 159, 51);
		}
		return btnPrikaziSvaPitanja;
	}

	private JButton getBtnPostaviPitanje() {
		if (btnPostaviPitanje == null) {
			btnPostaviPitanje = new JButton("Postavi pitanje");
			btnPostaviPitanje.addMouseListener(new MouseAdapter() {
				// public void mouseClicked(MouseEvent arg0) {
				//
				// rdbtnNeZnam.setSelected(false);
				// rdbtnSlaboZnam.setSelected(false);
				// rdbtnZnam.setSelected(false);
				// textArea.setText("");
				//
				// String tekst;
				// try {
				// tekst = a.postaviPitanje();
				// textArea.setText(tekst);
				// a.setPoslednjePitanje(tekst);
				// } catch (Exception e) {
				//
				// textArea.setVisible(true);
				// textArea.setText("");
				//
				// btnPostaviPitanje.setVisible(false);
				// btnPocniKviz.setVisible(true);
				// btnZavrsiKviz.setVisible(false);
				// btnUnosPitanja.setVisible(true);
				//
				// btnPrikaziSvaPitanja.setVisible(true);
				//
				// rdbtnNeZnam.setVisible(false);
				// rdbtnSlaboZnam.setVisible(false);
				// rdbtnZnam.setVisible(false);
				//
				// // textArea.setText("Odgovorili ste na sva pitanja!");
				// JOptionPane.showMessageDialog(null,
				// "Odgovorili ste na sva pitanja!" + "\n" + "Znali ste " +
				// a.getBrojac1() + "/"
				// + (a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) + "
				// pitanja.",
				// "Rezultat", JOptionPane.INFORMATION_MESSAGE);
				// }
				// }
				@Override
				public void mousePressed(MouseEvent e) {
					rdbtnNeZnam.setSelected(false);
					rdbtnSlaboZnam.setSelected(false);
					rdbtnZnam.setSelected(false);
					textArea.setText("");

					String tekst;
					try {
						tekst = a.postaviPitanje();
						textArea.setText(tekst);
						a.setPoslednjePitanje(tekst);
					} catch (Exception e1) {

						textArea.setVisible(true);
						textArea.setText("");

						btnPostaviPitanje.setVisible(false);
						btnPocniKviz.setVisible(true);
						btnZavrsiKviz.setVisible(false);
						btnUnosPitanja.setVisible(true);

						btnPrikaziSvaPitanja.setVisible(true);

						rdbtnNeZnam.setVisible(false);
						rdbtnSlaboZnam.setVisible(false);
						rdbtnZnam.setVisible(false);

						// textArea.setText("Odgovorili ste na sva pitanja!");
						JOptionPane.showMessageDialog(null,
								"Odgovorili ste na sva pitanja!" + "\n" + "Znali ste " + a.getBrojac1() + "/"
										+ (a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) + " pitanja.",
								"Rezultat", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			});
			btnPostaviPitanje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					rdbtnNeZnam.setSelected(false);
					rdbtnSlaboZnam.setSelected(false);
					rdbtnZnam.setSelected(false);
				}
			});
			btnPostaviPitanje.setBounds(323, 42, 159, 51);
		}
		return btnPostaviPitanje;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setForeground(Color.RED);
			textArea.setFont(new Font("Cambria", Font.BOLD, 13));
			textArea.setDisabledTextColor(Color.BLACK);
			textArea.setEnabled(false);
			textArea.setBackground(Color.WHITE);
			textArea.setBorder(new LineBorder(Color.gray, 2));

			DefaultCaret caret = (DefaultCaret) textArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
			
		}
		return textArea;
	}

	private JButton getBtnPocniKviz() {

		if (btnPocniKviz == null) {
			btnPocniKviz = new JButton("Pocni kviz");
			btnPocniKviz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					a.napuniListuPitanjima();
					a.napraviListuPitanjaUskladuSaOcenama();
					if(a.listaPitanja.isEmpty()){textArea.setText("Niste uneli pitanja!");}else{
					a.setBrojac1(0);
					a.setBrojac2(0);
					a.setBrojac3(0);
					
					
					rdbtnNeZnam.setVisible(true);
					rdbtnSlaboZnam.setVisible(true);
					rdbtnZnam.setVisible(true);

					btnPocniKviz.setVisible(false);
					btnPostaviPitanje.setVisible(true);
					btnPrikaziSvaPitanja.setVisible(false);
					btnZavrsiKviz.setVisible(true);
					btnZavrsiKviz.setVisible(true);
					btnUnosPitanja.setVisible(false);

					textArea.setText("");

					}

				}
			});
			btnPocniKviz.setBounds(321, 42, 161, 51);
		}
		return btnPocniKviz;
	}

	private JButton getBtnZavrsiKviz() {
		if (btnZavrsiKviz == null) {
			btnZavrsiKviz = new JButton("Zavrsi kviz");
			btnZavrsiKviz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					textArea.setVisible(true);
					textArea.setText("");

					btnPostaviPitanje.setVisible(false);
					btnPocniKviz.setVisible(true);
					btnZavrsiKviz.setVisible(false);
					btnUnosPitanja.setVisible(true);

					btnPrikaziSvaPitanja.setVisible(true);

					rdbtnNeZnam.setVisible(false);
					rdbtnSlaboZnam.setVisible(false);
					rdbtnZnam.setVisible(false);
					
					if((a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) > 0){
					JOptionPane.showMessageDialog(
							null, "Znali ste " + a.getBrojac1() + "/"
									+ (a.getBrojac1() + a.getBrojac2() + a.getBrojac3()) + " pitanja.",
							"Rezultat", JOptionPane.INFORMATION_MESSAGE);

				}}
			});
			btnZavrsiKviz.setBounds(8, 42, 161, 51);
		}
		return btnZavrsiKviz;
	}

	private JButton getBtnVisePitanja() {
		if (btnVisePitanja == null) {
			btnVisePitanja = new JButton("Unesi vise pitanja");
			btnVisePitanja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnVisePitanja.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					a.unesiSvaPitanjaIzListe();
					a.unesiSveOceneIzListe();

					UnosVisePitanja unos = new UnosVisePitanja();
					unos.setVisible(true);
				}
			});
			btnVisePitanja.addMouseMotionListener(new MouseMotionAdapter() {

			});
			btnVisePitanja.addFocusListener(new FocusAdapter() {

			});
			btnVisePitanja.setBounds(139, 200, 194, 23);
		}
		return btnVisePitanja;
	}

	private JButton getBtnUnosPitanja() {
		if (btnUnosPitanja == null) {
			btnUnosPitanja = new JButton("Unos pitanja");
			btnUnosPitanja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					a.unesiSvaPitanjaIzListe();
					a.unesiSveOceneIzListe();

					UnosVisePitanja unos = new UnosVisePitanja();
					unos.setVisible(true);

					textArea.setText("");
				}
			});
			btnUnosPitanja.setBounds(149, 200, 211, 35);
		}
		return btnUnosPitanja;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnObrisi());
			menuBar.add(getMnPrikazi());
			menuBar.add(getMntmInfo());
		}
		return menuBar;
	}

	private JMenu getMnObrisi() {
		if (mnObrisi == null) {
			mnObrisi = new JMenu("Obrisi");
			mnObrisi.add(getMntmSvaPitanja());
			mnObrisi.add(getSeparator_1());
			mnObrisi.add(getMntmIzaberiPitanja());
		}
		return mnObrisi;
	}

	private JMenuItem getMntmSvaPitanja() {
		if (mntmSvaPitanja == null) {
			mntmSvaPitanja = new JMenuItem("Sva pitanja");
			mntmSvaPitanja.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent arg0) {
					BrisanjePitanja obrisi = new BrisanjePitanja();
					obrisi.setVisible(true);

					textArea.setText("");

				}
			});
		}
		return mntmSvaPitanja;
	}

	private JMenuItem getMntmIzaberiPitanja() {
		if (mntmIzaberiPitanja == null) {
			mntmIzaberiPitanja = new JMenuItem("Izaberi pitanja");
			mntmIzaberiPitanja.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					BrisanjeOdredjenihPitanja odredjena = new BrisanjeOdredjenihPitanja();
					odredjena.setVisible(true);
					textArea.setText("");
				}
			});
		}
		return mntmIzaberiPitanja;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	private JMenuItem getMntmInfo() {
		if (mntmInfo == null) {
			mntmInfo = new JMenuItem("Info");
			mntmInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					InfoGUI info = new InfoGUI();
					info.setVisible(true);
				}
			});
			mntmInfo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return mntmInfo;
	}

	private JMenu getMnPrikazi() {
		if (mnPrikazi == null) {
			mnPrikazi = new JMenu("Prikazi");
			mnPrikazi.add(getMntmNajtezaPitanja());
			mnPrikazi.add(getMntmSrednjaPitanja());
			mnPrikazi.add(getMntmNajlaksaPitanja());
		}
		return mnPrikazi;
	}

	private JMenuItem getMntmNajtezaPitanja() {
		if (mntmNajtezaPitanja == null) {
			mntmNajtezaPitanja = new JMenuItem("Najteza pitanja");
			mntmNajtezaPitanja.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					String tekst = "";
					int brojac = 1;

					LinkedList<Pitanje> pitanja = new LinkedList<>();
					LinkedList<Ocena> ocene = new LinkedList<>();

					Util.iscitajOceneIUpisiUlistu(a.lokacijaOcena, ocene);
					Util.iscitajPitanjaIUpisiUlistu(a.lokacijaPitanja, pitanja);

					if (pitanja == null || pitanja.size() == 0) {
						tekst = "Niste uneli pitanja!";
					} else {

						for (int i = 0; i < ocene.size(); i++) {
							if (ocene.get(i).getOcena() == 3) {
								tekst += brojac + ". " + pitanja.get(i).getTekst() + "\n";
								brojac++;
							}
						}
					}
					if (tekst.equals("")) {
						tekst = "Nema pitanja oznacenih kao najteza!";
					}

					textArea.setText(tekst);

				}
			});
		}
		return mntmNajtezaPitanja;
	}

	private JMenuItem getMntmSrednjaPitanja() {
		if (mntmSrednjaPitanja == null) {
			mntmSrednjaPitanja = new JMenuItem("Srednja pitanja");
			mntmSrednjaPitanja.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					String tekst = "";
					int brojac = 1;

					LinkedList<Pitanje> pitanja = new LinkedList<>();
					LinkedList<Ocena> ocene = new LinkedList<>();

					Util.iscitajOceneIUpisiUlistu(a.lokacijaOcena, ocene);
					Util.iscitajPitanjaIUpisiUlistu(a.lokacijaPitanja, pitanja);

					if (pitanja == null || pitanja.size() == 0) {
						tekst = "Niste uneli pitanja!";
					} else {
						for (int i = 0; i < ocene.size(); i++) {
							if (ocene.get(i).getOcena() == 2) {
								tekst += brojac + ". " + pitanja.get(i).getTekst() + "\n";
								brojac++;
							}
						}
					}
					if (tekst.equals("")) {
						tekst = "Nema pitanja oznacenih kao srednja!";
					}
					textArea.setText(tekst);
				}
			});
		}
		return mntmSrednjaPitanja;
	}

	private JMenuItem getMntmNajlaksaPitanja() {
		if (mntmNajlaksaPitanja == null) {
			mntmNajlaksaPitanja = new JMenuItem("Najlaksa pitanja");
			mntmNajlaksaPitanja.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int brojac = 1;
					String tekst = "";

					LinkedList<Pitanje> pitanja = new LinkedList<>();
					LinkedList<Ocena> ocene = new LinkedList<>();

					Util.iscitajOceneIUpisiUlistu(a.lokacijaOcena, ocene);
					Util.iscitajPitanjaIUpisiUlistu(a.lokacijaPitanja, pitanja);

					if (pitanja == null || pitanja.size() == 0) {
						tekst = "Niste uneli pitanja!";
					} else {
						for (int i = 0; i < ocene.size(); i++) {
							if (ocene.get(i).getOcena() == 1) {
								tekst += brojac + ". " + pitanja.get(i).getTekst() + "\n";
								brojac++;
							}
						}
					}
					if (tekst.equals("")) {
						tekst = "Nema pitanja oznacenih kao najlaksa";
					}
					textArea.setText(tekst);

				}
			});
		}
		return mntmNajlaksaPitanja;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(42, 104, 380, 89);
			scrollPane_1.setViewportView(getTextArea());
		}
		return scrollPane_1;
	}
}
