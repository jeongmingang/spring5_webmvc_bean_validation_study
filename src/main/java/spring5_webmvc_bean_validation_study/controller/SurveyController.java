package spring5_webmvc_bean_validation_study.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring5_webmvc_bean_validation_study.survey.AnsweredData;
import spring5_webmvc_bean_validation_study.survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {
//	@GetMapping
//	public String form(Model model) {
//		List<Question> questions = createQuestion();
//		model.addAttribute("questions", questions);
//		return "survey/surveyForm";
//	}
	// ModelAndView는 모델과 뷰 이름을 함께 제공하여 한번에 처리 가능
	@GetMapping
	public ModelAndView form() {
		List<Question> questions = createQuestion();
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions", questions);
		mav.setViewName("survey/surveyForm");
		return mav;
	}

	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
	
	
	private List<Question> createQuestion() {
		Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("서버", "프론트", "풀스텍"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?", Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요");
		return Arrays.asList(q1, q2, q3);
	}
}
