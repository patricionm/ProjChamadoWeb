/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.dao;

import chamados.modelo.Tecnico;
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
public class TecnicoDAO implements Serializable{
    
    @PersistenceContext(unitName = "SistChamados-WebPU")
    private EntityManager em;
    private List<Tecnico> listarTodos;

    public TecnicoDAO(){
        
    }
    
    public void persist(Tecnico obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Tecnico obj) throws Exception {
        em.merge(obj);
    }    
    
    public void remove(Tecnico obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Tecnico getObjectById(Integer id) throws Exception {
        return (Tecnico) em.find(Tecnico.class, id);
    }    
    
    public List<Tecnico> getListarTodos() {
        return em.createQuery("from Tecnico order by nome").getResultList();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setListarTodos(List<Tecnico> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
