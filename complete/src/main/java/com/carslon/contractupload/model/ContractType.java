package com.carslon.contractupload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ContractType {

	private static final long serialVersionUID = 1L;
	private Long id;

	private String name;

	@JsonIgnore
	private String lastModifiedBy;

	@JsonIgnore
	private Long travelTypeId;


	@JsonIgnore
	private Date lastModifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getTravelTypeId() {
		return travelTypeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ContractType that = (ContractType) o;

		if (id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if (name != null ? !name.equals(that.name) : that.name != null) {
			return false;
		}
		if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null) {
			return false;
		}
		if (travelTypeId != null ? !travelTypeId.equals(that.travelTypeId) : that.travelTypeId != null) {
			return false;
		}
		return lastModifiedDate != null ? lastModifiedDate.equals(that.lastModifiedDate) : that.lastModifiedDate == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
		result = 31 * result + (travelTypeId != null ? travelTypeId.hashCode() : 0);
		result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ContractType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lastModifiedBy='" + lastModifiedBy + '\'' +
				", travelTypeId=" + travelTypeId +
				", lastModifiedDate=" + lastModifiedDate +
				'}';
	}
}
