package org.japs.web.hatimiti;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.japs.web.JapsTomcat;

public class HatimitiShareDataServlet {

	public static void main(String[] args) throws Exception {
		new JapsTomcat("/hatimiti", new HttpServlet() {
			
			private Integer i = 0;
			
			private static final long serialVersionUID = 1L;
			@Override
			protected void service(
					HttpServletRequest req,
					HttpServletResponse resp)
						throws ServletException, IOException {

				Writer w = resp.getWriter();
				w.write(increment() + "\n");
				w.flush();
			}
			
			private int increment() {
				synchronized (i) {
					try {
						Thread.sleep(500);
						++i;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return i;
			}
			
		}).start();
	}
}
