package com.carslon.dto;

public enum TravelType {
	air(10L),
	hotel(6L),
	shareShift(null);

	private final Long travelTypeId;

	TravelType(Long travelType) {
		this.travelTypeId = travelType;
	}

	public Long getId() {
		return travelTypeId;
	}

}
