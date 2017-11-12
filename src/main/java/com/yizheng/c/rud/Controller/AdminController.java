package com.yizheng.c.rud.Controller;

import com.yizheng.c.rud.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admins")
	public String Users(Model model) {
		model.addAttribute(new Menu("/users"));
		return "admin";
	}
}
