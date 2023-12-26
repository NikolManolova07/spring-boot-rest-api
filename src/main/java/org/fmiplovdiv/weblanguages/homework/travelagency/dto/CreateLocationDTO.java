package org.fmiplovdiv.weblanguages.homework.travelagency.dto;

public class CreateLocationDTO {
	
	private String street;
    
    private String number;
    
    private String city;

    private String country;
    
    private String imageUrl;

	public CreateLocationDTO() {
		super();
	}

	public CreateLocationDTO(String street, String number, String city, String country, String imageUrl) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
		this.imageUrl = imageUrl;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}    
}
