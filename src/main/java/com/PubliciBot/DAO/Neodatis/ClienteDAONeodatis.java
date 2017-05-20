package com.PubliciBot.DAO.Neodatis;


import com.PubliciBot.DM.Cliente;
import com.PubliciBot.DAO.Interfaces.ClienteDAO;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.ArrayList;


public class ClienteDAONeodatis extends DAONeodatis<Cliente> implements ClienteDAO {


    public int cantidadClientes() {
        ODB odb = ODBFactory.open("mibase");
        //consulta para la cant de clientes
        odb.close();
        return 0;
    }


    public ArrayList<Cliente> buscarporRazonSocial(String rs) {
        IQuery query = new CriteriaQuery(Cliente.class, Where.equal("razonSocial", rs));
        ODB odb = ODBFactory.open("mibase");
        return (ArrayList) odb.getObjects(query);

    }


}
