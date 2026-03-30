package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.CategoryMapper;
import com.example.cangqiong.Pojo.Category.*;
import com.example.cangqiong.Service.CategoryService;
import com.example.cangqiong.Service.Constant.CategoryConstant;
import com.example.cangqiong.Service.Redis.CategoryRedis;
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

    @Autowired
    private CategoryRedis categoryRedis;

    //分类分页查询
    @Override
    public CategoryPageResonseBody pageCategory(CategoryPageRequestBody categoryPageRequestBody) {
        if (!CheckIsValidUtil.isValid(categoryPageRequestBody)) {
            log.warn(CategoryConstant.CATEGORY_PAGE_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.CATEGORY_PAGE_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
        if (categoryRedis.categoryPageIsExist(categoryPageRequestBody)){
            return (CategoryPageResonseBody) categoryRedis
                    .getCategoryPageFromRedis(categoryPageRequestBody);
        }
        return getCategoryPageResonseBody(categoryPageRequestBody);
    }

    //分类分页查询（如果redis里面没有数据，那么就查数据库）
    private CategoryPageResonseBody getCategoryPageResonseBody
            (CategoryPageRequestBody categoryPageRequestBody) {
        Integer start = CategoryConstant.startPage(categoryPageRequestBody.getPage()
                , categoryPageRequestBody.getPageSize());
        List<CategoryBody> r = categoryMapper.queryCategoryPage(categoryPageRequestBody, start);
        if (!CheckIsValidUtil.isValid(r)) {
            log.warn(CategoryConstant.CATEGORY_PAGE_RESULT_ERROR);
            throw new BusinessException(CategoryConstant.CATEGORY_PAGE_RESULT_ERROR
                    , CategoryConstant.CODE_BEHIND);
        }
        CategoryPageResonseBody rEnd = new CategoryPageResonseBody(categoryTotal(), r);
        categoryRedis.putCategoryPageToRedis(rEnd,categoryPageRequestBody,10);
        return rEnd;
    }

    //新增分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addCategory(AddCategoryBody addCategoryBody) {
        if (!CheckIsValidUtil.isValid(addCategoryBody)) {
            log.warn(CategoryConstant.ADD_CATEGORY_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.ADD_CATEGORY_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
        categoryRedis.deleteAllRedisCategoryPage();
        Integer row = categoryMapper.addNewCategory(addCategoryBody);
        return row;
    }

    //修改分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateCategory(UpdateCategoryBody updateCategoryBody) {
        if (!CheckIsValidUtil.isValid(updateCategoryBody)) {
            log.warn(CategoryConstant.UPDATE_CATEGORY_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.UPDATE_CATEGORY_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
        categoryRedis.deleteAllRedisCategoryPage();
        Integer row = categoryMapper.updateCategory(updateCategoryBody);
        return row;
    }

    //修改分类状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateCategoryStatus(Integer status, Integer id) {
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)) {
            log.warn(CategoryConstant.UPDATE_CATEGORY_STATUS_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.UPDATE_CATEGORY_STATUS_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
        categoryRedis.deleteAllRedisCategoryPage();
        Integer row = categoryMapper.updateCategoryStatus(status, id);
        return row;
    }

    //根据type查询分类
    @Override
    public List<CategoryBody> selectCategoryByType(Integer type) {
        if (!CheckIsValidUtil.isValid(type)) {
            log.warn(CategoryConstant.SELECT_BY_TYPE_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.SELECT_BY_TYPE_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
        List<CategoryBody> categoryBodies = categoryMapper.selectCategoryByType(type);
        if (!CheckIsValidUtil.isValid(categoryBodies)) {
            log.warn(CategoryConstant.SELECT_BY_TYPE_RESULT_ERROR);
            throw new BusinessException(CategoryConstant.SELECT_BY_TYPE_RESULT_ERROR
                    , CategoryConstant.CODE_BEHIND);
        }
        return categoryBodies;
    }

    //删除分类根据id
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteCategoryById(Integer id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(CategoryConstant.DELETE_CATEGORY_PARAM_ERROR);
            throw new BusinessException(CategoryConstant.DELETE_CATEGORY_PARAM_ERROR
                    , CategoryConstant.CODE_FRONT);
        }
        categoryRedis.deleteAllRedisCategoryPage();
        Integer row = categoryMapper.deleteCategoryById(id);
        return row;
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


