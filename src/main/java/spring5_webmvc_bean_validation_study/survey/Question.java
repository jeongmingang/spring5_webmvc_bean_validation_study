package spring5_webmvc_bean_validation_study.survey;

import java.util.Collections;
import java.util.List;

public class Question {
	private String title;		// 질문제목
	private List<String> options;	// 답변보기 옵션
	
	// 객관식
	public Question(String title, List<String> options) {	// 질문제목, 답변보기
		this.title = title;	
		this.options = options;
	}

	// 주관식 
	public Question(String title) {		// 질문 제목
		this(title, Collections.<String>emptyList());
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}

	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
