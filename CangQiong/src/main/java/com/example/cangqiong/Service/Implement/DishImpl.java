package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.DishMapper;
import com.example.cangqiong.Pojo.Dish.DishAndDishFlavorBody;
import com.example.cangqiong.Pojo.Dish.DishFlavorBody;
import com.example.cangqiong.Pojo.Dish.DishPageResponseBody;
import com.example.cangqiong.Pojo.Dish.DishSelectPageBody;
import com.example.cangqiong.Service.Constant.DishConstant;
import com.example.cangqiong.Service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DishImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    //新增菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addNewDishMain(DishAndDishFlavorBody dishAndDishFlavorBody) {
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBody)) {
            log.warn(DishConstant.ADD_DISH_PARAM_ERROR);
            throw new BusinessException(DishConstant.ADD_DISH_PARAM_ERROR, DishConstant.CODE_FRONT);
        }
        Integer row1 = dishMapper.addNewDish(dishAndDishFlavorBody);
        if (row1 < 1) {
            log.warn(DishConstant.ADD_DISH_RESULT_ERROR);
            throw new BusinessException(DishConstant.ADD_DISH_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        List<DishFlavorBody> dishFlavorBodies = dishKeyToFlavor(dishAndDishFlavorBody.getId()
                , dishAndDishFlavorBody.getFlavors());
        Integer row2 = dishMapper.batchAddFlavor(dishFlavorBodies);
        log.info(DishConstant.ADD_DISH_RESULT_RIGHT, row1, row2);
        return DishConstant.END_RIGHT_RESULT_CODE;
    }

    //分页查询菜品
    @Override
    public DishPageResponseBody selectDishPageMain(DishSelectPageBody dishSelectPageBody) {
        if (!CheckIsValidUtil.isValid(dishSelectPageBody)) {
            log.warn(DishConstant.SELECT_DISH_PAGE_PARAM_ERROR);
            throw new BusinessException(DishConstant.SELECT_DISH_PAGE_PARAM_ERROR, DishConstant.CODE_FRONT);
        }
        dishSelectPageBody.setStart(DishConstant.startPage
                (dishSelectPageBody.getPage(), dishSelectPageBody.getPageSize()));
        List<DishAndDishFlavorBody> dishAndDishFlavorBodies = dishMapper.selectDishPage(dishSelectPageBody);
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBodies)) {
            log.warn(DishConstant.SELECT_DISH_PAGE_RESULT_ERROR);
            throw new BusinessException(DishConstant.SELECT_DISH_PAGE_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return new DishPageResponseBody(dishTotal(), dishAndDishFlavorBodies);
    }

    //更新菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDishMain(DishAndDishFlavorBody dishAndDishFlavorBody) {
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBody)) {
            log.warn(DishConstant.UPDATE_DISH_PARAM_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_PARAM_ERROR, DishConstant.CODE_FRONT);
        }
        dishMapper.deleteFlavor(dishAndDishFlavorBody.getId());
        if (!dishAndDishFlavorBody.getFlavors().isEmpty()) {
            Integer row = dishMapper.batchAddFlavor(dishKeyToFlavor(dishAndDishFlavorBody.getId()
                    , dishAndDishFlavorBody.getFlavors()));
            if (row < 1) {
                log.warn(DishConstant.UPDATE_DISH_DELETE_FLAVOR_ERROR);
                throw new BusinessException(DishConstant.UPDATE_DISH_DELETE_FLAVOR_ERROR
                        , DishConstant.CODE_BEHIND);
            }
        }
        return dishMapper.updateDish(dishAndDishFlavorBody);
    }

    //根据id查询菜品
    @Override
    public DishAndDishFlavorBody selectDishByIdMain(Long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(DishConstant.QUERY_DISH_BY_ID_PARAM_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_ID_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
        DishAndDishFlavorBody dishAndDishFlavorBody = dishMapper.selectDishById(id);
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBody)) {
            log.warn(DishConstant.QUERY_DISH_BY_ID_DISH_RESULT_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_ID_DISH_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        List<DishFlavorBody> dishFlavorBodies = dishMapper.selectFlavorByDishId(id);
        if (!CheckIsValidUtil.isValid(dishFlavorBodies)) {
            log.warn(DishConstant.QUERY_DISH_BY_ID_FLAVOR_RESULT_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_ID_FLAVOR_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        dishAndDishFlavorBody.setFlavors(dishFlavorBodies);
        return dishAndDishFlavorBody;
    }

    //根据分类id查询餐品
    @Override
    public List<DishAndDishFlavorBody> selectDishByCategoryIdMain(Long categoryId) {
        if (!CheckIsValidUtil.isValid(categoryId)) {
            log.warn(DishConstant.QUERY_DISH_BY_CATEGORY_ID_PARAM_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_CATEGORY_ID_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
        List<DishAndDishFlavorBody> dishAndDishFlavorBodes = dishMapper.selectDishByCategoryId(categoryId);
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBodes)) {
            log.warn(DishConstant.QUERY_DISH_BY_CATEGORY_ID_RESULT_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_CATEGORY_ID_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return dishAndDishFlavorBodes;
    }

    //菜品起售、停售
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDishStatusMain(Integer status , Long id){
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)){
            log.warn(DishConstant.UPDATE_DISH_STATUS_PARAM_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_STATUS_PARAM_ERROR
                    ,DishConstant.CODE_FRONT);
        }
        Integer row = dishMapper.updateDishStatus(status, id);
        if (row < 1){
            log.warn(DishConstant.UPDATE_DISH_STATUS_RESULT_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_STATUS_RESULT_ERROR
                    ,DishConstant.CODE_BEHIND);
        }
        return row;
    }

    //批量删除
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteDishAndFlavorMain(List<Long> ids) {
        if (!CheckIsValidUtil.isValid(ids)) {
            log.warn(DishConstant.DELETE_DISH_AND_FLAVOR_PARAM_ERROR);
            throw new BusinessException(DishConstant.DELETE_DISH_AND_FLAVOR_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
        Integer row1 = dishMapper.batchDeleteFlavor(ids);
        if (row1 < 1) {
            log.warn(DishConstant.DELETE_DISH_AND_FLAVOR_RESULT_ERROR);
            throw new BusinessException(DishConstant.DELETE_DISH_AND_FLAVOR_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        Integer row2 = dishMapper.batchDeleteDish(ids);
        log.info(DishConstant.DELETE_DISH_RESULT_RIGHT, row1, row2);
        return DishConstant.END_RIGHT_RESULT_CODE;
    }

    //将dish的主键给到flavor
    private List<DishFlavorBody> dishKeyToFlavor(Long id, List<DishFlavorBody> list) {
        if (!CheckIsValidUtil.isValid(id) || !CheckIsValidUtil.isValid(list)) {
            log.warn(DishConstant.DISH_KEY_TO_FLAVOR_PARAM_ERROR);
            throw new BusinessException(DishConstant.DISH_KEY_TO_FLAVOR_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
        list.forEach(E -> {
            E.setDishId(id);
        });
        return list;
    }

    //获取菜品总数
    private Long dishTotal() {
        Long total = dishMapper.total();
        if (!CheckIsValidUtil.isValid(total)) {
            log.warn(DishConstant.GET_DISH_TOTAL_RESULT_ERROR);
            throw new BusinessException(DishConstant.GET_DISH_TOTAL_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return total;
    }


}
