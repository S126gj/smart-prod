package com.smart.prod.sms.service.impl;

import com.smart.prod.common.constanst.Constanst;
import com.smart.prod.sms.service.MessageService;
import com.smart.prod.sms.utils.RlySmsUtil;
import com.smart.prod.sms.utils.SmsConstants;
import com.smart.prod.sms.utils.TxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短信服务实现类
 * @Author: Guoji Shen
 * @Date: 2022/9/20 13:30
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public boolean sendTxVerify(String phone, String verifyCode) {
        return TxSmsUtil.sendMessage(phone, SmsConstants.TX_LOGIN_VERIFY_TEMPLATE_ID, new String[]{verifyCode, Constanst.DEFAULT_AUTH_CODE_EXPIRE_STR});
    }

    @Override
    public boolean sendRlyVerify(String phone, String verifyCode) {
        return RlySmsUtil.sendMessage(phone, SmsConstants.RLY_LOGIN_VERIFY_TEMPLATE_ID, new String[]{verifyCode, Constanst.DEFAULT_AUTH_CODE_EXPIRE_STR});
    }
}