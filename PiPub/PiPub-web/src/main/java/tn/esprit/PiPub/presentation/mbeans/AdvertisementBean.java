package tn.esprit.PiPub.presentation.mbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import tn.esprit.PiPub.persistence.Advertisement;
import tn.esprit.PiPub.services.AdvertisementService;

@LocalBean
@ManagedBean
@ViewScoped
public class AdvertisementBean {

	private int IdAdvertisement;
	private static String Description;
	private static String Nom;
	private static Boolean IsApproved;
	private static Date Date;
	private static String picture;
	private static Date DateFin;
	@ManagedProperty(value = "#{identity}")
	private Identity identity;
	public boolean testModeration=false;
	
	

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

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@EJB
	AdvertisementService advertisementService;
	private List<Advertisement> advertisements;
	private Advertisement a;
	private int advertisementIdToBeUpdate;

	public AdvertisementService getAdvertisementService() {
		return advertisementService;
	}

	public void setAdvertisementService(AdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public String addAdvertisement() {
		Date d = new Date();
		Advertisement advertisement = new Advertisement();
		advertisement.setDescription(Description);
		advertisement.setNom(Nom);
		advertisement.setIsApproved(IsApproved);
		advertisement.setDate(d);
		advertisement.setPicture(name);
		advertisement.setDateFin(DateFin);
		advertisement.setUser(identity.getUser());
		System.out.println("tt " + identity.getUser().getId());
		System.out.println("ad " + advertisement.getUser().getId());
		advertisementService.ajouterAdvertisement(advertisement);
		return "/PagesAdv/SimpleUser?faces-redirect=true";
	}

	public List<Advertisement> getAdvertisements() {
		advertisements = advertisementService.findAllByLikes();
		// advertisements = advertisementService.findAll();
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public Advertisement getA() {
		return a;
	}

	public void setA(Advertisement a) {
		this.a = a;
	}

	public List<Advertisement> dofindAllByUser() {
		List<Advertisement> advertisementsbyuser = advertisementService.findAllByUser(identity.getUser());
			
		advertisementsbyuser.forEach(System.out::println);
		
		
	System.out.println("AdvertisementBean.dofindAllByUser() "+advertisementsbyuser.size());

		return advertisementsbyuser;
	}

	public String modifier(Advertisement advertisement) {
		this.setDescription(advertisement.getDescription());
		this.setNom(advertisement.getNom());
		this.setDate(advertisement.getDate());
		this.setAdvertisementIdToBeUpdate(advertisement.getIdAdvertisement());
		this.setPicture(name);
		advertisement.setUser(identity.getUser());

		return "/PagesAdv/Modif?faces-redirect=true";

	}

	public List<Advertisement> notApprovedAds() {
		List<Advertisement> list = new ArrayList<Advertisement>();
		for (Advertisement advertisement : advertisementService.findAll()) {
			if (advertisement.getIsApproved().equals(Boolean.FALSE))
				list.add(advertisement);
			
		
		}
		return list;
	}
	
	

	public void supprimer(int IdAdvertisement) {
		advertisementService.deleteAdvertisementById(IdAdvertisement);
	}

	public String mettreAjourAdvertisement() {
		advertisementService.updateAdvertisement(new Advertisement(advertisementIdToBeUpdate, Description, Nom,
				IsApproved, Date, name, identity.getUser()));
		return "/PagesAdv/SimpleUser?faces-redirect=true";

	}

	public int getAdvertisementIdToBeUpdate() {
		return advertisementIdToBeUpdate;
	}

	public void setAdvertisementIdToBeUpdate(int advertisementIdToBeUpdate) {
		this.advertisementIdToBeUpdate = advertisementIdToBeUpdate;
	}

	public void validerlikes(int id) {
		advertisementService.validerAdvertisementlikes(id);
		
	}

	public void Approuver(Advertisement a) {
		a.setIsApproved(Boolean.TRUE);
		advertisementService.updateAdvertisement(a);

	}
	// redirections

	public String GoToHome() {

		return "/PagesAdv/Advertisement?faces-redirect=true";
	}

	public String GoToSimpleUser() {

		return "/PagesAdv/SimpleUser?faces-redirect=true";
	}

	public String GoSimpleUserAjout() {

		return "/PagesAdv/SimpleUserAjout?faces-redirect=true";
	}
	
	public String GoToNotApproved(){
		return "/PagesAdv/NotApproved?faces-redirect=true";
	
	}

	
	public String GoToHomeAdmin() {

		return "/PagesAdv/AdvertisementAdmin?faces-redirect=true";
	}
	/*
	 * public List<Advertisement> rechercherAdvertisementparname(String name){
	 * 
	 * List<Advertisement>a=advertisementService.getByName(name);
	 * 
	 * return a; }
	 */

	public void handleFileUpload(FileUploadEvent event) {
		name = event.getFile().getFileName();
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		String localPath = "C:" + File.separator + "Users" + File.separator + "Charfoun" + File.separator + "Documents"
				+ File.separator + "pi" + File.separator + "PiJEE" + File.separator + "PiPub" + File.separator
				+ "PiPub-web" + File.separator + "src" + File.separator + "main" + File.separator + "webapp"
				+ File.separator + "PagesAdv" + File.separator + name;

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String filepath = externalContext.getRealPath("") + File.separator + "PagesAdv" + File.separator + name;

		try (

				InputStream input = event.getFile().getInputstream()) {
			OutputStream os = new FileOutputStream(localPath);
			OutputStream osServer = new FileOutputStream(localPath);
			byte[] b = new byte[2048];
			int length;

			while ((length = input.read(b)) != -1) {
				os.write(b, 0, length);
				osServer.write(b, 0, length);
			}

			input.close();
			os.close();
			osServer.close();

		} catch (IOException e) {
			// Show faces message?
		}

	}

	String name;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	private UploadedFile file;

	public void doAddAdvertisement() {
		System.out.println("*****" + a.getPicture());
		a.setPicture(name);

		advertisementService.ajouterAdvertisement(a);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "the adv : " + a.getNom() + " is successfuly added "));

		// return "Advertisement?faces-redirect=true";

	}

}
