# angular入门教程

1. 安装nodejs

   https://nodejs.org/zh-cn/

2. 安装npm

   安装nodejs时，已经顺带安装了npm，无需额外操作

   校验nodejs和npm是否安装成功：

   ```
   node -v
   ```

   ```
   npm -v
   ```
   修改npm默认安装路径：
   ```
   npm config set prefix "E:/Developer/nodejs/npm_global"
   npm config set cache "E:/Developer/nodejs/npm_cache"
   ```
   查看npm配置：
   ```
   npm config ls -l
   ```

3. 安装angular

   npm install -g @angular/cli

   验证：
   ```
   ng v
   ```

4. spring boot + angular

   https://www.baeldung.com/spring-boot-angular-web

5. 富文本编辑器

   https://zhuanlan.zhihu.com/p/296543833

6. angularjs常用命令
   1. 启动服务（本地联调，默认4200端口）
   ```
   ng serve --open
   ```
   若要使用代理，则需补充代理配置并修改启动命令
   代理配置文件示例：proxy.conf.json
   ```json
   {
       "/eden": {
           "target": "http://localhost:18512",
           "secure": false
       }
   }
   ```
   启动命令补充参数，示例如下：
   ```shell
   ng serve --proxy-config proxy.conf.json --open
   ```
   2. 创建类
   ```
   ng generate class user
   ```
   3. 创建service
   ```
   ng generate service user
   ```
   4. 创建component
   ```
   ng generate component user-list
   ```
   5. 创建 workspace
   ```
   ng new angularclient
   ```
8. angularjs开发步骤
   1. 创建angular workspace
   2. 创建类
   3. 创建service
   4. 创建component，展示的和提交的
   5. 修改app.component.html
   6. 修改app-routing.module.ts
   7. 修改app.module.ts，特别是 providers 部分
9. spring boot + angular 打包部署
   1. 打包angular
   ```
   ng build
   ```
   指定环境变量打包
   ```
   ng build -c production
   ```
   2. 将angular打包生成的文件剪切到 resources/static/ 目录下
   3. spring pom文件添加maven插件
   ```xml
   <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.6.7</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
   ```
   <strong>切记一定要剔除掉repackage，不然启动会报找不到主清单错误</strong>
   4. 执行打包
   ```
   mvn clean package -Dmaven.test.skip=true
   ```
   5. 启动服务
   ```
   java -ja xxx.jar
   ```