package com.peergreen.subsystems.showcase.consumer;

import com.peergreen.subsystems.showcase.HelloService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Created with IntelliJ IDEA.
 * User: guillaume
 * Date: 21/01/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        ServiceReference<HelloService> reference = context.getServiceReference(HelloService.class);
        if (reference == null) {
            System.err.printf("HelloService is missing.");
        } else {
            HelloService service = context.getService(reference);
            System.out.printf("Bundle %d calling service from Bundle %d%n",
                    context.getBundle().getBundleId(),
                    reference.getBundle().getBundleId());
            System.out.printf("Result[%s]%n", service.hello("Guillaume"));
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
