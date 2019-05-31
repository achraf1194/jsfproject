package tn.esprit.PiPub.persistence;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: LikeAdv
 *
 */
@Entity
public class LikeAdv implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LikeAdvId id;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="userId",insertable=false,updatable=false)
	private User user;
	@ManyToOne(targetEntity = Advertisement.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="advertisementId",insertable=false,updatable=false)
	private Advertisement advertisement;

	public LikeAdv() {
		super();
	}

	public LikeAdvId getId() {
		return id;
	}

	public void setId(LikeAdvId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public LikeAdv(User user, Advertisement advertisement) {
		super();
		this.id = new LikeAdvId(user.getId(), advertisement.getIdAdvertisement());
		this.user = user;
		this.advertisement = advertisement;
	}
	
	

}
