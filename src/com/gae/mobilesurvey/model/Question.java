package com.gae.mobilesurvey.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * �ʾ���Ŀ
 * @author Frank
 *
 */
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qId;
	
	/**
	 * �����ʾ��Id
	 */
	private Long surveyId;
	
	/**
	 * �ʾ���Ŀ���ͣ���Ϊ��ѡ����ѡ��������
	 */
	private Long qType;
	
	/**
	 * �������Ŀ
	 */
	private String qTitle;
	
	/**
	 * �����ѡ��
	 */
	private List<String> qOption;
	
	public Question(Long surveyId, String qTitle, List<String> qOption) {
		this.surveyId  = surveyId;
		this.qTitle = qTitle;
		this.qOption = qOption;
	}

	public Long getqId() {
		return qId;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public Long getqType() {
		return qType;
	}

	public void setqType(Long qType) {
		this.qType = qType;
	}
	
	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public List<String> getqOption() {
		return qOption;
	}

	public void setqOption(List<String> qOption) {
		this.qOption = qOption;
	}
}
