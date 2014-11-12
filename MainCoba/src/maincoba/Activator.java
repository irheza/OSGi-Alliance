package maincoba;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sensor.Sensor;


public class Activator implements BundleActivator {
    ServiceReference helloServiceReference;
    public void start(BundleContext context) throws Exception {

        helloServiceReference= context.getServiceReference(Sensor.class.getName());
        Sensor helloService =(Sensor)context.getService(helloServiceReference);
        //System.out.println(helloService.getSuhu());
        //Scanner cobainput = new Scanner(System.in);
        //String kata = cobainput.nextLine();
        //System.out.println("Hello "+kata);
     

    }
    public void stop(BundleContext context) throws Exception {
        System.out.println("Goodbye World!!");
        context.ungetService(helloServiceReference);
    }
}
