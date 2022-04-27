package org.apache.jmeter.protocol.https.control.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.jmeter.gui.util.JSyntaxTextArea;
import org.apache.jmeter.gui.util.JTextScrollPane;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.protocol.https.sampler.HttpsSampler;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

import org.apache.jorphan.gui.JLabeledTextField;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

//这个注解必须要有
@SuppressWarnings("deprecation")
public class HttpsSamplerUI extends AbstractSamplerGui {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggingManager.getLoggerForClass();

    private final JLabeledTextField sslVersionField = new JLabeledTextField("SSL版本");
    private final JLabeledTextField cipherField = new JLabeledTextField("密码套件");
    private final JLabeledTextField twoWayField = new JLabeledTextField("双向");
    private final JLabeledTextField caCertField = new JLabeledTextField("CA证书");
    private final JLabeledTextField clientCertField = new JLabeledTextField("客户端证书");
    private final JLabeledTextField clientP12Field = new JLabeledTextField("客户端私钥");

    private final JLabeledTextField requestsStringField = new JLabeledTextField("请求信息");



    private final JSyntaxTextArea textMessage = new JSyntaxTextArea(10, 50);
    // private final JLabel textArea = new JLabel(JMeterUtils.getResString("kafka.message", "Message"));
    private final JLabel textArea = new JLabel("Message");
    private final JTextScrollPane textPanel = new JTextScrollPane(textMessage);
    public HttpsSamplerUI(){
        super();
        this.init();

    }

    private void init(){
        log.info("Initializing the UI.");
        setLayout(new BorderLayout());
        setBorder(makeBorder());

        add(makeTitlePanel(), BorderLayout.NORTH);
        JPanel mainPanel = new VerticalPanel();
        add(mainPanel, BorderLayout.CENTER);

        JPanel DPanel = new JPanel();
        DPanel.setLayout(new GridLayout(4, 2));
        DPanel.add(sslVersionField);
        DPanel.add(cipherField);
        DPanel.add(twoWayField);
        DPanel.add(caCertField);
        DPanel.add(clientCertField);
        DPanel.add(clientP12Field);
        DPanel.add(requestsStringField);

        JPanel ControlPanel = new VerticalPanel();
        ControlPanel.add(DPanel);
        ControlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "参数"));
        mainPanel.add(ControlPanel);

        /**这是是输出**/
        JPanel ContentPanel = new VerticalPanel();
        JPanel messageContentPanel = new JPanel(new BorderLayout());
        messageContentPanel.add(this.textArea, BorderLayout.NORTH);
        messageContentPanel.add(this.textPanel, BorderLayout.CENTER);
        ContentPanel.add(messageContentPanel);
        ContentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Content"));
        mainPanel.add(ContentPanel);

    }

    /**
     * 该方法创建一个新的Sampler，然后将界面中的数据设置到这个新的Sampler实例中。
     * **/
    @Override
    public TestElement createTestElement() {
        HttpsSampler sampler = new HttpsSampler();
        this.setupSamplerProperties(sampler);
        return sampler;
    }

    /**
     * 该方法会在reset新界面的时候调用，这里可以填入界面控件中需要显示的一些缺省的值。
     * **/
    @Override
    public void clearGui(){
        super.clearGui();
        this.sslVersionField.setText("TLSv1.2");
        this.cipherField.setText("ECDHE-AES256-SHA384");
        this.twoWayField.setText("y");
        this.caCertField.setText("xxx/xxx/ca.cert");
        this.clientCertField.setText("xxx/xxx/client.cert");
        this.clientP12Field.setText("xxx/xxx/client.key");
        this.requestsStringField.setText("GET /1k.html HTTP1.0\r\n");

    }

    /**
     * 界面与Sampler之间的数据交换
     * 该方法用于把Sampler中的数据加载到界面中。
     * 在实现自己的逻辑之前，先调用一下父类的方法super.configure(el)，这样可以确保框架自动为你加载一些缺省数据，比如Sampler的名字。
     * **/
    @Override
    public void configure(TestElement el){
        super.configure(el);
        HttpsSampler sampler = (HttpsSampler) el;
        this.sslVersionField.setText(sampler.getHttpsSslVersion());
        this.cipherField.setText(sampler.getHttpsCipher());
        this.twoWayField.setText(sampler.getHttpsTwoWay());
        this.caCertField.setText(sampler.getHttpsCa());
        this.clientCertField.setText(sampler.getHttpsClientCert());
        this.clientP12Field.setText(sampler.getHttpsClientP12());
        this.requestsStringField.setText(sampler.getHttpsRequest());
    }


    private void setupSamplerProperties(HttpsSampler sampler) {
        this.configureTestElement(sampler);
        sampler.setSslVersion(this.sslVersionField.getText());
        sampler.setCipher(this.cipherField.getText());
        sampler.setHttpsTwoWay(this.twoWayField.getText());
        sampler.setHttpsCa(this.caCertField.getText());
        sampler.setHttpsClientCert(this.clientCertField.getText());
        sampler.setHttpsClientP12(this.clientP12Field.getText());
        sampler.setHttpsRequest(this.requestsStringField.getText());
    }

    /**gui显示sample的名称**/
    @Override
    public String getStaticLabel() {
        return "Https Sampler";
    }

    @Override
    public String getLabelResource() {
        throw new IllegalStateException("This shouldn't be called");
    }

    /**
     * 这个方法用于把界面的数据移到Sampler中，刚好与上面的方法相反。
     * 在调用自己的实现方法之前，请先调用一下super.configureTestElement(e)，这个会帮助移到一些缺省的数据。
     * **/
    @Override
    public void modifyTestElement(TestElement testElement) {
        HttpsSampler sampler = (HttpsSampler) testElement;
        this.setupSamplerProperties(sampler);
    }

}
