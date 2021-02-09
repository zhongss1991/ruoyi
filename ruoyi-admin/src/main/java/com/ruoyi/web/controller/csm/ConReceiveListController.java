package com.ruoyi.web.controller.csm;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.ConReceiveList;
import com.ruoyi.system.domain.ConType;
import com.ruoyi.system.service.IConReceiveListService;
import com.ruoyi.system.service.IConTypeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 耗材领用Controller
 * 
 * @author zss
 * @date 2020-05-25
 */
@Controller
@RequestMapping("/csm/receiveList")
public class ConReceiveListController extends BaseController
{
    private String prefix = "csm/receiveList";

    @Autowired
    private IConReceiveListService conReceiveListService;
    
    @Autowired
    private IConTypeService conTypeService;

    @RequiresPermissions("csm:receiveList:view")
    @GetMapping()
    public String receiveList()
    {
        return prefix + "/receiveList";
    }

    /**
     * 耗材采购
     */
    @RequiresPermissions("csm:receiveList:purchase")
    @GetMapping("/purchase")
    public String purchase()
    {
    	return "csm/receiveList/purchase";
    }
    
    //手机端耗材领用
  	@GetMapping(value = "receiveCos")
  	public String receiveCos(Model model, ConType conType) {
  		
  		List<ConType> conTypes = conTypeService.selectConTypeList(conType);
		model.addAttribute("conTypes", conTypes);
		
  		return "csm/receiveList/M_receiveCons";
  	}
  	
  	//领用成功
  	@RequestMapping(value="receSuccess", method = RequestMethod.GET)
  	public String receSuccess() {
  		
  		return prefix + "/M_success";
  		
  	} 
  	//领用失败
  	@RequestMapping(value="receFailed", method = RequestMethod.GET)
  	public String receFailed() {
  		
  		return prefix + "/M_failed";
  		
  	}
    
    /**
     * 查询耗材领用列表
     */
    @RequiresPermissions("csm:receiveList:list")
    @PostMapping("/list/{opetype}")
    @ResponseBody
    public TableDataInfo list(ConReceiveList conReceiveList, @PathVariable("opetype") long opetype)
    {
        startPage();
        /** 0 领用*/
        conReceiveList.setOpetype(opetype);
        List<ConReceiveList> list = conReceiveListService.selectConReceiveListList(conReceiveList);
        return getDataTable(list);
    }

    /**
     * 导出耗材领用列表
     */
    @RequiresPermissions("csm:receiveList:export")
    @Log(title = "耗材领用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConReceiveList conReceiveList)
    {
        List<ConReceiveList> list = conReceiveListService.selectConReceiveListList(conReceiveList);
        ExcelUtil<ConReceiveList> util = new ExcelUtil<ConReceiveList>(ConReceiveList.class);
        return util.exportExcel(list, "receiveList");
    }

    /**
     * 新增耗材领用
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存耗材领用
     */
//    @RequiresPermissions("csm:receiveList:add")
    @Log(title = "耗材领用", businessType = BusinessType.INSERT)
    @PostMapping("/add/{opetype}")
    @ResponseBody
    public AjaxResult addSave(ConReceiveList conReceiveList, @PathVariable("opetype") long opetype)
    {
    	System.out.println("领用人:" + conReceiveList.getReceiveuser());
    	conReceiveList.setOpetype(opetype);
    	if(opetype == 1) {
    		String userName = ShiroUtils.getSysUser().getUserName();
    		conReceiveList.setReceiveuser(userName);
    	}
        return toAjax(conReceiveListService.insertConReceiveList(conReceiveList));
    }

    /**
     * 修改耗材领用
     */
    @GetMapping("/edit/{conid}")
    public String edit(@PathVariable("conid") Long conid, ModelMap mmap)
    {
        ConReceiveList conReceiveList = conReceiveListService.selectConReceiveListById(conid);
        mmap.put("conReceiveList", conReceiveList);
        return prefix + "/edit";
    }

    /**
     * 修改保存耗材领用
     */
    @RequiresPermissions("csm:receiveList:edit")
    @Log(title = "耗材领用", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConReceiveList conReceiveList)
    {
        return toAjax(conReceiveListService.updateConReceiveList(conReceiveList));
    }

    /**
     * 删除耗材领用
     */
    @RequiresPermissions("csm:receiveList:remove")
    @Log(title = "耗材领用", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	conTypeService.updateConTypeNum(ids);
        return toAjax(conReceiveListService.deleteConReceiveListByIds(ids));
    }
}
