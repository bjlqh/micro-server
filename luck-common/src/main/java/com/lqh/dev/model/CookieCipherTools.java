package com.lqh.dev.model;

import com.lqh.dev.secutity.Base32;
import com.lqh.dev.secutity.DESCoder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.UnsupportedEncodingException;

public class CookieCipherTools {
    private static final Logger LOG = LoggerFactory.getLogger(CookieCipherTools.class);
    private String charsetName;

    public CookieCipherTools() {
    }

    public String encrypt(String value, String key) {
        try {
            byte[] data;
            if (!StringUtils.isEmpty(this.charsetName)) {
                try {
                    data = value.getBytes(this.charsetName);
                } catch (Exception var5) {
                    LOG.error("charset " + this.charsetName + " Unsupported!", var5);
                    data = value.getBytes();
                }
            } else {
                data = value.getBytes();
            }

            byte[] bytes = this.encrypt(key, data);
            return this.encoding(bytes);
        } catch (Exception var6) {
            LOG.error("encrypt error", var6);
            return null;
        }
    }

    private String encoding(byte[] bytes) throws Exception {
        return Base32.encode(bytes);
    }

    private byte[] decoding(String value) throws Exception {
        return Base32.decode(value);
    }

    private byte[] encrypt(String key, byte[] data) throws Exception {
        return DESCoder.encrypt(data, key);
    }

    private byte[] decrypt(String key, byte[] data) throws Exception {
        return DESCoder.decrypt(data, key);
    }

    public String decrypt(String value, String key) {
        try {
            byte[] data = this.decoding(value);
            byte[] bytes = this.decrypt(key, data);
            if (!StringUtils.isEmpty(this.charsetName)) {
                try {
                    return new String(bytes, this.charsetName);
                } catch (UnsupportedEncodingException var6) {
                    LOG.error("charset " + this.charsetName + " Unsupported!", var6);
                    return new String(bytes);
                }
            } else {
                return new String(bytes);
            }
        } catch (Exception var7) {
            LOG.error("encrypt error", var7);
            return null;
        }
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }
}
