package com.PubliciBot.DAO.Neodatis;

import com.PubliciBot.DAO.Interfaces.DAO;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;



public class DAONeodatis<T> implements DAO<T> {

	
	public void guardar(T t){
		ODB odb  = ODBFactory.open("mibase");
		odb.store(t);
		odb.close();
	}
	

	public void eliminar(T t){
		ODB odb  = ODBFactory.open("mibase");
		odb.store(t);
		odb.close();
	}

	
}
