package guru.springframework.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "/index", "/index.html", "/index.htm"})
public class IndexController {

	@GetMapping
	public String indexPage() {
		LoggerFactory.getLogger(IndexController.class).info("Some logging output!!!!!");
		return "index";
	}
}
