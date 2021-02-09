package com.ruoyi.web.controller.csm;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ConPurchaseList;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IConPurchaseListService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * purchaseController
 * 
 * @author zss
 * @date 2020-06-05
 */
@Controller
@RequestMapping("/csm/purchaseList")
public class ConPurchaseListController extends BaseController
{
    private String prefix = "csm/purchaseList";

    @Autowired
    private IConPurchaseListService conPurchaseListService;

    @RequiresPermissions("csm:purchaseList:view")
    @GetMapping()
    public String purchaseList()
    {
        return prefix + "/purchaseList";
    }

    /**
     * 查询purchase列表
     */
    @RequiresPermissions("csm:purchaseList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConPurchaseList conPurchaseList)
    {
        startPage();
        List<ConPurchaseList> list = conPurchaseListService.selectConPurchaseListList(conPurchaseList);
        return getDataTable(list);
    }

    /**
     * 导出purchase列表
     */
    @RequiresPermissions("csm:purchaseList:export")
    @Log(title = "purchase", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConPurchaseList conPurchaseList)
    {
        List<ConPurchaseList> list = conPurchaseListService.selectConPurchaseListList(conPurchaseList);
        ExcelUtil<ConPurchaseList> util = new ExcelUtil<ConPurchaseList>(ConPurchaseList.class);
        return util.exportExcel(list, "purchaseList");
    }

    /**
     * 新增purchase
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存purchase
     */
    @RequiresPermissions("csm:purchaseList:add")
    @Log(title = "purchase", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConPurchaseList conPurchaseList)
    {
    	SysUser currentUser = ShiroUtils.getSysUser();
    	conPurchaseList.setUserId(currentUser.getUserId());
        return toAjax(conPurchaseListService.insertConPurchaseList(conPurchaseList));
    }

    /**
     * 修改purchase
     */
    @GetMapping("/edit/{purchaseId}")
    public String edit(@PathVariable("purchaseId") Long purchaseId, ModelMap mmap)
    {
        ConPurchaseList conPurchaseList = conPurchaseListService.selectConPurchaseListById(purchaseId);
        mmap.put("conPurchaseList", conPurchaseList);
        return prefix + "/edit";
    }

    /**
     * 修改保存purchase
     */
    @RequiresPermissions("csm:purchaseList:edit")
    @Log(title = "purchase", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConPurchaseList conPurchaseList)
    {
        return toAjax(conPurchaseListService.updateConPurchaseList(conPurchaseList));
    }

    /**
     * 删除purchase
     */
    @RequiresPermissions("csm:purchaseList:remove")
    @Log(title = "purchase", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(conPurchaseListService.deleteConPurchaseListByIds(ids));
    }
}
