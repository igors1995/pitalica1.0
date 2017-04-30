package igor.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.regex.Pattern;

import igor.domen.Ocena;
import igor.domen.Pitanje;

public class Util {

	public static void sacuvajPitanjaUFile(String lokacija, LinkedList<Pitanje> pitanja) {
		try {
			PrintWriter out = new PrintWriter((new FileWriter(lokacija)));

			for (Pitanje pitanje : pitanja) {

				int id = pitanje.getId();
				String tekst = pitanje.getTekst();

				String pitanjeTekst = id + "|" + tekst;

				out.println(pitanjeTekst);
			}

			out.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom cuvanja pitanja u file.");
		}

	}

	public static void sacuvajOceneUFile(String lokacija, LinkedList<Ocena> ocene) {
		try {
			PrintWriter out = new PrintWriter((new FileWriter(lokacija)));

			for (Ocena ocena : ocene) {

				int id = ocena.getId();
				int ocenaPitanja = ocena.getOcena();

				String ocenaTekst = id + "|" + ocenaPitanja;

				out.println(ocenaTekst);
			}

			out.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom cuvanja ocena u file.");
		}

	}

	public static void iscitajPitanjaIUpisiUlistu(String lokacija, LinkedList<Pitanje> pitanja) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(lokacija));
			String linija;
			boolean prolaz = true;

			while (prolaz) {

				linija = in.readLine();
				if (linija == null) {
					prolaz = false;
					break;
				} else {
					String[] elementi = linija.split("\\|");

					String tekst = elementi[1];
					int id = Integer.parseInt(elementi[0]);

					Pitanje p = new Pitanje();

					p.setTekst(tekst);
					p.setId(id);

					pitanja.add(p);
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom citanja pitanja iz file!");
		}

	}

	public static void iscitajOceneIUpisiUlistu(String lokacija, LinkedList<Ocena> ocene) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(lokacija));
			String linija = "";
			boolean prolaz = true;
			
			while (prolaz) {

				linija = in.readLine();
				if (linija == null) {
					prolaz = false;
					break;
				} else {
					String[] elementi = linija.split("\\|");

					int ocena = Integer.parseInt(elementi[1]);
					int id = Integer.parseInt(elementi[0]);

					Ocena o = new Ocena();
					o.setId(id);
					o.setOcena(ocena);

					ocene.add(o);

				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom citanja ocena iz file!");
		}

	}

}
