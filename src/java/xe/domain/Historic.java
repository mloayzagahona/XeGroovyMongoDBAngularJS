package xe.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("historics")
public class Historic {

	@Id
	private String objid;
	private String currFrom;
	private String currTo;
	private Double rateFrom;
	private Double rateTo;
	private Date updatedAt;
	private Date createdAt;
	private Date deletedAt;

	public Historic() {
	}

	public Historic(Exchange exc) {
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
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the deletedAt
	 */
	public Date getDeletedAt() {
		return deletedAt;
	}

	/**
	 * @param deletedAt the deletedAt to set
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((currFrom == null) ? 0 : currFrom.hashCode());
		result = prime * result + ((currTo == null) ? 0 : currTo.hashCode());
		result = prime * result + ((deletedAt == null) ? 0 : deletedAt.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((rateFrom == null) ? 0 : rateFrom.hashCode());
		result = prime * result + ((rateTo == null) ? 0 : rateTo.hashCode());
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
		if (!(obj instanceof Historic)) {
			return false;
		}
		Historic other = (Historic) obj;
		if (createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!createdAt.equals(other.createdAt)) {
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
		if (deletedAt == null) {
			if (other.deletedAt != null) {
				return false;
			}
		} else if (!deletedAt.equals(other.deletedAt)) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Historic [objid=" + objid + ", currFrom=" + currFrom + ", currTo=" + currTo + ", rateFrom=" + rateFrom + ", rateTo="
				+ rateTo + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + "]";
	}

}
