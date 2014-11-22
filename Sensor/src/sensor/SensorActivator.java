package sensor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

// TODO: Auto-generated Javadoc
/**
 * The Class SensorActivator.
 */
public class SensorActivator implements BundleActivator  {
    
    /** The sensor service registration. */
    ServiceRegistration sensorServiceRegistration;
    
    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        Sensor sersorService = new SensorImpl();
        sensorServiceRegistration =context.registerService(Sensor.class.getName(), sersorService, null);
        
    }
    
    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        sensorServiceRegistration.unregister();
    }
}