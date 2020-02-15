package com.gft.parte2.repository;

import org.springframework.data.repository.CrudRepository;

import com.gft.parte2.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {

    List<Stock> findByProduct(String product);
    
    List<Stock> findByFileAndProduct(String file, String product);
    
    Stock findByFileAndProductAndQuantityAndPrice(String file, String product, int quantity, String price);    

}