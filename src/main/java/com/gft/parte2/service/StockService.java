package com.gft.parte2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.parte2.model.Stock;
import com.gft.parte2.model.StoreQtd;
import com.gft.parte2.model.StoreValue;
import com.gft.parte2.repository.StockRepository;

@Service
public class StockService {

	private static final Logger log = LoggerFactory.getLogger(StockService.class);

	@Autowired
	private StockRepository stockRepo;
	
	public static int currentLucky;

	public List<StoreValue> calculateStoreValues(String product, int numberStores) {

		log.info("Calculating store values...");

		List<Stock> stockList = stockRepo.findByProduct(product);
		//List<Stock> stockList = stockRepo.findByFileAndProduct("data_1.json", product);

		List<StoreValue> storeList = new ArrayList<StoreValue>();		
		for (int i=0; i < numberStores; i++) storeList.add(new StoreValue());
		
		currentLucky = 0;

		// Go over all the stock
		stockList.forEach(x -> {
			
			boolean gotLucky = false;
			
			for (int counter = 0; counter < numberStores; counter++) {

				StoreQtd storeqtd = new StoreQtd();
				
				// Verifies that the division is exact
				if (x.getQuantity() % numberStores == 0) {
					storeqtd.setQuantity(x.getQuantity() / numberStores);
				}
				else {					
					// Someone will take more product
					if (counter == currentLucky) {
						storeqtd.setQuantity((x.getQuantity() / numberStores) + (x.getQuantity() % numberStores));
						gotLucky = true; 
					}
					else {
						storeqtd.setQuantity(x.getQuantity() / numberStores);
					}					
				}				
				
				storeqtd.setPrice(Double.parseDouble(x.getPrice().replace("$", "")));				
				storeqtd.setVolume(storeqtd.getQuantity() * storeqtd.getPrice());				
				storeList.get(counter).getListStoreQtd().add(storeqtd);
			}
			
			if (gotLucky) {
				currentLucky++;
				if (currentLucky >= numberStores) currentLucky = 0;
			}
				

		});	
		
		this.calculateFinancial(storeList);

		log.info("Calculated store values");

		return storeList;
	}
	

	public void calculateFinancial(List<StoreValue> storeValues) {

		log.info("Calculating quantities, financials and average price");

		storeValues.forEach( x -> {
			
			Integer totalQtd = 0;
			Double totalFinancial = 0.00;
			
			for (StoreQtd storeQtd : x.getListStoreQtd()) {				
				totalQtd = totalQtd + storeQtd.getQuantity();				
				totalFinancial = totalFinancial + storeQtd.getQuantity() * storeQtd.getPrice();
			}

			x.setQuantity(totalQtd);
			x.setFinancial(totalFinancial);
			x.setAveragePrice(totalFinancial / totalQtd);			
			
		});		
	}

}
