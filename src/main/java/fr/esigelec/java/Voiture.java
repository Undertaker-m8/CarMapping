package fr.esigelec.java;

public class Voiture {
	private String codePostale;
	private String nomVille;
	private String lat;
	private String lon;

	
	
	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Voiture(String codePostale, String nomVille, String lat, String lon) {
		this.codePostale = codePostale;
		this.nomVille = nomVille;
		this.lat = lat;
		this.lon = lon;
		
		
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	@Override
	public String toString() {
		return "Voiture [codePostale=" + codePostale + ", nomVille=" + nomVille + ", lat=" + lat + ", lon=" + lon
				+  "]";
	}

	
	
	

}
