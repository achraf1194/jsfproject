package tn.esprit.PiPub.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Advertisement {
	@Override
	public String toString() {
		return "Advertisement [IdAdvertisement=" + IdAdvertisement + ", Description=" + Description + ", Nom=" + Nom
				+ ", IsApproved=" + IsApproved + ", Date=" + Date + ", likes=" + likes + ", picture=" + picture
				+ ", DateFin=" + DateFin + ", user=" + user + ", likeAdvs=" + likeAdvs + "]";
	}





	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdAdvertisement;
	private String Description;
	private String Nom;
	private Boolean IsApproved;
	private Date Date;
	private int likes;
	private String picture;
	private Date DateFin;
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;
	
	@OneToMany(targetEntity=LikeAdv.class,mappedBy="advertisement",fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private List<LikeAdv> likeAdvs;
	
	
	
	
	
	
	public Advertisement() {
		super();
	}
	
	
	


	public Advertisement(String description, String nom, Boolean isApproved, Date date,
			String picture,Date DateFin,User user) {
		super();
		Description = description;
		Nom = nom;
		IsApproved = isApproved;
		Date = date;
	
		this.picture = picture;

		this.DateFin = DateFin;
		this.user=user;
	}

	


	public Advertisement(int idAdvertisement, String description, String nom, Boolean isApproved, Date date,
			 String picture,User user) {
		super();
		IdAdvertisement = idAdvertisement;
		Description = description;
		Nom = nom;
		IsApproved = isApproved;
		Date = date;
		this.user=user;
	
		this.picture = picture;
	}







	public Advertisement(int idAdvertisement, String description, String nom, Boolean isApproved, Date date,
			 String picture) {
		super();
		IdAdvertisement = idAdvertisement;
		Description = description;
		Nom = nom;
		IsApproved = isApproved;
		Date = date;
	
		this.picture = picture;
	}





	public Advertisement(String description, String nom, Boolean isApproved, Date date) {
		super();
		Description = description;
		Nom = nom;
		IsApproved = isApproved;
		Date = date;
	}




	public Advertisement(int idAdvertisement, String description, String nom, Boolean isApproved, Date date) {
		super();
		IdAdvertisement = idAdvertisement;
		Description = description;
		Nom = nom;
		IsApproved = isApproved;
		Date = date;
	}


	public int getIdAdvertisement() {
		return IdAdvertisement;
	}
	public void setIdAdvertisement(int idAdvertisement) {
		IdAdvertisement = idAdvertisement;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public Boolean getIsApproved() {
		return IsApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		IsApproved = isApproved;
	}


	public Date getDate() {
		return Date;
	}


	public void setDate(Date date) {
		Date = date;
	}



	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}





	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}





	public Date getDateFin() {
		return DateFin;
	}





	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}





	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
