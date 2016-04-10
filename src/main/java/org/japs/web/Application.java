package org.japs.web;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

public class Application {

	public static void main(String[] args) throws Exception {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		File base = new File("src/main/resources/static");
		Context ctx = tomcat.addContext("/app", base.getAbsolutePath());

		Tomcat.addServlet(ctx, "default", new DefaultServlet()).addMapping("/");

		Tomcat.addServlet(ctx, "hello", new HttpServlet() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void service(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				Writer w = resp.getWriter();
				w.write("Hello, World");
				w.flush();
			}
		}).addMapping("/hello");

		tomcat.start();
		Desktop.getDesktop().browse(new URI("http://localhost:8080/app/hello"));
		tomcat.getServer().await();

	}
}
