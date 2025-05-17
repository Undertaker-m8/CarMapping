package fr.esigelec.jee;

import java.util.Date;

public class VoitureClass {
	private String codgeo;
	private String libgeo;
	private String epci;
	private String libepci;
	private Date date_arrete;
	private int nb_vp_rech_elec;
	private int nb_vp_rech_gaz;
	private int nb_vp;
	
	public VoitureClass(String codgeo, String libgeo, String epci, String libepci, Date date_arrete, int nb_vp_rech_elec, int nb_vp_rech_gaz, int nb_vp) {
		this.codgeo = codgeo;
		this.libgeo = libgeo;
		this.epci = epci;
		this.libepci = libepci;
		this.date_arrete = date_arrete;
		this.nb_vp_rech_elec = nb_vp_rech_elec;
		this.nb_vp_rech_gaz = nb_vp_rech_gaz;
		this.nb_vp = nb_vp;
	}

	@Override
	public String toString() {
		return "VoitureClass [codgeo=" + codgeo + ", libgeo=" + libgeo + ", epci=" + epci + ", libepci=" + libepci
				+ ", date_arrete=" + date_arrete + ", nb_vp_rech_elec=" + nb_vp_rech_elec + ", nb_vp_rech_gaz="
				+ nb_vp_rech_gaz + ", nb_vp=" + nb_vp + "]";
	}

	public String getCodgeo() {
		return codgeo;
	}

	public void setCodgeo(String codgeo) {
		this.codgeo = codgeo;
	}

	public String getLibgeo() {
		return libgeo;
	}

	public void setLibgeo(String libgeo) {
		this.libgeo = libgeo;
	}

	public String getEpci() {
		return epci;
	}

	public void setEpci(String epci) {
		this.epci = epci;
	}

	public String getLibepci() {
		return libepci;
	}

	public void setLibepci(String libepci) {
		this.libepci = libepci;
	}

	public Date getDate_arrete() {
		return date_arrete;
	}

	public void setDate_arrete(Date date_arrete) {
		this.date_arrete = date_arrete;
	}

	public int getNb_vp_rech_elec() {
		return nb_vp_rech_elec;
	}

	public void setNb_vp_rech_elec(int nb_vp_rech_elec) {
		this.nb_vp_rech_elec = nb_vp_rech_elec;
	}

	public int getNb_vp_rech_gaz() {
		return nb_vp_rech_gaz;
	}

	public void setNb_vp_rech_gaz(int nb_vp_rech_gaz) {
		this.nb_vp_rech_gaz = nb_vp_rech_gaz;
	}

	public int getNb_vp() {
		return nb_vp;
	}

	public void setNb_vp(int nb_vp) {
		this.nb_vp = nb_vp;
	}
	
	

}
