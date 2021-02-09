package com.ruoyi.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest/faq")
public class SysFaqController {

	private String prefix = "system/faq";
	
//	@RequiresPermissions("system:faq:view")
//	@RequiresGuest
	@GetMapping("")
	public String faq() {
		
		return prefix + "/faq";
		
	}
}
