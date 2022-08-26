package com.exam.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.QuestionDao;
import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao qu_dao;

	@Override
	public Questions addQuestion(Questions question) {		
		return this.qu_dao.save(question);
	}

	@Override
	public Questions updateQuestion(Questions question) {
		return this.qu_dao.save(question);
	}

	@Override
	public Set<Questions> getQuestions() {
		
		return new HashSet<Questions>(this.qu_dao.findAll());
	}

	@Override
	public Questions getQuestionById(Long qid) {
		return this.qu_dao.findById(qid).get();
	}

	@Override
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
		return this.qu_dao.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quid) {
		this.qu_dao.deleteById(quid);
		
	}

}
