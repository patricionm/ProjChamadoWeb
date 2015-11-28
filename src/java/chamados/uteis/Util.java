
package chamados.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
public class Util {
    
    public static void mensagemErro(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void mensagemInformacao(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
    
    public static void mensagemAlerta(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }     

    public static String getMensagemErro(Exception e) {
        while(e.getCause() != null){
            e  = (Exception) e.getCause();
        }
        String erro = e.getMessage();
        if (erro.contains("viola restrição de chave estrangeira")){
            erro = "Objeto não pode ser excluido "
                    + "pois é referenciado por outro registro";
        }
        return erro;
    }
}
