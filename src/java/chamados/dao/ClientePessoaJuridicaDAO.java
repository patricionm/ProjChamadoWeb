
package chamados.dao;


import chamados.modelo.ClientePessoaJuridica;
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
public class ClientePessoaJuridicaDAO implements Serializable {

    @PersistenceContext(unitName = "SistChamados-WebPU")
    private EntityManager em;
    private List<ClientePessoaJuridica> listarTodos;

    public ClientePessoaJuridicaDAO(){
        
    }
    
    public void persist(ClientePessoaJuridica obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(ClientePessoaJuridica obj) throws Exception {
        em.merge(obj);
    }    
    
    public void remove(ClientePessoaJuridica obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public ClientePessoaJuridica getObjectById(Integer id) throws Exception {
        ClientePessoaJuridica obj = (ClientePessoaJuridica) em.find(ClientePessoaJuridica.class, id);
        // Forçando a recuperação dos telefones e desejos do banco
        obj.getTelefones().size();
        return obj;
    }    
    
    public List<ClientePessoaJuridica> getListarTodos() {
        return em.createQuery("from ClientePessoaJuridica order by razao_social").getResultList();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }



    public void setListarTodos(List<ClientePessoaJuridica> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
