package com.exam.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer>{

	Set<Questions> findByQuiz(Quiz quiz);//the findBy should be as it is but the quiz variable should match the variable declared at Questions pojo as a camelcase
	//custom finder method

	Questions findByQuesId(int quid);
	

}
