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
import com.ruoyi.system.domain.ConType;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IConTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author zss
 * @date 2020-05-19
 */
@Controller
@RequestMapping("/csm/type")
public class ConTypeController extends BaseController
{
    private String prefix = "csm/type";

    @Autowired
    private IConTypeService conTypeService;

    @RequiresPermissions("system:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConType conType)
    {
        startPage();
        List<ConType> list = conTypeService.selectConTypeList(conType);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:type:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConType conType)
    {
        List<ConType> list = conTypeService.selectConTypeList(conType);
        ExcelUtil<ConType> util = new ExcelUtil<ConType>(ConType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:type:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConType conType)
    {
    	SysUser user = ShiroUtils.getSysUser();
    	conType.setUserid(user.getUserId());
        return toAjax(conTypeService.insertConType(conType));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConType conType = conTypeService.selectConTypeById(id);
        mmap.put("conType", conType);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConType conType)
    {
        return toAjax(conTypeService.updateConType(conType));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(conTypeService.deleteConTypeByIds(ids));
    }
    
    /**
     * 选择类型树
     * 
     * @param id 耗材类型ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectConTypeTree/{id}", "/selectConTypeTree/{id}/{excludeId}" })
    public String selectConTypeTree(@PathVariable("id") Long id,
            @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("conType", conTypeService.selectConTypeById(id));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    /**
     * 加载类型列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = conTypeService.selectConTypeTree(new ConType());
        return ztrees;
    }
}
