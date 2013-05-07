package com.gae.mobilesurvey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.gae.mobilesurvey.model.Answer;

public enum AnswerDao {
	INSTANCE;
	
	/**
	 * �г���
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Answer> listAnswers() {
		EntityManager em = EMFService.get().createEntityManager();
		Query query = em.createQuery("select m from Answer m");
		
		return query.getResultList();
	}
	
	/**
	 * ����ʾ�������
	 * @param answerNum
	 */
	public void addAnswerNum(Long questionId, List<Integer> answerNum) {
		EntityManager em = EMFService.get().createEntityManager();
		Answer answer = new Answer(questionId, answerNum);
		em.persist(answer);
		em.close();
	}
	
	public void updateAnswerNum(Long id, List<Integer> newAnswerNum) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		Answer answer = em.find(Answer.class, id);
		em.clear();
		answer.setAnswerNum(newAnswerNum);
		em.merge(answer);
		em.getTransaction().commit();
		em.close();
	}
}
