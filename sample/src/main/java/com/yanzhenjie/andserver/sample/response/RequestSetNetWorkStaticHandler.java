/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.andserver.sample.response;

import android.content.Intent;
import android.util.Log;

import com.yanzhenjie.andserver.RequestHandler;
import com.yanzhenjie.andserver.sample.interf.WApplication;
import com.yanzhenjie.andserver.sample.util.JsonUtil;
import com.yanzhenjie.andserver.util.HttpRequestParser;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * <p>Login Handler.</p>
 * Created by Yan Zhenjie on 2016/6/13.
 */
public class RequestSetNetWorkStaticHandler implements RequestHandler {

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        Map<String, String> params = HttpRequestParser.parse(request);

        Log.d("wzb", "Params: " + params.toString());

        String ip = URLDecoder.decode(params.get("ip"), "utf-8");
        String mask = URLDecoder.decode(params.get("mask"), "utf-8");
        String gate=URLDecoder.decode(params.get("gate"), "utf-8");
        String dns1=URLDecoder.decode(params.get("dns1"), "utf-8");
        String dns2=URLDecoder.decode(params.get("dns2"), "utf-8");

        Intent intent = new Intent("com.android.custom.net_static");

        intent.putExtra("ip",ip);
        intent.putExtra("mask",mask);
        intent.putExtra("gate",gate);
        intent.putExtra("dns1",dns1);
        intent.putExtra("dns2",dns2);
        WApplication.CONTEXT.sendBroadcast(intent);
        String rs="";

        rs= JsonUtil.httpApiRes("1","set ok","");

        StringEntity stringEntity = new StringEntity(rs, "utf-8");
        response.setEntity(stringEntity);

    }
}
