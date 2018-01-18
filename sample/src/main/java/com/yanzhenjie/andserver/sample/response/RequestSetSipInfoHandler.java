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
public class RequestSetSipInfoHandler implements RequestHandler {

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        Map<String, String> params = HttpRequestParser.parse(request);

        Log.d("wzb", "Params: " + params.toString());

       // StringEntity sstringEntity = new StringEntity("Login Succeed", "utf-8");
      //  response.setEntity(sstringEntity);
        String userName = URLDecoder.decode(params.get("username"), "utf-8");
        String password = URLDecoder.decode(params.get("password"), "utf-8");
        String displayname=URLDecoder.decode(params.get("displayname"), "utf-8");
        String account=URLDecoder.decode(params.get("displayname"), "utf-8");
        String enable=URLDecoder.decode(params.get("enable"), "utf-8");
        String server=URLDecoder.decode(params.get("server"), "utf-8");
        String port=URLDecoder.decode(params.get("port"), "utf-8");
        Intent intent = new Intent("com.android.sip.update");
        intent.putExtra("num","1");//1组sip 帐号 //目前单帐号固定为1
        intent.putExtra("sip1account",account); //帐号
        intent.putExtra("sip1password",password);//密码
        intent.putExtra("sip1host",server); //服务器
        intent.putExtra("sip1port",port);//端口
        intent.putExtra("sip1protocol","TCP");//协议
        WApplication.CONTEXT.sendBroadcast(intent);
        String rs="";

        rs= JsonUtil.httpApiRes("1","set ok","");
        String callback=URLDecoder.decode(params.get("callback"), "utf-8");
        if(callback!=null){
            rs=callback+"("+rs+")";
        }

        StringEntity stringEntity = new StringEntity(rs, "utf-8");
        response.setEntity(stringEntity);

    }
}
