package com.PubliciBot.DM;

public class Cliente {
	
	
	private long cuit;
	private String razonSocial;


	
	public Cliente(long cuit, String razonSocial) {
		this.cuit = cuit;
		this.razonSocial = razonSocial;
	}
	
	public long getCuit() {
		return cuit;
	}
	public void setCuit(long cuit) {
		this.cuit = cuit;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


}
