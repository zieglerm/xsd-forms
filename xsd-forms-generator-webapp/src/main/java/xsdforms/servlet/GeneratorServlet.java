package xsdforms.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scala.Option;
import xsdforms.Generator;

public class GeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String schema = req.getParameter("schema");
		InputStream schemaIn = new ByteArrayInputStream(schema.getBytes());
		String idPrefix = "a-";
		Option<String> rootElement = Option.empty();
		Option<String> extraScript = Option.empty();
		String action = req.getParameter("action");
		if ("zip".equals(action)) {
			resp.setContentType("application/zip");
			resp.setHeader("Content-Disposition", "attachment; filename=site.zip");
			Generator.generateZip(schemaIn, resp.getOutputStream(),idPrefix, rootElement, extraScript);
		} else {
			resp.setContentType("text/html");
			Generator.generateHtml(schemaIn, resp.getOutputStream(),idPrefix,rootElement, extraScript);
		}
	}
	
	

}