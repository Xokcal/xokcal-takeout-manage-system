package com.example.cangqiong.Service.Implement;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.SetmealMapper;
import com.example.cangqiong.Pojo.Setmeal.*;
import com.example.cangqiong.Service.Constant.SetmealConstant;
import com.example.cangqiong.Service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SetmealImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    //分页查询套餐
    @Override
    public SetmealPageResonseBody pageSetmeal(PageSetmealRequetBody pageSetmealRequetBody) {
        validPageSetmealParam(pageSetmealRequetBody);
        List<SetmealAndDishBody> setmealBodies = pageSetmealDo(pageSetmealRequetBody);
        return new SetmealPageResonseBody(getSetmealTotal(), setmealBodies);
    }

    //分页查询套餐：执行
    private List<SetmealAndDishBody> pageSetmealDo(PageSetmealRequetBody pageSetmealRequetBody) {
        Integer start = SetmealConstant.startPage(pageSetmealRequetBody.getPage(), pageSetmealRequetBody.getPageSize());
        List<SetmealAndDishBody> setmealBodies = setmealMapper.selectSetmealPage(pageSetmealRequetBody , start);
        if (!CheckIsValidUtil.isValid(setmealBodies)) {
            log.warn(SetmealConstant.SETMEAL_SELECT_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.SETMEAL_SELECT_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return setmealBodies;
    }

    //分页查询套餐：校验参数
    private static void validPageSetmealParam(PageSetmealRequetBody pageSetmealRequetBody) {
        if (!CheckIsValidUtil.isValid(pageSetmealRequetBody)) {
            log.warn(SetmealConstant.SETMEAL_SELECT_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.SETMEAL_SELECT_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
    }

    //新增套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addSetmeal(SetmealAndDishBody setmealAndDishBody) {
        validAddSetmealParam(setmealAndDishBody);
        Integer row1 = insertSetmealDo(setmealAndDishBody);
        List<SetmealDishBody> setmealDishBodies = fillKeyToSetmealAndDishBody(setmealAndDishBody);
        Integer row2 = insertSetmealDishDo(setmealDishBodies);
        validAddSetmealResult(row1, row2);
        return SetmealConstant.ADD_SETMEAL_PASS_RESULT;
    }

    //新增套餐：执行插入套餐餐品
    private Integer insertSetmealDishDo(List<SetmealDishBody> setmealDishBodies) {
        Integer row2 = setmealMapper.insertSetmealDish(setmealDishBodies);
        return row2;
    }

    //新增套餐：
    private List<SetmealDishBody> fillKeyToSetmealAndDishBody(SetmealAndDishBody setmealAndDishBody) {
        List<SetmealDishBody> setmealDishBodies = batchKeyValueToSetmealDishBody(
                setmealAndDishBody.getId(), setmealAndDishBody.getSetmealDishes());
        return setmealDishBodies;
    }

    //新增套餐：执行
    private Integer insertSetmealDo(SetmealAndDishBody setmealAndDishBody) {
        Integer row1 = setmealMapper.insertSetmeal(setmealAndDishBody);
        return row1;
    }

    //新增套餐：校验结果
    private static void validAddSetmealResult(Integer row1, Integer row2) {
        if (row1 < 1 || row2 < 1) {
            log.warn(SetmealConstant.ADD_SETMEAL_RESULT_DEFEAT);
            throw new BusinessException(SetmealConstant.ADD_SETMEAL_RESULT_DEFEAT
                    , SetmealConstant.CODE_BEHIND);
        }
    }

    //新增套餐：校验参数
    private static void validAddSetmealParam(SetmealAndDishBody setmealAndDishBody) {
        if (!CheckIsValidUtil.isValid(setmealAndDishBody)) {
            log.warn(SetmealConstant.ADD_SETMEAL_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.ADD_SETMEAL_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
    }

    //修改套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateSetmeal(SetmealAndDishBody setmealAndDishBody) {
        validUpdateSetmealParam(setmealAndDishBody);
        Integer row1 = updateSetmealDo(setmealAndDishBody);
        deleteSetmealDish(setmealAndDishBody.getId());
        List<SetmealDishBody> setmealDishBodies = fillKeyToSetmealAndDishBody(setmealAndDishBody);
        Integer row2 = insertSetmealDishDo(setmealDishBodies);
        validUpdateSetmealResult(row1, row2);
        return SetmealConstant.END_RIGHT_RESULT_CODE;
    }

    //修改套餐：校验参数
    private static void validUpdateSetmealParam(SetmealAndDishBody setmealAndDishBody) {
        if (!CheckIsValidUtil.isValid(setmealAndDishBody)) {
            log.warn(SetmealConstant.UPDATE_SETMEAL_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.UPDATE_SETMEAL_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
    }

    //修改套餐：校验结果
    private static void validUpdateSetmealResult(Integer row1, Integer row2) {
        if (row1 < 1 || row2 < 1) {
            log.warn(SetmealConstant.UPDATE_SETMEALRESULT_ERROR);
            throw new BusinessException(SetmealConstant.UPDATE_SETMEALRESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
    }

    //修改套餐：执行
    private Integer updateSetmealDo(SetmealAndDishBody setmealAndDishBody) {
        Integer row1 = setmealMapper.updateSetmeal(setmealAndDishBody);
        return row1;
    }

    //批量删除套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteSetmealAndSetmealDish(List<Long> ids) {
        validDeleteSetmealAndSetmealDishParam(ids);
        Integer row1 = batchDeleteSetmealDo(ids);
        Integer row2 = batchDeleteSetmealDishDo(ids);
        log.info(SetmealConstant.DELETE_SETMEAL_AND_DISH_RESULT_RIGHT, row1, row2);
        return SetmealConstant.END_RIGHT_RESULT_CODE;
    }

    //批量删除套餐：批量删除餐品
    private Integer batchDeleteSetmealDishDo(List<Long> ids) {
        Integer row2 = setmealMapper.batchDeleteSetmealDish(ids);
        return row2;
    }

    //批量删除套餐：套餐
    private static void validDeleteSetmealAndSetmealDishParam(List<Long> ids) {
        if (!CheckIsValidUtil.isValid(ids)) {
            log.warn(SetmealConstant.DELETE_SETMEAL_AND_DISH_PARRAM_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_AND_DISH_PARRAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
    }

    //批量删除套餐：批量删除套餐
    private Integer batchDeleteSetmealDo(List<Long> ids){
        Integer row = setmealMapper.batchDeleteSetmeal(ids);
        if (row < 1) {
            log.warn(SetmealConstant.DELETE_SETMEAL_AND_DISH_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_AND_DISH_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return row;
    }

    //修改套餐状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateSetmealStatus(Integer status, Integer id) {
        validUpdateSetmealStatusParam(status, id);
        Integer row = updateSetmealStatusDo(status, id);
        return row;
    }

    //修改套餐状态：执行
    private Integer updateSetmealStatusDo(Integer status, Integer id) {
        Integer row = setmealMapper.updateSetmealStatus(status, id);
        return row;
    }

    //修改套餐状态：校验参数
    private static void validUpdateSetmealStatusParam(Integer status, Integer id) {
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)) {
            log.warn(SetmealConstant.UPDATE_SETMEAL_STATUS_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.UPDATE_SETMEAL_STATUS_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
    }

    //根据id查询套餐
    @Override
    public SetmealAndDishBody selectSetmealById(long id) {
        validSelectSetmealByIdParam(id);
        SetmealAndDishBody setmealAndDishBody = selectSetmealByIdDo(id);
        List<SetmealDishBody> setmealDishBodes = selectSetmealDishByIdDo(id);
        fillSetmealAndDish(setmealAndDishBody, setmealDishBodes);
        return setmealAndDishBody;
    }

    //根据id查询套餐：组装结果
    private static void fillSetmealAndDish(SetmealAndDishBody setmealAndDishBody
            , List<SetmealDishBody> setmealDishBodes) {
        setmealAndDishBody.setSetmealDishes(setmealDishBodes);
        if (!CheckIsValidUtil.isValid(setmealAndDishBody)) {
            log.warn(SetmealConstant.SELECT_SETMEAL_BY_ID_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.SELECT_SETMEAL_BY_ID_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
    }

    //根据id查询套餐：执行通过餐品id查询套餐
    private List<SetmealDishBody> selectSetmealDishByIdDo(long id) {
        List<SetmealDishBody> setmealDishBodes = setmealMapper.selectSetmealDishById(id);
        return setmealDishBodes;
    }

    //根据id查询套餐：执行通过id查询套餐
    private SetmealAndDishBody selectSetmealByIdDo(long id) {
        SetmealAndDishBody setmealAndDishBody = setmealMapper.selectSetmealById(id);
        return setmealAndDishBody;
    }

    //根据id查询套餐：校验参数
    private static void validSelectSetmealByIdParam(long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(SetmealConstant.SELECT_SETMEAL_BY_ID_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.SELECT_SETMEAL_BY_ID_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
    }

    //删除setmeal_dish
    private void deleteSetmealDish(long setmealId) {
        if (!CheckIsValidUtil.isValid(setmealId)) {
            log.warn(SetmealConstant.DELETE_SETMEAL_DISH_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_DISH_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer row = setmealMapper.deleteSetmealDish(setmealId);
        if (row < 1) {
            log.warn(SetmealConstant.DELETE_SETMEAL_DISH_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_DISH_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
    }

    //将新增的主键赋值给每一个SetmealDishes的setmeal_id
    private List<SetmealDishBody> batchKeyValueToSetmealDishBody(long id, List<SetmealDishBody> list) {
        if (!CheckIsValidUtil.isValid(id) || !CheckIsValidUtil.isValid(list)) {
            log.warn(SetmealConstant.SETMEAL_VALUE_KEY_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.SETMEAL_VALUE_KEY_PARAM_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        list.forEach(E -> {E.setSetmealId(id);});
        return list;
    }

    //获得套餐总数
    private long getSetmealTotal() {
        long sermealTotal = setmealMapper.getSermealTotal();
        if (!CheckIsValidUtil.isValid(sermealTotal)) {
            log.warn(SetmealConstant.GET_SETMEAL_TOTAL_ERROR);
            throw new BusinessException(SetmealConstant.GET_SETMEAL_TOTAL_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return sermealTotal;
    }


}
