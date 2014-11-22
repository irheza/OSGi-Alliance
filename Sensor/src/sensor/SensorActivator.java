package sensor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SensorActivator implements BundleActivator  {
    ServiceRegistration sensorServiceRegistration;
    public void start(BundleContext context) throws Exception {
        Sensor sersorService = new SensorImpl();
        sensorServiceRegistration =context.registerService(Sensor.class.getName(), sersorService, null);
        
    }
    public void stop(BundleContext context) throws Exception {
        sensorServiceRegistration.unregister();
    }
}