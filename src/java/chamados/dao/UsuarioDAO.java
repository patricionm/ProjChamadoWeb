/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.dao;

import chamados.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author patricio
 */
@Stateful
public class UsuarioDAO<T> extends GenericDAO<Usuario>implements Serializable{

    public UsuarioDAO(){
        super();
        super.setPersistentClass(Usuario.class);
        super.getListOrder().add(new Order("id", "ID", "#"));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
    @Override
    public Usuario getObjectById(Integer id){
        Usuario obj = super.getEm().find(Usuario.class, id);
        
        return obj;
    }

    public boolean login(String usuario, String senha){
        Query query = super.getEm().createQuery("from Usuario where upper(login) = :usuario and upper(senha) = :senha");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if(!query.getResultList().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    public Usuario localizaPorNomeUsuario(String usuario){
        Usuario obj = (Usuario)
                super.getEm().createQuery("from Usuario where upper(login) = :usuario").
                        setParameter("usuario", usuario.toUpperCase()).getSingleResult();
        return obj;
    }
}
