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



![image](https://user-images.githubusercontent.com/15613793/165494223-303129d7-19c3-449a-96e3-2b74b0dadcbb.png)


![image](https://user-images.githubusercontent.com/15613793/165494271-4df55702-f7b9-4847-b955-191f80f9a236.png)
![image](https://user-images.githubusercontent.com/15613793/165494333-528bbf86-0152-4ff4-8bae-534e35cfa10a.png)
![image](https://user-images.githubusercontent.com/15613793/165494364-42657f30-f37d-4067-acb9-9b2a72dde090.png)
![image](https://user-images.githubusercontent.com/15613793/165494400-684ed285-ac80-4a6f-9b53-a324ffe67417.png)
![image](https://user-images.githubusercontent.com/15613793/165494448-bd3d1840-943f-4c41-bd3f-bdc87978b339.png)
