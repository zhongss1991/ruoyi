package com.ruoyi.web.controller.system;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MgSampleBatch;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IMgSampleBatchService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 样本导入记录Controller
 * 
 * @author zss
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/system/batch")
public class MgSampleBatchController extends BaseController
{
    private String prefix = "system/batch";

    @Autowired
    private IMgSampleBatchService mgSampleBatchService;

    @RequiresPermissions("system:batch:view")
    @GetMapping()
    public String batch()
    {
        return prefix + "/batch";
    }

    /**
     * 查询样本导入记录列表
     */
    @RequiresPermissions("system:batch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MgSampleBatch mgSampleBatch)
    {
        startPage();
        List<MgSampleBatch> list = mgSampleBatchService.selectMgSampleBatchList(mgSampleBatch);
        return getDataTable(list);
    }

    /**
     * 导出样本导入记录列表
     */
    @RequiresPermissions("system:batch:export")
    @Log(title = "样本导入记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MgSampleBatch mgSampleBatch)
    {
        List<MgSampleBatch> list = mgSampleBatchService.selectMgSampleBatchList(mgSampleBatch);
        ExcelUtil<MgSampleBatch> util = new ExcelUtil<MgSampleBatch>(MgSampleBatch.class);
        return util.exportExcel(list, "batch");
    }

    /**
     * 新增样本导入记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存样本导入记录
     */
    @RequiresPermissions("system:batch:add")
    @Log(title = "样本导入记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MgSampleBatch mgSampleBatch)
    {
        return toAjax(mgSampleBatchService.insertMgSampleBatch(mgSampleBatch));
    }

    /**
     * 修改样本导入记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MgSampleBatch mgSampleBatch = mgSampleBatchService.selectMgSampleBatchById(id);
        mmap.put("mgSampleBatch", mgSampleBatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存样本导入记录
     */
    @RequiresPermissions("system:batch:edit")
    @Log(title = "样本导入记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MgSampleBatch mgSampleBatch)
    {
        return toAjax(mgSampleBatchService.updateMgSampleBatch(mgSampleBatch));
    }

    /**
     * 删除样本导入记录
     */
    @RequiresPermissions("system:batch:remove")
    @Log(title = "样本导入记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mgSampleBatchService.deleteMgSampleBatchByIds(ids));
    }
    
    @Log(title="样本编号导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:batch:import")
    @PostMapping("importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
    	ExcelUtil<MgSampleBatch> util = new ExcelUtil<MgSampleBatch>(MgSampleBatch.class);
    	List<MgSampleBatch> samples = util.importExcel(file.getInputStream());
    	String operName = ShiroUtils.getSysUser().getLoginName();
        String message = mgSampleBatchService.importBatch(samples, true, operName);
        return AjaxResult.success(message);
    }
    
    /**
     * 导入模板下载
     * @return
     */
    @RequiresPermissions("system:batch:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<MgSampleBatch> util = new ExcelUtil<MgSampleBatch>(MgSampleBatch.class);
        return util.importTemplateExcel("样本导入");
    }
}
