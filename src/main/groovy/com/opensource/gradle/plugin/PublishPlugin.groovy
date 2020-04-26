package com.opensource.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication

class PublishPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("execute PublishPlugin ...")
        project.allprojects allProjects
    }

    static def allProjects = {
        apply plugin: 'java'
        apply plugin: 'maven-publish'

        /* 指定jdk版本 */
        sourceCompatibility = '1.8'

        /* java编译的时候缺省状态下会因为中文字符而失败 */
        [compileJava, compileTestJava, javadoc]*.options*.encoding = 'UTF-8'

        ext.repositoryUrl = RepoInfoUtils.getRepoSnapshotUrl()
        boolean isReleases = project.buildVersion.indexOf("-SNAPSHOT") > -1 ? false : true;
        if (isReleases == true) {
            repositoryUrl = RepoInfoUtils.getRepoReleaseUrl()
        }

        publishing {
            publications {
                myPublication(MavenPublication) {
                    //指定group/artifact/version信息
                    groupId project.buildGroup
                    artifactId project.name
                    version project.buildVersion
                    //打包类型 war: components.web jar: components.java
                    from components.java
                }
            }

            repositories {
                maven {
                    //指定要上传的maven私服仓库
                    url = repositoryUrl
                    //认证用户和密码
                    credentials {
                        username RepoInfoUtils.getRepoUserName()
                        password RepoInfoUtils.getRepoPassword()
                    }
                }
            }
        }

    }
}
