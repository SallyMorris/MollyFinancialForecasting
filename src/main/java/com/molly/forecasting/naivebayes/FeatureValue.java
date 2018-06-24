package com.molly.forecasting.naivebayes;

public class FeatureValue {
	private String name;
	private int occurence;

	public FeatureValue(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean returnValue = true;
		if (obj == null)
			returnValue = false;
		if (getClass() != obj.getClass())
			returnValue = false;
		FeatureValue other = (FeatureValue) obj;
		if (name == null) {
			if (other.name != null)
				returnValue = false;
		} else if (!name.equals(other.name))
			returnValue = false;
		return returnValue;
	}

	@Override
	public String toString() {
		return name;
	}

}
