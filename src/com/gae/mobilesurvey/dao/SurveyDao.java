package com.gae.mobilesurvey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.mobilesurvey.model.Survey;

/**
 * �г���������ɾ���ʾ����
 * @author Frank
 *
 */
public enum SurveyDao {
	INSTANCE;
	
	/**
	 * �г����еĵ����ʾ�
	 * @return
	 */
	public List<Survey> listSurveys() {
		EntityManager em = EMFService.get().createEntityManager();
		//��ȡ���е�����
		Query query = em.createQuery("select m from Survey m");
		@SuppressWarnings("unchecked")
		List<Survey> surveys = query.getResultList();
		return  surveys;
	}
	
	/**
	 * ���������ʾ�
	 * @param title
	 * @param dateCreated
	 * @param narrative
	 */
	public void add(String title, String dateCreated, String narrative) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Survey surveys = new Survey(title, dateCreated, narrative);
			em.persist(surveys);
			em.close();
		}
	}
	
	/**
	 * �����ʾ����Id��ȡ��Ӧ���ʾ����
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Survey> getSurvey(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query query = em.createQuery("select s from Survey s where s.id = :id");
		query.setParameter("id", id);
		List<Survey> listSurveyById = query.getResultList();
		return listSurveyById;
	}
	
	/**
	 * �����ʾ�����Idɾ����Ӧ���ʾ����
	 * @param id
	 */
	public boolean remove(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			em.remove(em.find(Survey.class, id));
		} finally {
			em.close();
		}
		return true;
	}
	
	/**
	 * ���µ����ʾ�
	 * @param id ��ȡҪִ�и��²������ʾ����	
	 * @param newTitle 
	 * @param newDate
	 * @param newNarrative
	 * @return
	 */
	public boolean update(Long id, String newTitle, String newDate, String newNarrative) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		Survey survey = em.find(Survey.class, id);
		em.clear();
		survey.setTitle(newTitle);
		survey.setDateCreated(newDate);
		survey.setNarrative(newNarrative);
		em.merge(survey);
		em.getTransaction().commit();
		em.close();
		return true;
	}
}
