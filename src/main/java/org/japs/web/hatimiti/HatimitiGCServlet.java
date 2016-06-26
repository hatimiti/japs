package org.japs.web.hatimiti;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.japs.web.JapsTomcat;

public class HatimitiGCServlet {

	public static void main(String[] args) throws Exception {
		
		Object obj = new Object();
		System.out.println(obj);
		obj = null;
		
		new JapsTomcat("/hatimiti", new HttpServlet() {
			// ここに Servlet を実装
			private static final long serialVersionUID = 1L;
			
			private AtomicInteger i = new AtomicInteger(0);
			private List<String> strings = new Vector<>();
			
			@Override
			protected void service(
					HttpServletRequest req,
					HttpServletResponse resp)
						throws ServletException, IOException {

				Writer w = resp.getWriter();
				strings.add(req.getSession().getId());
				w.write(strings.get(i.getAndIncrement()) + '\n');
				w.flush();
			}
		}).start();
	}
}
