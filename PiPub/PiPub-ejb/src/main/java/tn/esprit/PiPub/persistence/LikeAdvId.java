package tn.esprit.PiPub.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: LikeAdvId
 *
 */

@Embeddable
public class LikeAdvId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private String userId;
	private Integer advertisementId;
	
	

	public LikeAdvId(String userId, Integer advertisementId) {
		super();
		this.userId = userId;
		this.advertisementId = advertisementId;
	}



	public LikeAdvId() {
		super();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advertisementId == null) ? 0 : advertisementId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeAdvId other = (LikeAdvId) obj;
		if (advertisementId == null) {
			if (other.advertisementId != null)
				return false;
		} else if (!advertisementId.equals(other.advertisementId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
   
	
	
	
	
	
}
