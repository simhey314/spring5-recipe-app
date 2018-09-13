package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(final CategoryRepository categoryRepository, final UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(final Model model) {
		final Optional<Category> categoryOptional = categoryRepository.findByCategoryName("Fast Food");
		LOGGER.info("Category by name: " + categoryOptional);
		final Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("ml");
		LOGGER.info("UnitOfMeasure by name: " + unitOfMeasureOptional);

		return "index";
	}
}
