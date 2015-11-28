/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.dao;

import chamados.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patricio
 */
@Stateless
public class UsuarioDAO implements Serializable{
    
    @PersistenceContext(unitName = "SistChamados-WebPU")
    private EntityManager em;
    private List<Usuario> listarTodos;

    public UsuarioDAO(){
        
    }
    
    public void persist(Usuario obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Usuario obj) throws Exception {
        em.merge(obj);
    }    
    
    public void remove(Usuario obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Usuario getObjectById(Integer id) throws Exception {
        return (Usuario) em.find(Usuario.class, id);
    }    
    
    public List<Usuario> getListarTodos() {
        return em.createQuery("from Usuario order by nome").getResultList();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setListarTodos(List<Usuario> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
