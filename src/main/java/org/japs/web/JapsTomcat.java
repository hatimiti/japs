package org.japs.web;

import java.io.File;
import java.net.Socket;

import javax.servlet.http.HttpServlet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

public final class JapsTomcat {

	private final Tomcat tomcat;
	private final int port;
	
	public JapsTomcat(String path, HttpServlet servlet) {
		
		tomcat = new Tomcat();
		port = getRandomPort();
		tomcat.setPort(port);

		Context ctx = tomcat.addContext("/app", null);

		Tomcat.addServlet(ctx, "default", new DefaultServlet()).addMapping("/");
		Tomcat.addServlet(ctx, path, servlet).addMapping(path);
	}
	
	public void start() {
		try {
			tomcat.start();
			System.out.println("port: " + port + " で LISTEN します。");
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	
	private int getRandomPort() {
		try (Socket socket = new Socket()) {
			socket.bind(null);
			return socket.getLocalPort();
		} catch (Exception e) {
			return 8080;
		}
	}
}
