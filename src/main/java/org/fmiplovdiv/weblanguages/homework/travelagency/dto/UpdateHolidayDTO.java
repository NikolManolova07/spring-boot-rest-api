package org.fmiplovdiv.weblanguages.homework.travelagency.dto;

import java.time.LocalDate;

public class UpdateHolidayDTO {
	
	private Long id;
	
	private Long location;

    private String title;
    
    private LocalDate startDate;
    
    private int duration;
    
    private double price;

    private int freeSlots;

	public UpdateHolidayDTO() {
		super();
	}

	public UpdateHolidayDTO(Long id, Long location, String title, LocalDate startDate, int duration, double price,
			int freeSlots) {
		super();
		this.id = id;
		this.location = location;
		this.title = title;
		this.startDate = startDate;
		this.duration = duration;
		this.price = price;
		this.freeSlots = freeSlots;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getFreeSlots() {
		return freeSlots;
	}

	public void setFreeSlots(int freeSlots) {
		this.freeSlots = freeSlots;
	}
}
