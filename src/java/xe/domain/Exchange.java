package xe.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("exchanges")
public class Exchange {

	public Exchange() {
	}

	public Exchange(Exchange exch) {
		this.currFrom = exch.getCurrFrom();
		this.currTo = exch.getCurrTo();
		this.rateFrom = exch.getRateFrom();
		this.rateTo = exch.getRateTo();
		this.updatedAt = exch.getUpdatedAt();
	}

	@Id
	private String objid;
	private String currFrom;
	private String currTo;
	private Double rateFrom;
	private Double rateTo;
	private Date updatedAt;

	public String getCurrFrom() {
		return currFrom;
	}

	public void setCurrFrom(String currFrom) {
		this.currFrom = currFrom;
	}

	public String getCurrTo() {
		return currTo;
	}

	public void setCurrTo(String currTo) {
		this.currTo = currTo;
	}

	public Double getRateFrom() {
		return rateFrom;
	}

	public void setRateFrom(Double rateFrom) {
		this.rateFrom = rateFrom;
	}

	public Double getRateTo() {
		return rateTo;
	}

	public void setRateTo(Double rateTo) {
		this.rateTo = rateTo;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the objid
	 */
	public String getObjid() {
		return objid;
	}

	/**
	 * @param objid
	 *            the objid to set
	 */
	public void setObjid(String objid) {
		this.objid = objid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currFrom == null) ? 0 : currFrom.hashCode());
		result = prime * result + ((currTo == null) ? 0 : currTo.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((rateFrom == null) ? 0 : rateFrom.hashCode());
		result = prime * result + ((rateTo == null) ? 0 : rateTo.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Exchange)) {
			return false;
		}
		Exchange other = (Exchange) obj;
		if (currFrom == null) {
			if (other.currFrom != null) {
				return false;
			}
		} else if (!currFrom.equals(other.currFrom)) {
			return false;
		}
		if (currTo == null) {
			if (other.currTo != null) {
				return false;
			}
		} else if (!currTo.equals(other.currTo)) {
			return false;
		}
		if (objid == null) {
			if (other.objid != null) {
				return false;
			}
		} else if (!objid.equals(other.objid)) {
			return false;
		}
		if (rateFrom == null) {
			if (other.rateFrom != null) {
				return false;
			}
		} else if (!rateFrom.equals(other.rateFrom)) {
			return false;
		}
		if (rateTo == null) {
			if (other.rateTo != null) {
				return false;
			}
		} else if (!rateTo.equals(other.rateTo)) {
			return false;
		}
		if (updatedAt == null) {
			if (other.updatedAt != null) {
				return false;
			}
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exchange [objid=" + objid + ", currFrom=" + currFrom + ", currTo=" + currTo + ", rateFrom=" + rateFrom + ", rateTo="
				+ rateTo + ", updatedAt=" + updatedAt + "]";
	}

	public static Double inverse(double amount, int approx) {
		return (Math.round(1 / amount * Math.pow(10, approx))) / (double)(Math.pow(10, approx));
	}

}
