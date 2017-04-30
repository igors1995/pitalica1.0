package igor.logika;

import java.io.LineNumberInputStream;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import igor.domen.Ocena;
import igor.domen.Pitanje;
import igor.util.Util;

public class Algoritam {
	public LinkedList<Pitanje> listaPitanja = new LinkedList<>();
	public LinkedList<Pitanje> postavljenaPitanja = new LinkedList<>();
	public LinkedList<Ocena> listaOcena = new LinkedList<>();
	
	private String poslednjePitanje = "";
	
	private int brojac1 = 0;
	private int brojac2 = 0;
	private int brojac3 = 0;
	

	//private Random randomGenerator;

	public String getPoslednjePitanje() {
		return poslednjePitanje;
	}
	public void setPoslednjePitanje(String poslednjePitanje) {
		this.poslednjePitanje = poslednjePitanje;
	}

	public int getBrojac1() {
		return brojac1;
	}
	public void setBrojac1(int brojac1) {
		this.brojac1 = brojac1;
	}
	public int getBrojac2() {
		return brojac2;
	}
	public void setBrojac2(int brojac2) {
		this.brojac2 = brojac2;
	}
	public int getBrojac3() {
		return brojac3;
	}
	public void setBrojac3(int brojac3) {
		this.brojac3 = brojac3;
	}

	public final String lokacijaPitanja = "DATA/pitanja.txt";
	public final String lokacijaOcena = "DATA/ocene.txt";

//	public Algoritam() {
//		randomGenerator = new Random();
//	}

	public void inicijalizujListuZaUnosPitanja() {
		listaPitanja.clear();
		Util.iscitajPitanjaIUpisiUlistu(lokacijaPitanja, listaPitanja);

	}
	public void inicijalizujListuZaUnosOcena() {
		listaOcena.clear();
		Util.iscitajOceneIUpisiUlistu(lokacijaOcena, listaOcena);

	}

	public void unesiSvaPitanjaIzListe() {
		if (listaPitanja != null && listaPitanja.size() > 0) {
			Util.sacuvajPitanjaUFile(lokacijaPitanja, listaPitanja);
			listaPitanja.clear();
		}
	}
	public void unesiSveOceneIzListe() {
		if (listaOcena != null && listaOcena.size() > 0) {
			Util.sacuvajOceneUFile(lokacijaOcena, listaOcena);
			listaOcena.clear();
		}
	}
	

	public void dodajPitanjeUlistu(String tekst) {
		Pitanje pitanje = new Pitanje();
		
			//pitanje.setId(0);
		if(listaPitanja.size() == 0 || listaPitanja == null){pitanje.setId(0);}else{
			pitanje.setId(listaPitanja.size());
		}
		pitanje.setTekst(tekst);

		listaPitanja.add(pitanje);
	}
	
	public void dodajOcenuUlistu(String tekst) {
		Ocena ocena = new Ocena();
		
			//ocena.setId(0);
		
			if(listaOcena.size() == 0 || listaOcena == null){ocena.setId(0);}else{
			ocena.setId(listaOcena.size());
			}
		ocena.setOcena(1);

		listaOcena.add(ocena);
	}
	

	public String prikaziSvaPitanja() {
		LinkedList<Pitanje> pitanja = new LinkedList<>();

		Util.iscitajPitanjaIUpisiUlistu(lokacijaPitanja, pitanja);
		String tekst = "";
		int brojac = 1;

		for (Pitanje pitanje : pitanja) {
			tekst += brojac + "." + pitanje.getTekst() + "\n";
			brojac++;
		}
		return tekst;
	}

	public void napuniListuPitanjima() {
		listaPitanja.clear();
		Util.iscitajPitanjaIUpisiUlistu(lokacijaPitanja, listaPitanja);
	}
	
	public void napuniListuOcenama(){
		listaOcena.clear();
		Util.iscitajOceneIUpisiUlistu(lokacijaOcena, listaOcena);
		
	}

	public String postaviPitanje() throws Exception {
		String tekst = "";

	//int randomIndeks = randomGenerator.nextInt(listaPitanja.size());
		int randomIndeks = (int) ((Math.random() * listaPitanja.size()));
				Pitanje pitanje = listaPitanja.get(randomIndeks);
		tekst = pitanje.getTekst();
		
		if(tekst.equals(getPoslednjePitanje()) && postojeIDrugaPitanja(getPoslednjePitanje()) > -1){
			randomIndeks = postojeIDrugaPitanja(getPoslednjePitanje());
			
			pitanje = listaPitanja.get(randomIndeks);
			tekst = pitanje.getTekst();
		}

		postavljenaPitanja.add(pitanje);
		listaPitanja.remove(randomIndeks);
		setPoslednjePitanje(tekst);
		return tekst;
	}
	
	private int postojeIDrugaPitanja(String tekst){
		for (int i = 0; i < listaPitanja.size(); i++) {
			if(!tekst.equals(listaPitanja.get(i))){
				return i;
			}
		}
		return -1;
	}
	
	
		
		public int prondajiID(String tekst){
			for (Pitanje pitanje : postavljenaPitanja) {
				if(pitanje.getTekst().equals(tekst)){
					int id = pitanje.getId();
					postavljenaPitanja.remove(pitanje);
					System.out.println(id);
					return id;
				}
			}
			System.out.println("Greska pronadji ID");
			return -1;
		}
	
	
	public void nadjiPitanjeUFileOcenaIPostaviOcenu(int id, int ocena){
		LinkedList<Ocena> ocene = new LinkedList<>();
		
		Util.iscitajOceneIUpisiUlistu(lokacijaOcena, ocene);
		for (Ocena ocena2 : ocene) {
			if(ocena2.getId() == id){
				ocena2.setOcena(ocena);
				break;
			}
		}
		Util.sacuvajOceneUFile(lokacijaOcena, ocene);
	}
	public void ispisiOcene(LinkedList<Ocena> listaOcena){
		for (Ocena ocena : listaOcena) {
			System.out.println(ocena.getId() + " " + ocena.getOcena());
		}
	}

	
	public void napraviListuPitanjaUskladuSaOcenama(){
		inicijalizujListuZaUnosOcena();
		for (Ocena ocena : listaOcena) {
				if(ocena.getOcena() != 1){
					duplirajPitanjeUListi(ocena.getOcena(), ocena.getId());
				}
			}
		}
	
	private void duplirajPitanjeUListi(int ocena, int id){
		LinkedList<Pitanje> pitanjaIzFajla = new LinkedList<>();
		
		Util.iscitajPitanjaIUpisiUlistu(lokacijaPitanja, pitanjaIzFajla);
		
		
		for (Pitanje pitanje : pitanjaIzFajla) {
			if(pitanje.getId() == id){
				if(ocena == 2){
					Pitanje p = new Pitanje();
					p.setId(pitanje.getId());
					p.setTekst(pitanje.getTekst());
					
					Pitanje p2 = new Pitanje();
					p2.setId(pitanje.getId());
					p2.setTekst(pitanje.getTekst());
					
					listaPitanja.add(p2);
					listaPitanja.add(p);
					
					return;
				}else{
					Pitanje p = new Pitanje();
					p.setId(pitanje.getId());
					p.setTekst(pitanje.getTekst());
					
					Pitanje p1 = new Pitanje();
					p1.setId(pitanje.getId());
					p1.setTekst(pitanje.getTekst());
					
					Pitanje p2 = new Pitanje();
					p2.setId(pitanje.getId());
					p2.setTekst(pitanje.getTekst());
					
					Pitanje p3 = new Pitanje();
					p3.setId(pitanje.getId());
					p3.setTekst(pitanje.getTekst());
				
					listaPitanja.add(p);
					listaPitanja.add(p1);
					listaPitanja.add(p2);
					listaPitanja.add(p3);
				
				}
			}
		}
	}
		
		
		
	}


//	public static void main(String[] args) {
//		LinkedList<Ocena> ocene = new LinkedList<>();
//		Algoritam a = new Algoritam();
//		Util.iscitajOceneIUpisiUlistu("D:/JAVA PROJEKTI/ECLIPSE/Pitalica.1.0/src/igor/DATA/ocene.txt",ocene);
//		a.ispisiOcene(ocene);
//	}