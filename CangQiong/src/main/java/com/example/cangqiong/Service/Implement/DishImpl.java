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
        addNewDishValidParam(dishAndDishFlavorBody);
        Integer row1 = addNewDishForAddNewDish(dishAndDishFlavorBody);
        List<DishFlavorBody> dishFlavorBodies = dishKeyToFlavor(dishAndDishFlavorBody.getId()
                , dishAndDishFlavorBody.getFlavors());
        Integer row2 = batchAddFlavor(dishFlavorBodies);
        log.info(DishConstant.ADD_DISH_RESULT_RIGHT, row1, row2);
        return DishConstant.END_RIGHT_RESULT_CODE;
    }

    //新增菜品，批量添加风味
    private Integer batchAddFlavor(List<DishFlavorBody> dishFlavorBodies) {
        Integer row2 = dishMapper.batchAddFlavor(dishFlavorBodies);
        return row2;
    }

    //新增菜品，校验参数
    private void addNewDishValidParam(DishAndDishFlavorBody dishAndDishFlavorBody) {
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBody)) {
            log.warn(DishConstant.ADD_DISH_PARAM_ERROR);
            throw new BusinessException(DishConstant.ADD_DISH_PARAM_ERROR, DishConstant.CODE_FRONT);
        }
    }

    //添加新餐品
    private Integer addNewDishForAddNewDish(DishAndDishFlavorBody dishAndDishFlavorBody) {
        Integer row = dishMapper.addNewDish(dishAndDishFlavorBody);
        if (row < 1) {
            log.warn(DishConstant.ADD_DISH_RESULT_ERROR);
            throw new BusinessException(DishConstant.ADD_DISH_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return row;
    }

    //分页查询菜品
    @Override
    public DishPageResponseBody selectDishPageMain(DishSelectPageBody dishSelectPageBody) {
        selectDishPageValidParam(dishSelectPageBody);
        dishSelectPageBody.setStart(DishConstant.startPage
                (dishSelectPageBody.getPage(), dishSelectPageBody.getPageSize()));
        List<DishAndDishFlavorBody> dishAndDishFlavorBodies = selectDishPageBranch(dishSelectPageBody);
        return new DishPageResponseBody(dishTotal(), dishAndDishFlavorBodies);
    }

    //分页查询菜品，参数校验
    private void selectDishPageValidParam(DishSelectPageBody dishSelectPageBody) {
        if (!CheckIsValidUtil.isValid(dishSelectPageBody)) {
            log.warn(DishConstant.SELECT_DISH_PAGE_PARAM_ERROR);
            throw new BusinessException(DishConstant.SELECT_DISH_PAGE_PARAM_ERROR, DishConstant.CODE_FRONT);
        }
    }

    //分页查询菜品
    private List<DishAndDishFlavorBody> selectDishPageBranch(DishSelectPageBody dishSelectPageBody) {
        List<DishAndDishFlavorBody> dishAndDishFlavorBodies = dishMapper.selectDishPage(dishSelectPageBody);
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBodies)) {
            log.warn(DishConstant.SELECT_DISH_PAGE_RESULT_ERROR);
            throw new BusinessException(DishConstant.SELECT_DISH_PAGE_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return dishAndDishFlavorBodies;
    }

    //更新菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDishMain(DishAndDishFlavorBody dishAndDishFlavorBody) {
        updateDishValidParam(dishAndDishFlavorBody);
        deleteFlavor(dishAndDishFlavorBody);
        return dishMapper.updateDish(dishAndDishFlavorBody);
    }

    //更新菜品，删除风味
    private void deleteFlavor(DishAndDishFlavorBody dishAndDishFlavorBody) {
        dishMapper.deleteFlavor(dishAndDishFlavorBody.getId());
        if (!dishAndDishFlavorBody.getFlavors().isEmpty()) {
            batchAddFlavorForUpdateDish(dishAndDishFlavorBody);
        }
    }

    //更新菜品，参数校验
    private void updateDishValidParam(DishAndDishFlavorBody dishAndDishFlavorBody) {
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBody)) {
            log.warn(DishConstant.UPDATE_DISH_PARAM_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_PARAM_ERROR, DishConstant.CODE_FRONT);
        }
    }

    //批量添加风味
    private void batchAddFlavorForUpdateDish(DishAndDishFlavorBody dishAndDishFlavorBody) {
        Integer row = dishMapper.batchAddFlavor(dishKeyToFlavor(dishAndDishFlavorBody.getId()
                , dishAndDishFlavorBody.getFlavors()));
        if (row < 1) {
            log.warn(DishConstant.UPDATE_DISH_DELETE_FLAVOR_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_DELETE_FLAVOR_ERROR
                    , DishConstant.CODE_BEHIND);
        }
    }

    //根据id查询菜品
    @Override
    public DishAndDishFlavorBody selectDishByIdMain(Long id) {
        selectDishByIdValidParam(id);
        DishAndDishFlavorBody dishAndDishFlavorBody = selectDishByIdBranch(id);
        List<DishFlavorBody> dishFlavorBodies = selectFlavorByIdBranch(id);
        dishAndDishFlavorBody.setFlavors(dishFlavorBodies);
        return dishAndDishFlavorBody;
    }

    //根据id查询菜品，参数校验
    private void selectDishByIdValidParam(Long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(DishConstant.QUERY_DISH_BY_ID_PARAM_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_ID_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
    }

    //查询菜品根据id，得到结果
    private DishAndDishFlavorBody selectDishByIdBranch(Long id) {
        DishAndDishFlavorBody dishAndDishFlavorBody = dishMapper.selectDishById(id);
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBody)) {
            log.warn(DishConstant.QUERY_DISH_BY_ID_DISH_RESULT_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_ID_DISH_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return dishAndDishFlavorBody;
    }

    //查询风味根据id，得到结果
    private List<DishFlavorBody> selectFlavorByIdBranch(Long id) {
        List<DishFlavorBody> dishFlavorBodies = dishMapper.selectFlavorByDishId(id);
        if (!CheckIsValidUtil.isValid(dishFlavorBodies)) {
            log.warn(DishConstant.QUERY_DISH_BY_ID_FLAVOR_RESULT_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_ID_FLAVOR_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return dishFlavorBodies;
    }

    //根据分类id查询餐品
    @Override
    public List<DishAndDishFlavorBody> selectDishByCategoryIdMain(Long categoryId) {
        selectDishByCategoryIdValidParam(categoryId);
        List<DishAndDishFlavorBody> dishAndDishFlavorBodies = selectDishByCategoryId(categoryId);
        return dishAndDishFlavorBodies;
    }

    //根据分类id查询餐品，查询dish通过分类id
    private List<DishAndDishFlavorBody> selectDishByCategoryId(Long categoryId) {
        List<DishAndDishFlavorBody> dishAndDishFlavorBodes = dishMapper.selectDishByCategoryId(categoryId);
        if (!CheckIsValidUtil.isValid(dishAndDishFlavorBodes)) {
            log.warn(DishConstant.QUERY_DISH_BY_CATEGORY_ID_RESULT_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_CATEGORY_ID_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return dishAndDishFlavorBodes;
    }

    //根据分类id查询餐品，参数校验
    private void selectDishByCategoryIdValidParam(Long categoryId) {
        if (!CheckIsValidUtil.isValid(categoryId)) {
            log.warn(DishConstant.QUERY_DISH_BY_CATEGORY_ID_PARAM_ERROR);
            throw new BusinessException(DishConstant.QUERY_DISH_BY_CATEGORY_ID_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
    }

    //菜品起售、停售
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDishStatusMain(Integer status , Long id){
        updateDishStatusValidParam(status, id);
        return updateDishStatusBranch(status, id);
    }

    //菜品起售、停售，更新dish状态
    private Integer updateDishStatusBranch(Integer status, Long id) {
        Integer row = dishMapper.updateDishStatus(status, id);
        if (row < 1){
            log.warn(DishConstant.UPDATE_DISH_STATUS_RESULT_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_STATUS_RESULT_ERROR
                    ,DishConstant.CODE_BEHIND);
        }
        return row;
    }

    //菜品起售、停售，参数校验
    private void updateDishStatusValidParam(Integer status, Long id) {
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)){
            log.warn(DishConstant.UPDATE_DISH_STATUS_PARAM_ERROR);
            throw new BusinessException(DishConstant.UPDATE_DISH_STATUS_PARAM_ERROR
                    ,DishConstant.CODE_FRONT);
        }
    }

    //批量删除
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteDishAndFlavorMain(List<Long> ids) {
        deleteDishAndFlavorValidParam(ids);
        Integer row1 = batchDeleteFlavor(ids);
        Integer row2 = dishMapper.batchDeleteDish(ids);
        log.info(DishConstant.DELETE_DISH_RESULT_RIGHT, row1, row2);
        return DishConstant.END_RIGHT_RESULT_CODE;
    }

    //批量删除，批量删除风味
    private Integer batchDeleteFlavor(List<Long> ids) {
        Integer row1 = dishMapper.batchDeleteFlavor(ids);
        if (row1 < 1) {
            log.warn(DishConstant.DELETE_DISH_AND_FLAVOR_RESULT_ERROR);
            throw new BusinessException(DishConstant.DELETE_DISH_AND_FLAVOR_RESULT_ERROR
                    , DishConstant.CODE_BEHIND);
        }
        return row1;
    }

    //批量删除，校验参数
    private void deleteDishAndFlavorValidParam(List<Long> ids) {
        if (!CheckIsValidUtil.isValid(ids)) {
            log.warn(DishConstant.DELETE_DISH_AND_FLAVOR_PARAM_ERROR);
            throw new BusinessException(DishConstant.DELETE_DISH_AND_FLAVOR_PARAM_ERROR
                    , DishConstant.CODE_FRONT);
        }
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
