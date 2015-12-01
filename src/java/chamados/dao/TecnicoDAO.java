/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.dao;

import chamados.modelo.Tecnico;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author patricio
 */
@Stateful
public class TecnicoDAO<T> extends GenericDAO<Tecnico> implements Serializable{
    
    public TecnicoDAO(){
        super();
        super.setPersistentClass(Tecnico.class);
        super.getListOrder().add(new Order("id", "ID", "#"));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
}
