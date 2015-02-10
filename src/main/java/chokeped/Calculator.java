package chokeped;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;

public class Calculator {
	
	public static void main(String[] args) {
		 get("/hello", (req, res) -> "Hello World");
		 get("/hello123", (req, res) -> "Hello Varis");
		 get("/hello*", (req, res) -> "Hello "+req.splat()[0]);
		 
		 FreeMarkerEngine fme = new FreeMarkerEngine();
		 Configuration configuration = new Configuration();
		 try {
			configuration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		 fme.setConfiguration(configuration);
		 
		 get("/", (req, res) -> {
			 return new ModelAndView(null, "calculator.ftl");
		 }, fme);
		 post("/", (req, res) -> {
			 System.out.println(req.queryParams("a") + "/" + req.queryParams("b"));
			 HashMap<String, Object> hm = new HashMap<String, Object>();
			 hm.put("a", req.queryParams("a"));
			 hm.put("b", req.queryParams("b"));
			 hm.put("sum", Integer.parseInt(req.queryParams("a")) + Integer.parseInt(req.queryParams("b")));
			 return new ModelAndView(hm, "calculator.ftl");
		 }, fme);
	}
	
}
