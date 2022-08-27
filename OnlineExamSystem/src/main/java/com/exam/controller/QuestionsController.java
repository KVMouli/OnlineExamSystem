package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/questions")
public class QuestionsController {

	@Autowired
	private QuestionService quService;//this will automatically make the object of QuestionServiceImpl
	@Autowired
	private QuizService qService;//for loading the questions we need this

	@PostMapping("/")
	public Questions addQuestions(@RequestBody Questions question) {
		//Quiz quiz =new Quiz();
		//quiz.setQid(question.getQuiz().getQid());
		return this.quService.addQuestion(question);
	}

	@PutMapping("/")
	public Questions updateQuestion(@RequestBody Questions question) {
		return this.quService.updateQuestion(question);
	}
//	@GetMapping("/")
//	public Set<Questions> getQuestions(){
//		return new HashSet<Questions>(this.quService.getQuestions());
//	} No need of this why to take out all question the question should come out as per quiz
	
	//get single question
	@GetMapping("/{quesId}")//quid can be said as URI variable
	public Questions getQuestionById(@PathVariable("quesId") int quesId){
		return this.quService.getQuestionById(quesId);
		
	}

	//FOR STUDENT 
	//BOTH ARE SAME ("/quiz/all/{qid}")
	// get all question of any respective quiz
	@GetMapping("/quiz/{qid}")//this is quiz id not ques id DONE
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") int qid){
		/*Quiz quiz= new Quiz();This quiz only has id it has no data
		quiz.setQid(qid);
		return this.quService.getQuestionsOfQuiz(quiz);
		This was the method of taking out all the questions now how to sublist the questions by using number of questions field*/
		
		Quiz quiz=this.qService.getQuizById(qid);//to get the maximum no of question in quizzes or to load the quiz details thus used QuizService qService
		Set<Questions> quSet=quiz.getQuestions();//all the set of that particular quiz
		List qulist=new ArrayList(quSet);//In order to subList we changed it from set to arrayList set does not support subset
		if(qulist.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
			qulist=qulist.subList(0,Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(qulist);
		return ResponseEntity.ok(qulist);
		
	}
	@DeleteMapping("/{quid}")
	public void delete(@PathVariable("quid") int quid) {
		this.quService.deleteQuestion(quid);
	}
	
	//FOR ADMIN
	// get all question of any respective quiz by id
    @GetMapping("/quiz/all/{qid}")//qid is quiz id
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") int qid) {
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Questions> questionsOfQuiz = this.quService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);

    }
}


