package com.demo.sheep.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 返回Long转换为String
 */
class JacksonMapper extends ObjectMapper {

  JacksonMapper() {
    super();
    this.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
    this.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    simpleModule.addSerializer(long.class, ToStringSerializer.instance);
    //DateSerializer ds = new DateSerializer(false,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    //simpleModule.addSerializer(Date.class, ds);
    registerModule(simpleModule);

  }
}