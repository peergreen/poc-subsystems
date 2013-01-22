package com.peergreen.subsystems.showcase.english;

import com.peergreen.subsystems.showcase.HelloService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created with IntelliJ IDEA.
 * User: guillaume
 * Date: 21/01/13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        context.registerService(HelloService.class,
                                new HelloService() {
                                    @Override
                                    public String hello(String who) {
                                        return "Hello " + who;
                                    }
                                },
                                null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
