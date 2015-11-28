/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.controle;

import chamados.dao.TecnicoDAO;
import chamados.modelo.Tecnico;
import chamados.uteis.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author patricio
 */
@ManagedBean(name = "controleTecnico")
@SessionScoped
public class ControleTecnico implements Serializable{
    
    @EJB
    private TecnicoDAO dao;
    private Tecnico objeto;
    
    public ControleTecnico() {
        
    }
    
    public String listar() {
        return "/privado/tecnico/listar?faces-redirect=true";
    }
    
    public String novo() {
        objeto = new Tecnico();
        return "formulario";
    }
    
    public String salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
            return "listar";
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: "+e.getMessage());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.getObjectById(id);
            return "formulario";
        } catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());
            return "listar";
        }
    }
    
    public void remover(Integer id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto excluído com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: "+
                    Util.getMensagemErro(e));
        }        
    }
    
    
    public TecnicoDAO getDao() {
        return dao;
    }

    public void setDao(TecnicoDAO dao) {
        this.dao = dao;
    }

    public Tecnico getObjeto() {
        return objeto;
    }

    public void setObjeto(Tecnico objeto) {
        this.objeto = objeto;
    }
}
