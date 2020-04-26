## gradle-plugin

### 一、简介

- 负责人：孙权
- 说明：自定义gradle插件封装

### 二、接入说明

#### 1. 在项目的build.gradle配置文件中加入以下配置

```groovy
ext{
    buildGroup = 'com.opensource.component'
    buildVersion = '1.0.0-SNAPSHOT'
}

apply plugin: 'java'
apply plugin: 'com.opensource.publish'

buildscript {
    repositories {
        maven {
            credentials {
                username System.getenv("REPO_USER_NAME")
                password System.getenv("REPO_PASSWORD")
            }
            url System.getenv("REPO_URL_RELEASE")
        }
    }
    dependencies {
        classpath 'com.opensource.gradle:gradle-plugin:+'
    }
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
    }
}
```

#### 2. PublishPlugin：用于构建基础组件jar包并发布到仓库

```groovy
apply plugin: 'com.opensource.publish'
```     

### 三、构建发布
```groovy
gradle -x test clean build uA
```
