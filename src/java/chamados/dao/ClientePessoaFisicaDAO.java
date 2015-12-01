
package chamados.dao;


import chamados.modelo.ClientePessoaFisica;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class ClientePessoaFisicaDAO<T> extends GenericDAO<ClientePessoaFisica> implements Serializable {

    public ClientePessoaFisicaDAO(){
        super();
        super.setPersistentClass(ClientePessoaFisica.class);
        super.getListOrder().add(new Order("id", "ID", "#"));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }  
 
 public String retornaClientePF(Integer idx) throws Exception{
     return ClientePessoaFisicaDAO.super.getObjectById(idx).getNome();
 }
 
 @Override
 public ClientePessoaFisica getObjectById(Integer id) throws Exception{ 
        
       ClientePessoaFisica obj = (ClientePessoaFisica) ClientePessoaFisicaDAO.this.getEm().find(ClientePessoaFisica.class, id);
        
       obj.getTelefones().size();
       
       return obj;
    }

}
