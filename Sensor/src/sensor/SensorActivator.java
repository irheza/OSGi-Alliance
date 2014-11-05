package sensor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import function.SensorImpl;

public class SensorActivator implements BundleActivator  {
    ServiceRegistration sensorServiceRegistration;
    public void start(BundleContext context) throws Exception {
        Sensor helloService = new SensorImpl();
        sensorServiceRegistration =context.registerService(Sensor.class.getName(), helloService, null);
        
    }
    public void stop(BundleContext context) throws Exception {
        sensorServiceRegistration.unregister();
    }
}