package com.carslon.contractupload.model;

public class BusinessDivision {

	private Long id;
	private String name;
	private String group;
	private String code;

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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 1;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (group != null ? group.hashCode() : 0);
		result = 31 * result + (code != null ? code.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) return false;

		BusinessDivision that = (BusinessDivision) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (group != null ? !group.equals(that.group) : that.group != null)
			return false;
		if (code != null ? !code.equals(that.code) : that.code != null)
			return false;

		return true;
	}

	public boolean isParent() {
		return name.equals(group);
	}

	@Override
	public String toString() {
		return "BusinessDivision [id=" + id + ", name=" + name + ", group=" + group + " code="+ code+"]";
	}
}
