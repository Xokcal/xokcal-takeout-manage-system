package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.Oss.Oss;
import com.example.cangqiong.Service.CommonService;
import com.example.cangqiong.Service.Constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class CommonImpl implements CommonService {

    @Autowired
    private Oss oss;

    //文件上传
    @Override
    public String uploadFile(MultipartFile file){
        validUploadFileParam(file);
        String r = uploadFileDo(file);
        log.info(CommonConstant.FILE_UPLOAD_RESULT_RIGHT , r);
        return r;
    }

    //文件上传：执行
    private String uploadFileDo(MultipartFile file) {
        String r = oss.uploadFile(file);
        if (!CheckIsValidUtil.isValid(r)){
            log.warn(CommonConstant.FILE_UPLOAD_RESULT_ERROR);
            throw new BusinessException(CommonConstant.FILE_UPLOAD_RESULT_ERROR
                    ,CommonConstant.CODE_BEHIND);
        }
        return r;
    }

    //文件上传：校验参数
    private void validUploadFileParam(MultipartFile file) {
        if (!CheckIsValidUtil.isValid(file)){
            log.warn(CommonConstant.FILE_UPLOAD_PARAM_ERROR);
            throw new BusinessException(CommonConstant.FILE_UPLOAD_PARAM_ERROR
                    , CommonConstant.CODE_FRONT);
        }
    }


}
