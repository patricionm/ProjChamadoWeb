
package chamados.dao;


import chamados.modelo.ClientePessoaFisica;
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
public class ClientePessoaFisicaDAO implements Serializable {

    @PersistenceContext(unitName = "SistChamados-WebPU")
    private EntityManager em;
    private List<ClientePessoaFisica> listarTodos;

    public ClientePessoaFisicaDAO(){
        
    }
    
    public void persist(ClientePessoaFisica obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(ClientePessoaFisica obj) throws Exception {
        em.merge(obj);
    }    
    
    public void remove(ClientePessoaFisica obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public ClientePessoaFisica getObjectById(Integer id) throws Exception {
        ClientePessoaFisica obj = (ClientePessoaFisica) em.find(ClientePessoaFisica.class, id);
        // Forçando a recuperação dos telefones e desejos do banco
        obj.getTelefones().size();
        return obj;
    }    
    
    public List<ClientePessoaFisica> getListarTodos() {
        return em.createQuery("from ClientePessoaFisica order by nome").getResultList();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }



    public void setListarTodos(List<ClientePessoaFisica> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
