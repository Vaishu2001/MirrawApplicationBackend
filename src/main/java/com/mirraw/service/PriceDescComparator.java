package com.mirraw.service;

import java.util.Comparator;

import com.mirraw.entity.Item;

public class PriceDescComparator implements Comparator<Item> {
	

	@Override
	public int compare(Item o1, Item o2) {
		// TODO Auto-generated method stub
		return o2.getItemPrice()-o1.getItemPrice();
	}
	

}
