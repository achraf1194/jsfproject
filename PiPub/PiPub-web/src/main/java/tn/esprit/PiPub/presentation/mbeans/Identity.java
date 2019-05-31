package tn.esprit.PiPub.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.PiPub.persistence.User;
import tn.esprit.PiPub.util.AdvertisementServiceRemote;

@ManagedBean
@SessionScoped
public class Identity {
	// injection of the dependency
	@EJB
	private AdvertisementServiceRemote advertisementServiceRemote;
	// the model
	private User user = new User();
	private boolean loggedInAsAgent = false;
	private boolean loggedInAsMember = false;
	public String test="false";
	public String testSimpleUser="false";

	public String doLogin() {
		String navigateTo = "/horror?faces-redirect=true";
		User userLoggedIn = advertisementServiceRemote.login(user.getEmail(), user.getPasswordHash());
		if (userLoggedIn != null) {
			user = userLoggedIn;
			System.out.println(user.getEmail());
			if(user.getRole().equals("Admin")){
				test="true";
				testSimpleUser="false";
				navigateTo = "/PagesAdv/AdvertisementAdmin?faces-redirect=true";
			}else if (user.getRole().equals("SimpleUser")) {
				test="false";	
				testSimpleUser="true";
				navigateTo = "/PagesAdv/SimpleUser?faces-redirect=true";
			
		
			}
			
			
			
			
		}
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedInAsAgent() {
		return loggedInAsAgent;
	}

	public void setLoggedInAsAgent(boolean loggedInAsAgent) {
		this.loggedInAsAgent = loggedInAsAgent;
	}

	public boolean isLoggedInAsMember() {
		return loggedInAsMember;
	}

	public void setLoggedInAsMember(boolean loggedInAsMember) {
		this.loggedInAsMember = loggedInAsMember;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getTestSimpleUser() {
		return testSimpleUser;
	}

	public void setTestSimpleUser(String testSimpleUser) {
		this.testSimpleUser = testSimpleUser;
	}
	
	
	
	
}
