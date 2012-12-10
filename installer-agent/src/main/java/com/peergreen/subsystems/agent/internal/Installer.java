package com.peergreen.subsystems.agent.internal;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.subsystem.Subsystem;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


public class Installer implements BundleActivator, ServiceTrackerCustomizer<Subsystem, Boolean> {

	private ServiceTracker<Subsystem,Boolean> tracker;
	
	private BundleContext context;

	private Scenario scenario1 = new ApplicationWithIpojoInsideScenario();
	private Scenario scenario2 = new ApplicationWithIpojoOutsideScenario();
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Agent started");
		this.context = context;
		
		tracker = new ServiceTracker<Subsystem, Boolean>(context, context.createFilter(filter()), this);
		tracker.open();

	}

	private String filter() {
		return "(&(objectClass=" + Subsystem.class.getName() + ")(subsystem.id=0))";
	}

	private String maven(String gId, String aId, String version, String ext) {
		String url = "file://" + System.getProperty("user.home") + "/.m2/repository/"
				+ gId.replace(".", "/") + "/" + aId + "/" + version + "/"
				+ aId + "-" + version + "." + ext;
		System.out.println("maven: " + url);
		return url;
	}

	public void stop(BundleContext context) throws Exception {
		tracker.close();
		
	}

	public Boolean addingService(ServiceReference<Subsystem> reference) {
		Subsystem root = context.getService(reference);
		
		try {
			scenario1.install(root);
			scenario2.install(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	public void modifiedService(ServiceReference<Subsystem> reference, Boolean service) {
		// TODO Auto-generated method stub
		
	}

	public void removedService(ServiceReference<Subsystem> reference, Boolean service) {

		try {
			scenario2.uninstall();
			scenario1.uninstall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private interface Scenario {
		void install(Subsystem root) throws Exception;
		void uninstall() throws Exception;
	}
	
	private class ApplicationWithIpojoInsideScenario implements Scenario {

		private Subsystem one;
		
		public void install(Subsystem root) {
			one = root.install(maven("com.peergreen.subsystems", "ipojo-inside-application", "0.0.1-SNAPSHOT", "esa"));
			one.start();
		}

		public void uninstall() {
			one.stop();
			one.uninstall();
		}
		
	}
	
	private class ApplicationWithIpojoOutsideScenario implements Scenario {

		private Bundle ipojoBundle;
		private Bundle ipojoSubsystemBundle;
		private Subsystem one;

		public void install(Subsystem root) throws Exception {
			ipojoBundle = context.installBundle(maven("org.apache.felix", "org.apache.felix.ipojo", "1.9.0-SNAPSHOT", "jar"));
			ipojoSubsystemBundle = context.installBundle(maven("org.apache.felix", "org.apache.felix.ipojo.subsystem", "1.9.0-SNAPSHOT", "jar"));
			
			ipojoBundle.start();
			ipojoSubsystemBundle.start();

			one = root.install(maven("com.peergreen.subsystems", "ipojo-outside-application", "0.0.1-SNAPSHOT", "esa"));
			one.start();
		}

		public void uninstall() throws Exception {
			one.stop();
			one.uninstall();

			ipojoSubsystemBundle.uninstall();
			ipojoBundle.uninstall();
		}
	}

}
