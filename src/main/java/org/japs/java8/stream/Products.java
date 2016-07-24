package org.japs.java8.stream;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Products {

	public static List<Product> all() {

		Company c1 = new Company("Jahoo", "111-2222-3333");
		Company c2 = new Company("Aooale", "111-9393-8383");
		Company c3 = new Company("Vamazon", "222-5555-1234");
		Company c4 = new Company("Acebook", "333-9899-1111");
		Company c5 = new Company("Peitter", "444-1111-3355");

		return Arrays.asList(
				 new Product("本", 2_980, LocalDate.of(2012, 2, 12), Arrays.asList(c1, c3))
				,new Product("ハサミ", 300, LocalDate.of(2012, 6, 22), Arrays.asList(c1, c2, c3, c4, c5))
				,new Product("ギター", 89_980, LocalDate.of(2013, 1, 1), Arrays.asList(c4, c3))
				,new Product("扇風機", 8_000, LocalDate.of(2012, 2, 12), Arrays.asList(c3))
				,new Product("机", 19_980, LocalDate.of(2015, 9, 2), Arrays.asList(c3, c4))
				,new Product("謎の物体", 9, LocalDate.of(2012, 12, 29), Arrays.asList(c5, c3))
				,new Product("赤ペン", 120, LocalDate.of(2013, 9, 8), Arrays.asList(c2, c3))
				,new Product("青ペン", 120, LocalDate.of(2014, 6, 28), Arrays.asList(c1, c4))
				,new Product("パン", 150, LocalDate.of(2016, 8, 31), Arrays.asList(c2, c5))
				,new Product("水", 300, LocalDate.of(2016, 8, 1), Arrays.asList(c5, c3))
				,new Product("スーツ", 32_980, LocalDate.of(2015, 8, 3), Arrays.asList(c1, c3))
				,new Product("衣類", 5_980, LocalDate.of(2015, 10, 20), Arrays.asList(c2, c5))
				,new Product("時計", 2_980, LocalDate.of(2016, 6, 2), Arrays.asList(c2))
				,new Product("PC", 210_000, LocalDate.of(2016, 1, 31), Arrays.asList(c4, c5))
				,new Product("車", 1_342_980, LocalDate.of(2015, 8, 22), Arrays.asList(c3))
		);
	}

}

class Product {

	private String name;
	private int value;
	private LocalDate saleDay;
	private List<Company> salesCompanies;

	public Product(String name, int value, LocalDate saleDay, List<Company> salesCompanies) {
		this.name = name;
		this.value = value;
		this.saleDay = saleDay;
		this.salesCompanies = salesCompanies;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public LocalDate getSaleDay() {
		return saleDay;
	}

	public List<Company> getSalesCompanies() {
		return salesCompanies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value, saleDay, salesCompanies);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !Product.class.equals(obj.getClass())) {
			return false;
		}

		Product target = (Product) obj;
		return this.name.equals(target.name)
				&& this.value == target.value
				&& this.saleDay.equals(target.saleDay)
				&& this.salesCompanies.equals(target.salesCompanies)
				;
	}

	@Override
	public String toString() {
		return String.format("{ \"name\": \"%s\", \"value\": %s, \"saleDay\": \"%s\", \"salesCompanies\": %s }",
				name, value, saleDay, salesCompanies);
	}
}

class Company {

	private String name;
	private String telNo;

	public Company(String name, String telNo) {
		this.name = name;
		this.telNo = telNo;
	}

	public String getName() {
		return name;
	}

	public String getTelNo() {
		return telNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, telNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !Company.class.equals(obj.getClass())) {
			return false;
		}

		Company target = (Company) obj;
		return this.name.equals(target.name)
				&& this.telNo.equals(target.telNo);
	}
	
	@Override
	public String toString() {
		return String.format("{ \"name\": \"%s\", \"telNo\": \"%s\" }", name, telNo);
	}
}