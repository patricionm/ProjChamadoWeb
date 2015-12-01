
package chamados.dao;


import chamados.modelo.Chamado;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class ChamadoDAO<T> extends GenericDAO<Chamado> implements Serializable {

    public ChamadoDAO(){
        super();
        super.setPersistentClass(Chamado.class);
        super.getListOrder().add(new Order("id", "ID", "#"));
        super.getListOrder().add(new Order("descricao", "Descrição", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
    
}
