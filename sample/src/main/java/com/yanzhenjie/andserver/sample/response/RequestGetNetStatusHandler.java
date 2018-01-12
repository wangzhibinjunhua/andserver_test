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

import android.os.Build;
import android.util.Log;

import com.yanzhenjie.andserver.RequestHandler;
import com.yanzhenjie.andserver.sample.interf.WApplication;
import com.yanzhenjie.andserver.sample.util.DateUtil;
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
import java.util.HashMap;
import java.util.Map;


public class RequestGetNetStatusHandler implements RequestHandler {

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        Map<String, String> params = HttpRequestParser.parse(request);

        Log.d("wzb", "Params: " + params.toString());

        String rs="";
        String code="0";
        String type="";
        String mac="";
        String ip="";
        String mask="";
        String gate="";
        if(NetUtil.isWifiConnected()){
            code="1";
            if(lNetUtil.isWifiDHCP(WApplication.CONTEXT)){
                type="DHCP";
            }else{
                type="StaticIP";
            }


        }


        mac=lNetUtil.getLocalMacAddress(WApplication.CONTEXT);
        ip=lNetUtil.getLocalIpAddress();
        StringEntity stringEntity = new StringEntity(rs, "utf-8");
        response.setEntity(stringEntity);

    }
}
