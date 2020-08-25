package com.study.depend;

import com.study.model.PluginInfo;
import com.study.service.PluginPost;
import org.springframework.util.MultiValueMap;

/**
 * 测试自己写的jar依赖被引用
 *
 * @date2020-07-27
 */
public class Test extends PluginPost {

    @Override
    public Object doPost(String s, Object o) {
        return null;
    }

    @Override
    public void init(PluginInfo pluginInfo) {

    }

    @Override
    public Object doWork(String s, MultiValueMap<String, String> multiValueMap) {
        return null;
    }

    @Override
    public void doWordAsync() {

    }

    @Override
    public void destroy() {

    }
}
