package com.test;

import org.testng.annotations.Test;

import com.pages.Crosswords;

import baseTest.BaseClass;
import utility.CommonUtils;

public class CrosswordsTest extends BaseClass{
	@Test
	public void SearchBooksTest() throws Exception {
		Crosswords cross = new Crosswords();
		
		cross.searchBooks();
		cross.sortBooks();
		cross.validateSorting();
		cross.findDiscountedBooks();
		cross.priceRange();
	}
}
