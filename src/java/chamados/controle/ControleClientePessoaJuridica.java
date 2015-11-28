/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamados.controle;

import chamados.dao.CidadeDAO;
import chamados.dao.ClientePessoaJuridicaDAO;
import chamados.modelo.ClientePessoaJuridica;
import chamados.modelo.Telefone;
import chamados.uteis.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author patricio
 */
@ManagedBean(name = "controleClientePessoaJuridica")
@ViewScoped
public class ControleClientePessoaJuridica implements Serializable{
    
    @EJB
    private ClientePessoaJuridicaDAO dao;
    private ClientePessoaJuridica objeto;
    
    @EJB
    private CidadeDAO cidadeDao;
    private Telefone telefone;
    private Boolean novoTelefone;

    public ControleClientePessoaJuridica() {
    }
    
    public String listar() {
        return "/privado/clientepessoajuridica/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new ClientePessoaJuridica();        
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");            
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: "+e.getMessage());            
        }
    }    
    
    public void editar(Integer id){
        try {
            objeto = dao.getObjectById(id);            
        } catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());            
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
    
    public void novoTelefone(){
        telefone = new Telefone();
        novoTelefone = true;
    }
    
    public void alterarTelefone(int index){
        telefone = objeto.getTelefones().get(index);
        novoTelefone = false;
    }
    
    public void salvarTelefone(){
        if (novoTelefone){
            objeto.adicionarTelefone(telefone);
        }
        Util.mensagemInformacao("Operação executada com sucesso!");
    }
    
    public void removerTelefone(int index){
        objeto.removerTelefone(index);
        Util.mensagemInformacao("Operação executada com sucesso!");
    }
    
    public ClientePessoaJuridicaDAO getDao() {
        return dao;
    }
    
    public void setDao(ClientePessoaJuridicaDAO dao) {
        this.dao = dao;
    }
    
    public ClientePessoaJuridica getObjeto() {
        return objeto;
    }
    
    public void setObjeto(ClientePessoaJuridica objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDAO cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }
    
}
