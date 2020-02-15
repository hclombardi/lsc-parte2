package com.gft.parte2.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreValue {

	@JsonProperty("Loja")
    private List<StoreQtd> listStoreQtd;
	
	@JsonProperty("Qtde")
    private Integer quantity;
	
	@JsonProperty("Financeiro")
    private Double financial;
	
	@JsonProperty("Preco Medio")
    private Double averagePrice;
    
    public StoreValue () {    	
    	listStoreQtd = new ArrayList<StoreQtd>();
    }
    
    
	public List<StoreQtd> getListStoreQtd() {
		return listStoreQtd;
	}
	public void setListStoreQtd(List<StoreQtd> listStoreQtd) {
		this.listStoreQtd = listStoreQtd;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
	
	public Double getFinancial() {
		return financial;
	}
	public void setFinancial(Double financeiro) {
		this.financial = financeiro;
	}
	
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
   
}
