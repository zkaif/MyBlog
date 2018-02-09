# SOA分布式个人博客系统

此网站使用前后端分离架构，后端为SOA分布式架构,主要由承担入口、静态页面和负载均衡服务的Nginx服务器，应用服务器Tomcat，数据库服务器MySQL，缓存服务器Memcached服务器，以及阿里云提供的OSS对象存储服务器，这几部分组成。

这里上传的单点登录系统是一个非常简单的实现，后面的版本会用Shiro框架来实现。

另外我这里使用阿里云的OSS时并没有“真正”的使用它，我只是把他当成一个数据库来用，然后从内网读取在由应用服务器返回数据。（原因是我发现不用OSS的外网带宽它就不收费。。。。）如果有人想修改这部分代码可以在这里修改：base项目中“top.u8u.storage.service.impl.StroageServiceAliImpl”抽象类。

[演示网站www.zhoukaifan.com](https://www.zhoukaifan.com/)

[>>>>开发文档下载<<<<](https://github.com/zkaif/MyBlog/blob/master/SOA%E5%88%86%E5%B8%83%E5%BC%8F%E4%B8%AA%E4%BA%BA%E5%8D%9A%E5%AE%A2%E7%B3%BB%E7%BB%9F-%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C.pdf)

