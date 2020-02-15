package com.gft.parte2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gft.parte2.model.StoreValue;
import com.gft.parte2.service.StockService;

@Controller
@RequestMapping("stock")
public class StockController {

	private static final Logger log = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	@GetMapping("store/{product}/{numberStores}")
	public ResponseEntity<List<StoreValue>> getProductsByStock(
			@PathVariable("product") String product,
			@PathVariable("numberStores") Integer numberStores) {

		log.info("Calculating store values for product: " + product + " to " + numberStores + " stores");

		List<StoreValue> listStores = stockService.calculateStoreValues(product, numberStores);
		return new ResponseEntity<List<StoreValue>>(listStores, HttpStatus.OK);
	}

}
