/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.dao;

import chamados.modelo.Cidade;
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
public class CidadeDAO implements Serializable{
    
    @PersistenceContext(unitName = "SistChamados-WebPU")
    private EntityManager em;
    private List<Cidade> listarTodos;

    public CidadeDAO(){
        
    }
    
    public void persist(Cidade obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Cidade obj) throws Exception {
        em.merge(obj);
    }    
    
    public void remove(Cidade obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Cidade getObjectById(Integer id) throws Exception {
        return (Cidade) em.find(Cidade.class, id);
    }    
    
    public List<Cidade> getListarTodos() {
        return em.createQuery("from Cidade order by nome").getResultList();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setListarTodos(List<Cidade> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
