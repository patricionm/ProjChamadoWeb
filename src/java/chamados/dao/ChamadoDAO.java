
package chamados.dao;


import chamados.modelo.Chamado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateless
public class ChamadoDAO implements Serializable {

    @PersistenceContext(unitName = "SistChamados-WebPU")
    private EntityManager em;
    private List<Chamado> listarTodos;

    public ChamadoDAO(){
        
    }
    
    public void persist(Chamado obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(Chamado obj) throws Exception {
        em.merge(obj);
    }    
    
    public void remove(Chamado obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Chamado getObjectById(Integer id) throws Exception {
        Chamado obj = (Chamado) em.find(Chamado.class, id);
        return obj;
    }    
    
    public List<Chamado> getListarTodos() {
        return em.createQuery("from Chamado order by dt_abertura").getResultList();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }



    public void setListarTodos(List<Chamado> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
