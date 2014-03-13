package xe.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("conversions")
public class Conversion {
	@Id
	private String objid;
	private String currFrom;
	private String currTo;
	private Double rateFrom;
	private Double rateTo;
	private Date updatedAt;
	private Double amount;
	private Double rateUsed;
	
	public Conversion() {
	}

	public Conversion(Exchange exc) {
		super();
		this.currFrom = exc.getCurrFrom();
		this.currTo = exc.getCurrTo();
		this.rateFrom = exc.getRateFrom();
		this.rateTo = exc.getRateTo();
	}

	/**
	 * @return the objid
	 */
	public String getObjid() {
		return objid;
	}

	/**
	 * @param objid the objid to set
	 */
	public void setObjid(String objid) {
		this.objid = objid;
	}

	/**
	 * @return the currFrom
	 */
	public String getCurrFrom() {
		return currFrom;
	}

	/**
	 * @param currFrom the currFrom to set
	 */
	public void setCurrFrom(String currFrom) {
		this.currFrom = currFrom;
	}

	/**
	 * @return the currTo
	 */
	public String getCurrTo() {
		return currTo;
	}

	/**
	 * @param currTo the currTo to set
	 */
	public void setCurrTo(String currTo) {
		this.currTo = currTo;
	}

	/**
	 * @return the rateFrom
	 */
	public Double getRateFrom() {
		return rateFrom;
	}

	/**
	 * @param rateFrom the rateFrom to set
	 */
	public void setRateFrom(Double rateFrom) {
		this.rateFrom = rateFrom;
	}

	/**
	 * @return the rateTo
	 */
	public Double getRateTo() {
		return rateTo;
	}

	/**
	 * @param rateTo the rateTo to set
	 */
	public void setRateTo(Double rateTo) {
		this.rateTo = rateTo;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the rateUsed
	 */
	public Double getRateUsed() {
		return rateUsed;
	}

	/**
	 * @param rateUsed the rateUsed to set
	 */
	public void setRateUsed(Double rateUsed) {
		this.rateUsed = rateUsed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currFrom == null) ? 0 : currFrom.hashCode());
		result = prime * result + ((currTo == null) ? 0 : currTo.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((rateFrom == null) ? 0 : rateFrom.hashCode());
		result = prime * result + ((rateTo == null) ? 0 : rateTo.hashCode());
		result = prime * result + ((rateUsed == null) ? 0 : rateUsed.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		if (!(obj instanceof Conversion)) {
			return false;
		}
		Conversion other = (Conversion) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
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
		if (rateUsed == null) {
			if (other.rateUsed != null) {
				return false;
			}
		} else if (!rateUsed.equals(other.rateUsed)) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Conversion [objid=" + objid + ", currFrom=" + currFrom + ", currTo=" + currTo + ", rateFrom=" + rateFrom + ", rateTo="
				+ rateTo + ", updatedAt=" + updatedAt + ", amount=" + amount + ", rateUsed=" + rateUsed + "]";
	}
}
