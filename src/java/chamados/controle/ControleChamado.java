/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.controle;

import chamados.dao.ChamadoDAO;
import chamados.dao.ClientePessoaFisicaDAO;
import chamados.dao.ClientePessoaJuridicaDAO;
import chamados.dao.TecnicoDAO;
import chamados.dao.UsuarioDAO;
import chamados.modelo.Chamado;
import chamados.uteis.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author patricio
 */
@ManagedBean(name = "controleChamado")
@SessionScoped
public class ControleChamado implements Serializable{
    
    @EJB
    private ChamadoDAO<Chamado> dao;
    private Chamado objeto;
    
    @EJB
    private ClientePessoaFisicaDAO pfdao;
    @EJB
    private ClientePessoaJuridicaDAO pjdao;
    @EJB
    private TecnicoDAO tdao;
    @EJB
    private UsuarioDAO udao;

    public ControleChamado() {
    }
    
    public String listar() {
        return "/privado/chamado/listar?faces-redirect=true";
    }
    
    public String novo() {
        objeto = new Chamado(); 
        return "formulario";
    }
    
    public String salvar() {
        try {
            if (objeto.getId() == null) {
                objeto.setDtAbertura(Calendar.getInstance());
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
    
    public String fechar() {
        try {
            if (objeto.getId() != null && objeto.getLaudo() != null) {
                objeto.setDtFechamento(Calendar.getInstance());
                dao.merge(objeto);
                Util.mensagemInformacao("Objeto persistido com sucesso: "+objeto.getLaudo());   
                return "listar";
            }
            else{
                Util.mensagemInformacao("É necessário preencher o laudo para fechar o chamado");
                return "formulario";
            }
            
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: "+e.getMessage());
            return "listar";
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

    public ChamadoDAO getDao() {
        return dao;
    }

    public void setDao(ChamadoDAO dao) {
        this.dao = dao;
    }

    public Chamado getObjeto() {
        return objeto;
    }

    public void setObjeto(Chamado objeto) {
        this.objeto = objeto;
    }

    public ClientePessoaFisicaDAO getPfdao() {
        return pfdao;
    }

    public void setPfdao(ClientePessoaFisicaDAO pfdao) {
        this.pfdao = pfdao;
    }

    public ClientePessoaJuridicaDAO getPjdao() {
        return pjdao;
    }

    public void setPjdao(ClientePessoaJuridicaDAO pjdao) {
        this.pjdao = pjdao;
    }

    public TecnicoDAO getTdao() {
        return tdao;
    }

    public void setTdao(TecnicoDAO tdao) {
        this.tdao = tdao;
    }

    public UsuarioDAO getUdao() {
        return udao;
    }

    public void setUdao(UsuarioDAO udao) {
        this.udao = udao;
    }  
    
    public String buscaCliente(Integer idx, String t) throws Exception{
        if(t.equals("F"))
            return pfdao.retornaClientePF(idx);
        else
            return pjdao.retornaClientePJ(idx);
    }
    
}
