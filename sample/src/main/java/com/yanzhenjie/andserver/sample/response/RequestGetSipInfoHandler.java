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

import android.util.Log;

import com.yanzhenjie.andserver.RequestHandler;
import com.yanzhenjie.andserver.sample.interf.WApplication;
import com.yanzhenjie.andserver.sample.util.JsonUtil;
import com.yanzhenjie.andserver.sample.util.lNetUtil;
import com.yanzhenjie.andserver.util.HttpRequestParser;
import com.yanzhenjie.nohttp.tools.NetUtil;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


public class RequestGetSipInfoHandler implements RequestHandler {

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        Map<String, String> params = HttpRequestParser.parse(request);

        Log.d("wzb", "Params: " + params.toString());

        String rs="";
        String code="0";
        String status="已注册";
        String username="";
        String displayname="";
        String account="";
        String password="";
        String enable="0";
        String server="";
        String port="";
        account=WApplication.sp_ext.get("sip1account","");
        username=WApplication.sp_ext.get("sip1username",account);
        displayname=WApplication.sp_ext.get("sip1displayname",account);
        password=WApplication.sp_ext.get("sip1password","");
        server=WApplication.sp_ext.get("sip1host","");
        port=WApplication.sp_ext.get("sip1port","5060");
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("code",code);
        map.put("status",status);
        map.put("username",username);
        map.put("displayname",displayname);
        map.put("account",account);
        map.put("password",password);
        map.put("enable",enable);
        map.put("server",server);
        map.put("port",port);
        try {
            rs= JsonUtil.packageJsonObject(map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String callback= URLDecoder.decode(params.get("callback"), "utf-8");
        if(callback!=null){
            rs=callback+"("+rs+")";
        }
        StringEntity stringEntity = new StringEntity(rs, "utf-8");
        response.setEntity(stringEntity);

    }
}
