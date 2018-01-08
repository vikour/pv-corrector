
package vista.correccion;

import java.util.List;
import javax.swing.JPanel;
import modelo.ConfiguracionCorreccion;
import modelo.CurvaMedida;
import modelo.MetodoCorreccion;

/**
 *
 * @author Vikour
 */
public abstract class JPanelConfigCorreccion extends JPanel {
   
   public abstract List<ConfiguracionCorreccion> getConfiguracionCorreccion();
   public abstract void setCurva(CurvaMedida original);

}
