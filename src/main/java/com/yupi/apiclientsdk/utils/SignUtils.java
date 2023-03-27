package com.yupi.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {
    public static String genSign(String body, String secretKey) {
        Digester digester = DigestUtil.digester(DigestAlgorithm.SHA256);
        String digestHex = digester.digestHex(body + "." + secretKey);
        return digestHex;
    }
}
