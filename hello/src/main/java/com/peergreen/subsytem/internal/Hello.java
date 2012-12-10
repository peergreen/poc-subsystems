package com.peergreen.subsytem.internal;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Validate;

@Component
@Instantiate
public class Hello {

	@Validate
	public void start() {
		System.out.println("Starting Hello Component");
	}
	
}
