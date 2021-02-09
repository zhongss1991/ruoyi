package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.MgTecanSample;
import com.ruoyi.system.domain.MgTecanSampleError;
import com.ruoyi.system.service.IMgTecanSampleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * tecan样本表Controller
 * 
 * @author zss
 * @date 2020-07-28
 */
@Controller
@RequestMapping("/system/tecanSample")
public class MgTecanSampleController extends BaseController
{
    private String prefix = "system/tecanSample";

    @Autowired
    private IMgTecanSampleService mgTecanSampleService;

    @RequiresPermissions("system:tecanSample:errorSample")
    @GetMapping("/errorSample")
    public String errorSample()
    {
    	return prefix + "/errorSample";
    }
    
    /**
     * 查询tecan错误样本表列表
     */
    @RequiresPermissions("system:tecanSample:errorList")
    @PostMapping("/errorList")
    @ResponseBody
    public TableDataInfo errorList(MgTecanSampleError sampleError)
    {
        startPage();
        List<MgTecanSampleError> list = mgTecanSampleService.selectError(sampleError);
        return getDataTable(list);
    }
    
    @RequiresPermissions("system:tecanSample:view")
    @GetMapping()
    public String tecanSample()
    {
        return prefix + "/tecanSample";
    }

    /**
     * 查询tecan样本表列表
     */
    @RequiresPermissions("system:tecanSample:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MgTecanSample mgTecanSample)
    {
        startPage();
        List<MgTecanSample> list = mgTecanSampleService.selectMgTecanSampleList(mgTecanSample);
        return getDataTable(list);
    }

    /**
     * 导出tecan样本表列表
     */
    @RequiresPermissions("system:tecanSample:export")
    @Log(title = "tecan样本表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MgTecanSample mgTecanSample)
    {
        List<MgTecanSample> list = mgTecanSampleService.selectMgTecanSampleList(mgTecanSample);
        ExcelUtil<MgTecanSample> util = new ExcelUtil<MgTecanSample>(MgTecanSample.class);
        return util.exportExcel(list, "tecanSample");
    }

    /**
     * 新增tecan样本表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存tecan样本表
     */
    @RequiresPermissions("system:tecanSample:add")
    @Log(title = "tecan样本表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MgTecanSample mgTecanSample)
    {
        return toAjax(mgTecanSampleService.insertMgTecanSample(mgTecanSample));
    }

    /**
     * 修改tecan样本表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MgTecanSample mgTecanSample = mgTecanSampleService.selectMgTecanSampleById(id);
        mmap.put("mgTecanSample", mgTecanSample);
        return prefix + "/edit";
    }

    /**
     * 修改保存tecan样本表
     */
    @RequiresPermissions("system:tecanSample:edit")
    @Log(title = "tecan样本表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MgTecanSample mgTecanSample)
    {
        return toAjax(mgTecanSampleService.updateMgTecanSample(mgTecanSample));
    }

    /**
     * 删除tecan样本表
     */
    @RequiresPermissions("system:tecanSample:remove")
    @Log(title = "tecan样本表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mgTecanSampleService.deleteMgTecanSampleByIds(ids));
    }
}
