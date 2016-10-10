import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * The root path that activates the rest, configures the JaxRS restful services with an RESTeasy implementation
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016
 */

@ApplicationPath("/rs")
public class JaxRsActivator extends Application { 
	public JaxRsActivator()
	{

	} 
}  