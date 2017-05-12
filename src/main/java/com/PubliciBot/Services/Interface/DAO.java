package com.PubliciBot.Services.Interface;

public interface DAO<T> {
	
	public void guardar(T t);
	public void eliminar(T t);

}
