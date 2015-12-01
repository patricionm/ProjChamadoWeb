
package chamados.dao;


import chamados.modelo.ClientePessoaJuridica;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class ClientePessoaJuridicaDAO<T> extends GenericDAO<ClientePessoaJuridica> implements Serializable {

    public ClientePessoaJuridicaDAO(){
        super();
        super.setPersistentClass(ClientePessoaJuridica.class);
        super.getListOrder().add(new Order("id", "ID", "#"));
        super.getListOrder().add(new Order("razao_social", "Razão Social", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
    public String retornaClientePJ(Integer idx) throws Exception{
     return ClientePessoaJuridicaDAO.super.getObjectById(idx).getRazaoSocial();
 }
    
 @Override
 public ClientePessoaJuridica getObjectById(Integer id) throws Exception{ 
        
       ClientePessoaJuridica obj = (ClientePessoaJuridica) ClientePessoaJuridicaDAO.this.getEm().find(ClientePessoaJuridica.class, id);
        
       obj.getTelefones().size();
       
       return obj;
    }
}
