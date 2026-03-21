package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.CategoryMapper;
import com.example.cangqiong.Pojo.Category.AddCategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryBody;
import com.example.cangqiong.Pojo.Category.CategoryPageResonseBody;
import com.example.cangqiong.Pojo.Category.UpdateCategoryBody;
import com.example.cangqiong.Service.CategoryService;
import com.example.cangqiong.Service.Constant.CategoryConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //分类分页查询
    @Override
    public CategoryPageResonseBody pageCategory(String name, Integer type, Integer page, Integer pageSize) {
        validPageCategory(page, pageSize);
        List<CategoryBody> r = pageCategoryDo(name, type, page, pageSize);
        return new CategoryPageResonseBody(categoryTotal(), r);
    }

    //分类分页查询：查询
    private List<CategoryBody> pageCategoryDo(String name, Integer type, Integer page, Integer pageSize) {
        Integer start = CategoryConstant.startPage(page, pageSize);
        List<CategoryBody> r = categoryMapper.queryCategoryPage(name, type, start, pageSize);
        if (!CheckIsValidUtil.isValid(r)) {
            log.warn(CategoryConstant.CATEGORY_PAGE_RESULT_ERROR);
            throw new BusinessException(CategoryConstant.CATEGORY_PAGE_RESULT_ERROR
                    , CategoryConstant.CODE_BEHIND);
        }
        return r;
    }

    //分类分页查询：校验参数
    private static void validPageCategory(Integer page, Integer pageSize) {
        if (!CheckIsValidUtil.isValid(page) || !CheckIsValidUtil.isValid(pageSize)) {
            log.warn(CategoryConstant.CATEGORY_PAGE_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.CATEGORY_PAGE_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
    }

    //新增分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addCategory(AddCategoryBody addCategoryBody) {
        ValidAddCategoryParam(addCategoryBody);
        Integer row = categoryMapper.addNewCategory(addCategoryBody);
        return row;
    }

    //新增分类：校验参数
    private static void ValidAddCategoryParam(AddCategoryBody addCategoryBody) {
        if (!CheckIsValidUtil.isValid(addCategoryBody)) {
            log.warn(CategoryConstant.ADD_CATEGORY_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.ADD_CATEGORY_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
    }

    //修改分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateCategory(UpdateCategoryBody updateCategoryBody) {
        validUpdateCategoryParam(updateCategoryBody);
        Integer row = categoryMapper.updateCategory(updateCategoryBody);
        return row;
    }

    //修改分类：校验参数
    private static void validUpdateCategoryParam(UpdateCategoryBody updateCategoryBody) {
        if (!CheckIsValidUtil.isValid(updateCategoryBody)) {
            log.warn(CategoryConstant.UPDATE_CATEGORY_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.UPDATE_CATEGORY_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
    }

    //修改分类状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateCategoryStatus(Integer status, Integer id) {
        validUpdateCategoryStatusParam(status, id);
        Integer row = categoryMapper.updateCategoryStatus(status, id);
        return row;
    }

    //修改分类状态：校验参数
    private static void validUpdateCategoryStatusParam(Integer status, Integer id) {
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)) {
            log.warn(CategoryConstant.UPDATE_CATEGORY_STATUS_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.UPDATE_CATEGORY_STATUS_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
    }

    //根据type查询分类
    @Override
    public List<CategoryBody> selectCategoryByType(Integer type) {
        validSelectCategoryByTypeParam(type);
        List<CategoryBody> categoryBodies = getCategoryBodiesDo(type);
        return categoryBodies;
    }

    //根据type查询分类：执行
    private List<CategoryBody> getCategoryBodiesDo(Integer type) {
        List<CategoryBody> categoryBodies = categoryMapper.selectCategoryByType(type);
        if (!CheckIsValidUtil.isValid(categoryBodies)) {
            log.warn(CategoryConstant.SELECT_BY_TYPE_RESULT_ERROR);
            throw new BusinessException(CategoryConstant.SELECT_BY_TYPE_RESULT_ERROR
                    , CategoryConstant.CODE_BEHIND);
        }
        return categoryBodies;
    }

    //根据type查询分类：校验参数
    private static void validSelectCategoryByTypeParam(Integer type) {
        if (!CheckIsValidUtil.isValid(type)) {
            log.warn(CategoryConstant.SELECT_BY_TYPE_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.SELECT_BY_TYPE_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
    }

    //删除分类根据id
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteCategoryById(Integer id) {
        validDeleteCategoryByIdParam(id);
        Integer row = categoryMapper.deleteCategoryById(id);
        return row;
    }

    //删除分类根据id：校验参数
    private static void validDeleteCategoryByIdParam(Integer id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(CategoryConstant.DELETE_CATEGORY_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.DELETE_CATEGORY_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
    }

    //分类所有数量
    private long categoryTotal() {
        long categoryTotal = categoryMapper.getCategoryTotal();
        if (!CheckIsValidUtil.isValid(categoryTotal)) {
            log.warn(CategoryConstant.CATEGORY_TOTAL_RESULT_ERROR);
            throw new BusinessException(CategoryConstant.CATEGORY_TOTAL_RESULT_ERROR
                    , CategoryConstant.CODE_BEHIND);
        }
        return categoryTotal;
    }


}


