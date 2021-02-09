package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ConTypeMapper;
import com.ruoyi.system.domain.ConType;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.IConTypeService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author zss
 * @date 2020-05-19
 */
@Service
public class ConTypeServiceImpl implements IConTypeService 
{
    @Autowired
    private ConTypeMapper conTypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public ConType selectConTypeById(Long id)
    {
        return conTypeMapper.selectConTypeById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param conType 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ConType> selectConTypeList(ConType conType)
    {
        return conTypeMapper.selectConTypeList(conType);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param conType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertConType(ConType conType)
    {
        return conTypeMapper.insertConType(conType);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param conType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateConType(ConType conType)
    {
        return conTypeMapper.updateConType(conType);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConTypeByIds(String ids)
    {
        return conTypeMapper.deleteConTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteConTypeById(Long id)
    {
        return conTypeMapper.deleteConTypeById(id);
    }
    
    /**
     * 
     */
	@Override
	@DataScope(deptAlias = "d")
	public List<Ztree> selectConTypeTree(ConType conType) {
		
		List<ConType> conTypeList = conTypeMapper.selectConTypeList(conType);
		List<Ztree> ztrees = initZtree(conTypeList);
		return ztrees;
	}

	  /**
     * 对象转类型树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<ConType> conTypeList)
    {
        return initZtree(conTypeList, null);
    }
    
    /**
     * 对象转类型树
     *
     * @param conTypeList 类型列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<ConType> conTypeList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (ConType conType : conTypeList)
        {
            if (UserConstants.DEPT_NORMAL.equals("0"))
            {
                Ztree ztree = new Ztree();
                ztree.setId(conType.getId());
                ztree.setName(conType.getName());
                ztree.setTitle(conType.getName());
//                if (isCheck)
//                {
//                    ztree.setChecked(roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
//                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

	@Override
	public int updateConTypeNum(String conids) {
		// TODO Auto-generated method stub
		return conTypeMapper.updateConTypeNum(Convert.toStrArray(conids));
	}
}
