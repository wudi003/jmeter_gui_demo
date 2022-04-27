需要重写几个函数。

/**gui显示的sample的名称**/
public String getStaticLabel()
public String getLabelResource()
//这个方法用于把界面的数据移到Sampler中。
public void modifyTestElement(TestElement testElement)
//界面与Sampler之间的数据交换
public void configure(TestElement el)
//该方法会在reset新界面的时候调用，这里可以填入界面控件中需要显示的一些缺省的值(就是默认显示值)
public void clearGui()
//该方法创建一个新的Sampler，然后将界面中的数据设置到这个新的Sampler实例中。
public TestElement createTestElement()

重写函数

//该方法是JMeter实现对目标系统发起请求实际工作的地方
public SampleResult sample(Entry entry)