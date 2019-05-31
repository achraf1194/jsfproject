package tn.esprit.PiPub.util;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.PiPub.persistence.Advertisement;
import tn.esprit.PiPub.persistence.User;

@Remote
public interface AdvertisementServiceRemote {

	public int ajouterAdvertisement(Advertisement advertisement) ;
	public List<Advertisement> findAll();
	public void deleteAdvertisementById(int advertisementId);
	public void updateAdvertisement(Advertisement advertisement);
	public List<Advertisement> findAllByLikes();
	public void validerAdvertisementlikes(int id);
	//public List<Advertisement> getByName(String name);
	User login(String login, String password);
	List<Advertisement> findAllByUser(User user);
	
	
		
	
	
	
	
	
}
