package tn.esprit.PiPub.services;

import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Message;
import javax.jms.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PiPub.persistence.Advertisement;
import tn.esprit.PiPub.persistence.User;
import tn.esprit.PiPub.util.AdvertisementServiceRemote;








@Stateless
@LocalBean
public class AdvertisementService implements AdvertisementServiceRemote {

	@PersistenceContext(unitName = "PiPub-ejb")
	EntityManager em;

	public int ajouterAdvertisement(Advertisement advertisement) {
		// TODO Auto-generated method stub
		advertisement.setIsApproved(Boolean.FALSE);
		em.persist(advertisement);
		
		return advertisement.getIdAdvertisement();
	}

	public List<Advertisement> findAll() {
		TypedQuery<Advertisement> query = em.createQuery("Select a from Advertisement a ", Advertisement.class);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void deleteAdvertisementById(int advertisementId) {
		Advertisement advertisement = em.find(Advertisement.class, advertisementId);

		em.remove(advertisement);
	}

	public void updateAdvertisement(Advertisement advertisement) {
		em.merge(advertisement);
	}

	public List<Advertisement> findAllByLikes() {
		TypedQuery<Advertisement> query = em.createQuery("SELECT a FROM Advertisement a ORDER BY likes DESC",
				Advertisement.class);
		return query.getResultList();
	}

	public void validerAdvertisementlikes(int id) {
		Advertisement advertisement = em.find(Advertisement.class, id);
		int a = advertisement.getLikes() + 1;
		advertisement.setLikes(a);
	}

	@Override
	public User login(String login, String password) {
		User user = null;
		Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:param1 AND u.passwordHash=:param2");
		query.setParameter("param1", login);
		query.setParameter("param2", password);
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			System.err.println("user not found");
		}
		return user;
	}

	@Override
	public List<Advertisement> findAllByUser(User user) {
		String jpql = "select a from Advertisement a where a.user=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", user);
		return query.getResultList();
	}

	/*
	private boolean sendEmail(String email) {
		String host = "smtp.gmail.com";
		String user = "achraf.maalaoui@gmail.com";
		String pass = "Hajer2017@@@";
		String to = email;
		String from = "achraf.maalaoui@gmail.com";
		String subject = "New task";
		String url ="";
		String messageText = "A new task has been assigned to you, check it out ! <br/> ";
		boolean sessionDebug = false;
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.required", "true");

		// java.security.Security.addProvider(new
		// com.sun.net.ssl.internal.ssl.Provider());
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);
		Message msg = new MimeMessage(mailSession);
		try {
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// msg.setText(messageText);
			msg.setContent(messageText, "text/html; charset=utf-8");
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}*/
	/*
	 * public List<Advertisement> getByName(String name) {
	 * 
	 * TypedQuery<Advertisement> query=em.createQuery(
	 * "select a from Advertisement a where a.nom LIKE:a",Advertisement.class);
	 * query.setParameter("n", "%" + name + "%");
	 * 
	 * return query.getResultList();
	 * 
	 * 
	 * }
	 */

}
