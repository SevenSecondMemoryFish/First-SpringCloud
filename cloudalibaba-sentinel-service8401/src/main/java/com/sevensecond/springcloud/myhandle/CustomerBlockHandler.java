package com.sevensecond.springcloud.myhandle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.seven.second.springcloud.entities.CommentResult;

public class CustomerBlockHandler {

    /// 此方法必需是static 方法，乌龟的屁股
    public static CommentResult handlerBlockException(BlockException exception) {
        return new CommentResult(414, "全局处理BlockException");
    }
}
