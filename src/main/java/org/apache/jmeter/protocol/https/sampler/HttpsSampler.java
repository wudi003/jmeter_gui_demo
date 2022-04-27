package org.apache.jmeter.protocol.https.sampler;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestStateListener;

import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class HttpsSampler extends AbstractSampler implements TestStateListener {

    private static final long serialVersionUID = 1L;
    private static final String HTTPS_SSL_VERSION = "https.sslVersion";
    private static final String HTTPS_CIPHER = "https.cipher";
    private static final String HTTPS_TWO_WAY= "https.twoWay";
    private static final String HTTPS_CA = "https.caCert";
    private static final String HTTPS_CLIENT_CERT = "https.clientCert";
    private static final String HTTPS_CLIENT_P12 = "https.clientP12";
    private static final String HTTPS_REQUEST = "https.requestsString";
    private static final Logger log = LoggingManager.getLoggerForClass();

    // 构造函数要为pubilc
    public HttpsSampler(){
        setName("Https Sampler");
    }

    /**
     * 该方法是JMeter实现对目标系统发起请求实际工作的地方
     * **/
    @Override
    public SampleResult sample(Entry entry) {
        SampleResult result = new SampleResult();
        result.setSampleLabel(getName());
        result.sampleStart();
        try {
            /**
             * 采样器执行code处
             * **/
            result.setRequestHeaders("请求头---请求头展示数据");
            result.setSamplerData("设置采样器数据");
            if (!getHttpsSslVersion().startsWith("TLS")) {
                // stop stopwatch
                result.sampleEnd();
                result.setSuccessful(false);
                // 设置取样器结果里面响应信息
                result.setResponseMessage("ssl不为TLS开头，采样失败(示例)");
                result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
                result.setResponseCode("FAILED");
                // 设置响应数据的响应body
                result.setResponseData("响应信息---响应体展示数据", null);
                // 设置响应数据的响应header
                result.setResponseHeaders("响应头信息---响应头展示数据");
            }else {
                result.sampleEnd();
                result.setSuccessful(true);
                result.setResponseCodeOK();
            }

        } catch (Exception e) {
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful(false);
            result.setResponseMessage("Exception: " + e);
            // get stack trace as a String to return as document data
            java.io.StringWriter stringWriter = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(stringWriter));
            result.setResponseData(stringWriter.toString(), null);
            result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
            result.setResponseCode("FAILED");
        }
        return result;
    }


    public void setSslVersion(String sslVersion){
        setProperty(HTTPS_SSL_VERSION, sslVersion);
    }

    public void setCipher(String cipher){
        setProperty(HTTPS_CIPHER, cipher);
    }

    public void setHttpsTwoWay(String twoWay){
        setProperty(HTTPS_TWO_WAY, twoWay);
    }

    public void setHttpsCa(String ca){
        setProperty(HTTPS_CA, ca);
    }

    public void setHttpsClientCert(String clientCert){
        setProperty(HTTPS_CLIENT_CERT, clientCert);
    }

    public void setHttpsClientP12(String clientP12){
        setProperty(HTTPS_CLIENT_P12, clientP12);
    }

    public void setHttpsRequest(String requestsString){
        setProperty(HTTPS_REQUEST, requestsString);
    }

    public String getHttpsSslVersion(){
        return getPropertyAsString(HTTPS_SSL_VERSION);
    }

    public String getHttpsCipher(){
        return getPropertyAsString(HTTPS_CIPHER);
    }

    public String getHttpsTwoWay(){
        return getPropertyAsString(HTTPS_TWO_WAY);
    }

    public String getHttpsCa(){
        return getPropertyAsString(HTTPS_CA);
    }

    public String getHttpsClientCert(){
        return getPropertyAsString(HTTPS_CLIENT_CERT);
    }

    public String getHttpsClientP12(){
        return getPropertyAsString(HTTPS_CLIENT_P12);
    }

    public String getHttpsRequest(){
        return getPropertyAsString(HTTPS_REQUEST);
    }

    @Override
    public void testStarted() {

    }

    @Override
    public void testStarted(String s) {

    }

    @Override
    public void testEnded() {
        this.testEnded("local");
    }

    @Override
    public void testEnded(String s) {

    }
}
