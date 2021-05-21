package spring5_webmvc_bean_validation_study.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring5_webmvc_bean_validation_study.spring.DuplicateMemberException;
import spring5_webmvc_bean_validation_study.spring.MemberRegisterService;
import spring5_webmvc_bean_validation_study.spring.RegisterRequest;

@Controller
public class RegisterController {
	@Autowired
	private MemberRegisterService memberRegisterService;

	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "/register/step1";
	}
	
	@PostMapping("/register/step2")		// 커맨드 객체를 파라미터로 추가
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, RegisterRequest registerRequest) {
		if (!agree) {
			return "/register/step1";
		}
//		model.addAttribute("registerRequest", new RegisterRequest());
		return "/register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/register/step3")
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {	// Errors 타입 파라미터는 반드시 커맨드 객체를 위한 파라미터 다음에 위치해야 함
		System.out.println(regReq);

		if (errors.hasErrors())
			return "register/step2";
		
		if (!regReq.isPasswordEqualToConfirmPassword()) {
			errors.rejectValue("confirmPassword", "nomatch");
			return "register/step2";
		}
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
}
